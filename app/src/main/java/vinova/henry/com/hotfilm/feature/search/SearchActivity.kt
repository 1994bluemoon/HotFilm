package vinova.henry.com.hotfilm.feature.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_search.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.feature.detail.DetailActivity
import vinova.henry.com.hotfilm.feature.home.HomeActivity
import vinova.henry.com.hotfilm.interf.IMovieEvent
import vinova.henry.com.hotfilm.models.Movie

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener, IMovieEvent {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var totalPage: Int? = 0
    private var curPage: Int? = 1
    private var isLoading: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        svSearch.setOnQueryTextListener(this)
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchAdapter = SearchAdapter(this)

        linearLayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvSearch.layoutManager = linearLayoutManager
        rvSearch.adapter = searchAdapter

        searchViewModel.moviesQueryChange.observe(this, Observer {
            searchAdapter.clearData()
            searchAdapter.setAdapterMovies(it?.results)
            totalPage = it?.total_pages
            tvResultCount.text = "Total ${it?.total_results} results"
            loaded()
        })

        searchViewModel.moviesPageChange.observe(this, Observer {
            searchAdapter.setAdapterMovies(it?.results)
            loaded()
        })

        rvSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = linearLayoutManager.itemCount
                val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

                if (lastVisibleItem == totalItemCount - 10 && !isLoading){
                    if (curPage ?: 0 < totalPage ?: 0){
                        curPage = curPage?.plus(1)
                        searchViewModel.setPage(curPage)
                        Log.d("curPage", curPage.toString())
                        loading()
                    }
                }
            }
        })

        imBack.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@SearchActivity, HomeActivity::class.java))
                this@SearchActivity.finish()
            }
        })
    }

    override fun onBackPressed() {
        startActivity(Intent(this@SearchActivity, HomeActivity::class.java))
        this@SearchActivity.finish()
        super.onBackPressed()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchViewModel.setQuery(query)
        tvResultCount.text = "Total result: 0"
        curPage = 1
        loading()
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onItemRvClicked(movie: Movie?) {
        Log.d("click", movie?.title)
        startActivity(Intent(this@SearchActivity, DetailActivity::class.java).putExtra("movieId", movie?.id))
    }

    override fun onLoadMoreListener(currentPage: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun loaded(){
        isLoading = false
        pbLoading.visibility = View.INVISIBLE
    }

    fun loading(){
        isLoading = true
        pbLoading.visibility = View.VISIBLE
    }
}
