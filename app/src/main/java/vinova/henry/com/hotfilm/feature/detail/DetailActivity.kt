package vinova.henry.com.hotfilm.feature.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.models.detail.MovieDetail

class DetailActivity : AppCompatActivity() {

    internal var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        detailViewModel.movieDetail.observe(this, Observer {
            setData(it)
        })

        val ex = intent.extras
        if (ex != null) {
            movieId = ex.get("movieId") as Int
            detailViewModel.setMovieId(movieId)
        }



        /*val posterUri = "https://image.tmdb.org/t/p/w500/" + movie!!.posterPath
        Picasso.with(this).load(posterUri).into(imPoster)

        val backdropUri = "https://image.tmdb.org/t/p/w500/" + movie!!.backdropPath
        Picasso.with(this).load(backdropUri).into(imBackdrop)*/

        val linearLayoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)

    }

    fun setData(movieDetail: MovieDetail?){
        val posterUri = "https://image.tmdb.org/t/p/w500/${movieDetail?.poster_path}"
        Picasso.with(this).load(posterUri).into(imPoster)
        val backdropUri = "https://image.tmdb.org/t/p/w500/${movieDetail?.backdrop_path}"
        Picasso.with(this).load(backdropUri).into(imBackdrop)
        tvTitle.text = movieDetail?.title
        tvDetail.text = movieDetail?.overview
    }
}
