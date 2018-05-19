package vinova.henry.com.hotfilm.feature.discovermovie

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_discover_movie.*
import vinova.henry.com.hotfilm.R

class DiscoverMovieActivity : AppCompatActivity() {

    var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover_movie)

        val viewModel = ViewModelProviders.of(this).get(DiscoverMovieModel::class.java)
        rvFilm.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        viewModel.movies?.observe(this, Observer {
            adapter = MovieAdapter(it)
            rvFilm.adapter = adapter
            adapter?.notifyDataSetChanged()
        })


    }
}
