package vinova.henry.com.hotfilm.feature.trailer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_trailer_youtube_view.view.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.models.trailer.Trailer

class TrailerAdapter(val itemClicked : (String?) -> Unit) : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    private var trailers: ArrayList<Trailer>? = ArrayList()

    fun setTrailers(trailers: ArrayList<Trailer>?){
        this.trailers = trailers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trailer_youtube_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailers?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_title.text = trailers?.get(position)?.name
        val thumbnailUrl = "https://img.youtube.com/vi/${trailers?.get(position)?.key}/0.jpg"
        Glide.with(holder.itemView.im_thumbnail).load(thumbnailUrl).into(holder.itemView.im_thumbnail)

        holder.itemView.setOnClickListener {
            itemClicked(trailers?.get(holder.adapterPosition)?.key)
        }
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view)
}