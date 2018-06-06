package vinova.henry.com.hotfilm.pager

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.models.Movie
import vinova.henry.com.hotfilm.models.PageDataSet

sealed class PageItem (view: View) : RecyclerView.ViewHolder(view) {
    fun clearContent() {}
}

class ItemMovie(view: View) : PageItem(view){
    private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
    private val tvOverview = view.findViewById<TextView>(R.id.tvOverview)
    private val imBackdrop = view.findViewById<ImageView>(R.id.imBackdrop)

    fun setContent(movie: Movie?){
        tvTitle.text = movie?.title
        tvOverview.text = movie?.overview
        Glide.with(imBackdrop).load("https://image.tmdb.org/t/p/w500" + movie?.backdrop_path).into(imBackdrop)
    }
}

/*class ItemUser(view: View) : PageItem(view) {
    private val avatar = view.findViewById<ImageView>(R.id.avatar)
    private val userName = view.findViewById<TextView>(R.id.user_name)
    private val status = view.findViewById<TextView>(R.id.status)

    fun setContent(content: PageDataSet.ItemData) {
        userName.setText(content.userName)
        status.setText(content.status)
        avatar.setImageResource(content.avatar)

        Glide.with(avatar).load(content.avatar).into(avatar)
    }
}

class ItemImage(view: View) : PageItem(view) {
    private val imageView = view.findViewById<ImageView>(R.id.page_image)

    fun setImage(imgId: Int) {
        Glide.with(imageView).load(imgId).into(imageView)
    }
}*/
