package vinova.henry.com.hotfilm.feature.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.feature.trailer.YouTubeActivity
import vinova.henry.com.hotfilm.models.detail.MovieDetail
import vinova.henry.com.hotfilm.models.trailer.Trailer

class DetailActivity : AppCompatActivity() {

    private var detailViewModel: DetailViewModel? = null
    private var movieId: Int? = null
    private var trailerAdapter: TrailerAdapter? = null
    private var relateMovieAdapter: RelateMovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_detail)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        init()

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        detailViewModel?.movieDetail?.observe(this, Observer {
            setData(it)
        })
        detailViewModel?.trailer?.observe(this, Observer {
            trailerAdapter?.setTrailerList(it?.results)
        })
        detailViewModel?.relateMovie?.observe(this, Observer {
            relateMovieAdapter?.setRelateMovies(it?.results)
        })

        val ex = intent.extras
        if (ex != null) {
            movieId = ex.get("movieId") as Int
            detailViewModel?.setMovieId(movieId)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId){
            android.R.id.home -> {
                super.onBackPressed()
                false
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_2)

        trailerAdapter = TrailerAdapter(onTrailerClicked)
        relateMovieAdapter = RelateMovieAdapter(onRelateMovieClicked)
        rvThumbnail.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        rvRelateMovie.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        rvThumbnail.adapter = trailerAdapter
        rvRelateMovie.adapter = relateMovieAdapter
    }

    private val onTrailerClicked = fun(position: Int?, trailers: ArrayList<Trailer>?){
        startActivity(Intent(this@DetailActivity, YouTubeActivity::class.java).putExtra("position", position).putExtra("trailers", trailers))
    }

    private val onRelateMovieClicked = fun(movieId: Int?){
        detailViewModel?.setMovieId(movieId)
    }

    private fun setData(movieDetail: MovieDetail?){
        val posterUri = "https://image.tmdb.org/t/p/w500/${movieDetail?.poster_path}"
        Glide.with(imPoster).load(posterUri).into(imPoster)
        val backdropUri = "https://image.tmdb.org/t/p/w500/${movieDetail?.backdrop_path}"
        Glide.with(imBackdrop).load(backdropUri).into(imBackdrop)
        tvTitle.text = movieDetail?.title
        tvDetail.text = movieDetail?.overview
        collapsingToolbarLayout.title = movieDetail?.title

        tvRating.text = "Rating ${movieDetail?.vote_average}/10"
        rbRating.rating = (movieDetail?.vote_average)?.toFloat() ?: 5.0F
        tvRatingDetail.text = "Total rate: ${movieDetail?.vote_count}"
    }
}
