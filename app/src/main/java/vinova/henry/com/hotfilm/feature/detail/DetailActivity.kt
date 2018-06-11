package vinova.henry.com.hotfilm.feature.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.models.detail.MovieDetail

class DetailActivity : AppCompatActivity() {

    internal var movieId: Int? = null
    internal var trailerAdapter: TrailerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        val detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        trailerAdapter = TrailerAdapter()
        rvThumbnail.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        rvThumbnail.adapter = trailerAdapter

        detailViewModel.movieDetail.observe(this, Observer {
            setData(it)
        })
        detailViewModel.trailer.observe(this, Observer {
            trailerAdapter?.setTrailerList(it?.results)
        })

        val ex = intent.extras
        if (ex != null) {
            movieId = ex.get("movieId") as Int
            detailViewModel.setMovieId(movieId)
        }


    }

    fun setData(movieDetail: MovieDetail?){
        val posterUri = "https://image.tmdb.org/t/p/w500/${movieDetail?.poster_path}"
        Glide.with(imPoster).load(posterUri).into(imPoster)
        val backdropUri = "https://image.tmdb.org/t/p/w500/${movieDetail?.backdrop_path}"
        Glide.with(imBackdrop).load(backdropUri).into(imBackdrop)
        tvTitle.text = movieDetail?.title
        tvDetail.text = movieDetail?.overview
        collapsingToolbarLayout.title = movieDetail?.title
    }
}
