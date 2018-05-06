package vinova.henry.com.hotfilm.feature.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_home.*
import vinova.henry.com.hotfilm.R

class HomeActivity : AppCompatActivity() {

    var adapter: HomeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        rvFilm.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        viewModel.movies?.observe(this, Observer {
            adapter = HomeAdapter(it)
            rvFilm.adapter = adapter
            adapter?.notifyDataSetChanged()
        })


    }
}
