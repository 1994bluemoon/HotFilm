package vinova.henry.com.hotfilm.feature.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_search.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.feature.home.HomeActivity


class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        svSearch.setOnQueryTextListener(this)
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchAdapter = SearchAdapter()

        rvSearch.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvSearch.adapter = searchAdapter

        searchViewModel.movies.observe(this, Observer {
            searchAdapter.setAdapterMovies(it?.results)
            searchAdapter.notifyDataSetChanged()
            tvResultCount.text = "Total ${it?.total_results} results"
        })

        imBack.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@SearchActivity, HomeActivity::class.java))
                this@SearchActivity.finish()
            }
        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchViewModel.setQuery(query)
        tvResultCount.text = "Total 0 result"
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("query", newText)
        return false
    }
}
