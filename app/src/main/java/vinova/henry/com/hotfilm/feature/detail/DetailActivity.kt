/*
package vinova.henry.com.hotfilm.feature.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.feature.trailer.YouTubeActivity
import vinova.henry.com.hotfilm.models.Trailer
import vinova.henry.com.hotfilm.models.TrailerResult
import java.util.*

class DetailActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    internal var movie: MovieBase? = null
    internal var isClicked = false
    internal lateinit var trailers: MutableList<Trailer>
    internal lateinit var detailPresenterImp: DetailPresenterImp
    internal var isFirst = true
    internal lateinit var detailAdapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        trailers = ArrayList()

        val ex = intent.extras
        if (ex != null) {
            movie = ex.get("MovieBase") as MovieBase
        }

        val posterUri = "https://image.tmdb.org/t/p/w500/" + movie!!.posterPath
        Picasso.with(this).load(posterUri).into(imPoster)

        val backdropUri = "https://image.tmdb.org/t/p/w500/" + movie!!.backdropPath
        Picasso.with(this).load(backdropUri).into(imBackdrop)

        tvTitle!!.text = movie!!.title
        tvDetail!!.text = movie!!.overview

        //spinnerTrailer.setOnItemSelectedListener(this);
 detailAdapter = DetailAdapter(this@DetailActivity, this)
        val linearLayoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
        rvThumbnail!!.layoutManager = linearLayoutManager
        rvThumbnail!!.setHasFixedSize(true)
        rvThumbnail!!.adapter = detailAdapter

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (isFirst) {
            isFirst = false
        } else {
            Log.d("selected", trailers[position].name)
            val intent = Intent(this, YouTubeActivity::class.java)
            intent.putExtra("Trailer", trailers[position])
            startActivity(intent)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        if (trailers.size == 1) {
            val intent = Intent(this, YouTubeActivity::class.java)
            intent.putExtra("Trailer", trailers[0])
            startActivity(intent)
        }
    }

    fun onViewClicked() {
        if (isClicked) {
            isClicked = false
            rvThumbnail!!.visibility = View.GONE
        } else if (!isClicked) {
            isClicked = true
            rvThumbnail!!.visibility = View.VISIBLE
        }
    }

    fun onClickItem(position: Int) {
        Log.d("selected", trailers[position].name)
        val intent = Intent(this, YouTubeActivity::class.java)
        intent.putExtra("Trailer", trailers[position])
        startActivity(intent)
    }
}
*/
