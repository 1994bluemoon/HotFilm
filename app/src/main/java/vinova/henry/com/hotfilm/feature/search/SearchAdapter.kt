package vinova.henry.com.hotfilm.feature.search

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie_cardview.view.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.interf.IMovieEvent
import vinova.henry.com.hotfilm.models.Movie

class SearchAdapter(private val iMovieEvent: IMovieEvent?) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var movies: ArrayList<Movie>? = null

    fun setAdapterMovies(movies: List<Movie>?){
        this.movies?.addAll(movies ?: ArrayList<Movie>())
    }

    fun clearData(){
        this.movies = ArrayList<Movie>()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.itemView.tvTitle.text = movies?.get(position)?.title
        holder.itemView.tvOverview.text = movies?.get(position)?.overview

        Glide.with(holder.itemView.imBackdrop).load("https://image.tmdb.org/t/p/w500" + movies?.get(position)?.backdrop_path).into(holder.itemView.imBackdrop)

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                iMovieEvent?.onItemRvClicked(movies?.get(position))
            }

        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}