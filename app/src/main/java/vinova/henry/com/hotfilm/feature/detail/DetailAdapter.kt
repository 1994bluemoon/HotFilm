package vinova.henry.com.hotfilm.feature.detail

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.models.Trailer

class DetailAdapter internal constructor(private val mContext: Context, private val detailView: IDetailContract.IDetailView) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private var trailers: List<Trailer>? = null

    init {
        trailers = ArrayList()
    }

    internal fun setTrailers(trailers: List<Trailer>) {
        this.trailers = trailers
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.item_youtube_thumbnail, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle!!.text = trailers!![position].name
        val thumbnailUrl = "https://img.youtube.com/vi/" + trailers!![position].key + "/0.jpg"
        Picasso.with(mContext)
                .load(thumbnailUrl)
                .into(holder.imThumbnail)

        holder.cvThumbnail!!.setOnClickListener { detailView.onClickItem(position) }
    }


    override fun getItemCount(): Int {
        return if (trailers == null) 0 else trailers!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.im_thumbnail)
        internal var imThumbnail: ImageView? = null
        @BindView(R.id.tv_title)
        internal var tvTitle: TextView? = null
        @BindView(R.id.cv_thumbnail)
        internal var cvThumbnail: CardView? = null

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}
