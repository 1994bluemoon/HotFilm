package vinova.henry.com.hotfilm.feature.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.squareup.picasso.Picasso

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.feature.detail.DetailActivity
import vinova.henry.com.hotfilm.models.Movie

class MovieAdapter internal constructor(recyclerView: RecyclerView, private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private lateinit var onLoadMoreListener: IOnLoadMoreListener
    private var isLoading: Boolean = false
    private var movies: List<Movie>? = null
    private val visibleThreshold = 5
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0

    init {
        movies = ArrayList()

        val layoutManager = recyclerView.layoutManager as GridLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = layoutManager.itemCount
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener!!.onLoadMore()
                    }
                    isLoading = true
                }
            }
        })
    }

    override fun getItemViewType(position: Int): Int {
        return if (movies!![position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val v = LayoutInflater.from(mContext).inflate(R.layout.item_movie_cardview, parent, false)
            return ItemViewHolder(v)
        } else  {
            val v = LayoutInflater.from(mContext).inflate(R.layout.item_loading, parent, false)
            return LoadingViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        if (holder is ItemViewHolder) {
            val poster = "https://image.tmdb.org/t/p/w500/" + movies!![position].posterPath
            Picasso.with(mContext)
                    .load(poster)
                    .into(holder.imBackdrop)
            holder.tvTitle!!.text = movies!![position].title

            holder.cvMovie!!.setOnClickListener {
                val intent = Intent(mContext, DetailActivity::class.java)
                intent.putExtra("Movie", movies!![position])
                mContext.startActivity(intent)
            }
        } else if (holder is LoadingViewHolder) {
            holder.progressBar!!.isIndeterminate = true
        }

    }

    override fun getItemCount(): Int {
        return if (movies == null) 0 else movies!!.size
    }

    internal fun setOnLoadMoreListener(mOnLoadMoreListener: IOnLoadMoreListener, function: () -> Boolean) {
        this.onLoadMoreListener = mOnLoadMoreListener
    }

    internal fun setMovies(movies: List<Movie>) {
        this.movies = movies
    }

    internal fun setLoaded() {
        isLoading = false
    }

    internal inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R.id.im_backdrop)
        var imBackdrop: ImageView? = null
        @BindView(R.id.tv_title)
        var tvTitle: TextView? = null
        @BindView(R.id.cv_movie)
        var cvMovie: CardView? = null

        init {
            ButterKnife.bind(this, view)
        }
    }

    internal inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.progressBar1)
        var progressBar: ProgressBar? = null

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}
