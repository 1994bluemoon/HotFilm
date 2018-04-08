package vinova.henry.com.hotfilm.feature.home

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import java.sql.SQLException
import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.datahelper.IMovieSchema
import vinova.henry.com.hotfilm.models.Movie

class HomeActivity : AppCompatActivity(), IHomeContract.IView, IMovieSchema {

    internal var mPage: Int = 0
    internal lateinit var onLoadMoreListener : IOnLoadMoreListener
    internal lateinit var homePresenterImp: HomePresenterImp
    internal lateinit var movies: MutableList<Movie>
    internal lateinit var adapter: MovieAdapter
    @BindView(R.id.rv_film)
    internal var rvFilm: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)

        movies = ArrayList()
        homePresenterImp = HomePresenterImp(this, this)
        mPage = 1
        homePresenterImp.getUserFromServer(mPage)

        rvFilm!!.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        rvFilm!!.setHasFixedSize(true)
        adapter = MovieAdapter(rvFilm!!, this)
        rvFilm!!.adapter = adapter


        adapter.setOnLoadMoreListener(onLoadMoreListener){
            movies.add(Movie())
            adapter.notifyItemInserted(movies.size - 1)

            Handler().postDelayed({
                movies.removeAt(movies.size - 1)
                adapter.notifyItemRemoved(movies.size)
                mPage += 1
                homePresenterImp.getUserFromServer(mPage)
                Log.d("page ", mPage.toString())
            }, 1000)
        }
    }

    @Throws(SQLException::class)
    override fun showSuccess() {
        movies.addAll(homePresenterImp.movies!!)
        contentResolver.delete(IMovieSchema.CONTENT_URI, null, null)
        for (m in homePresenterImp.movies!!) {
            contentResolver.insert(IMovieSchema.CONTENT_URI, homePresenterImp.getContentValue(m))
            Log.d("save child offline", "item")
        }
        adapter.setMovies(movies)
        adapter.notifyDataSetChanged()
        adapter.setLoaded()
        Log.d("getMovie from server", "OK")
    }

    override fun showFail() {

        movies = ArrayList()

        val cursor = contentResolver.query(IMovieSchema.CONTENT_URI, IMovieSchema.MOVIE_COLUMN, null, null, null)

        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                movies.add(homePresenterImp.portMovie(cursor))
                cursor.moveToNext()
            }
            cursor.close()
        }
        adapter.setMovies(movies as ArrayList<Movie>)
        adapter.notifyDataSetChanged()
        Log.d("getMovie frome server", "Failed")
        Log.d("get offline data", "OK")
    }

    override fun showLoadDataOfflineSuccess() {
        //TODO
    }
}
