package vinova.henry.com.hotfilm.feature.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie_cardview.view.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.models.Movie

data class HomeAdapter(var movies: List<Movie>? = null) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies?.get(position))
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie?){
            itemView.tvTitle.text = movie?.title
            itemView.tvOverview.text = movie?.overview
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500" + movie?.backdrop_path).into(itemView.imBackdrop)
        }
    }

}