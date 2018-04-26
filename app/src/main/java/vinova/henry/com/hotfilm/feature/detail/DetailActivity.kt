package vinova.henry.com.hotfilm.feature.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.feature.trailer.YouTubeActivity
import vinova.henry.com.hotfilm.models.Movie
import vinova.henry.com.hotfilm.models.Trailer
import vinova.henry.com.hotfilm.models.TrailerResult
import vinova.henry.com.hotfilm.server.ApiUtils
import java.util.*

class DetailActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    internal var movie: Movie? = null
    internal var isClicked = false
    internal lateinit var trailers: MutableList<Trailer>
    internal lateinit var detailPresenterImp: DetailPresenterImp
    internal var isFirst = true
    internal lateinit var detailAdapter: DetailAdapter

    @BindView(R.id.im_backdrop)
    internal var imBackdrop: ImageView? = null
    @BindView(R.id.im_poster)
    internal var imPoster: ImageView? = null
    @BindView(R.id.tv_title)
    internal var tvTitle: TextView? = null
    @BindView(R.id.tv_detail)
    internal var tvDetail: TextView? = null
    /*@BindView(R.id.spinner_trailer)
    Spinner spinnerTrailer;*/
    @BindView(R.id.rv_thumbnail)
    internal var rvThumbnail: RecyclerView? = null
    @BindView(R.id.bt_showtrailer)
    internal var btShowtrailer: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ButterKnife.bind(this)
        trailers = ArrayList()
        detailPresenterImp = DetailPresenterImp()

        val ex = intent.extras
        if (ex != null) {
            movie = ex.get("Movie") as Movie
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

        val service = ApiUtils.getTrailerService(movie!!.id!!.toLong())
        val call = service.getTrailerData(ApiUtils.apiKey)
        call.enqueue(object : Callback<TrailerResult> {
            override fun onResponse(call: Call<TrailerResult>, response: Response<TrailerResult>) {
                //TODO
                (trailers as ArrayList<Trailer>).addAll(response.body()!!.trailerList!!)

                /*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailActivity.this, R.layout.support_simple_spinner_dropdown_item, detailPresenterImp.createSpinnerItem(trailers));
                arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerTrailer.setAdapter(arrayAdapter);*/

                detailAdapter.setTrailers(trailers)
                detailAdapter.notifyDataSetChanged()
                btShowtrailer!!.visibility = View.VISIBLE
            }

            override fun onFailure(call: Call<TrailerResult>, t: Throwable) {
                //TODO
            }
        })

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

    @OnClick(R.id.bt_showtrailer)
    fun onViewClicked() {
        if (isClicked) {
            isClicked = false
            rvThumbnail!!.visibility = View.GONE
        } else if (!isClicked) {
            isClicked = true
            rvThumbnail!!.visibility = View.VISIBLE
        }
    }

    override fun onClickItem(position: Int) {
        Log.d("selected", trailers[position].name)
        val intent = Intent(this, YouTubeActivity::class.java)
        intent.putExtra("Trailer", trailers[position])
        startActivity(intent)
    }
}
