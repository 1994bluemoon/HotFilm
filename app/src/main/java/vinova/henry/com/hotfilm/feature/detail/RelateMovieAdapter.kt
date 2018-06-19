package vinova.henry.com.hotfilm.feature.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_relate_movie.view.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.models.Movie

class RelateMovieAdapter(val relateMovieClicked : (movieId: Int?) -> Unit) : RecyclerView.Adapter<RelateMovieAdapter.ViewHolder>() {

    private var movies: List<Movie>? = ArrayList()

    fun setRelateMovies(movies: List<Movie>?){
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_relate_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvTitle.text = movies?.get(position)?.title
        Glide.with(holder.itemView.imBackdrop).load("https://image.tmdb.org/t/p/w500${movies?.get(position)?.backdrop_path}").into(holder.itemView.imBackdrop)
        holder.itemView.setOnClickListener { relateMovieClicked(movies?.get(holder.adapterPosition)?.id) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}