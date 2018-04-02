package vinova.henry.com.hotfilm.feature.home

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

import java.sql.SQLException
import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vinova.henry.com.hotfilm.datahelper.IMovieSchema
import vinova.henry.com.hotfilm.models.Movie
import vinova.henry.com.hotfilm.models.MvResult
import vinova.henry.com.hotfilm.server.ApiUtils
import vinova.henry.com.hotfilm.server.IMovieService

class HomePresenterImp internal constructor(private val mContext: Context, private val iView: IHomeContract.IView) : IHomeContract.IPresenter, IMovieSchema {
    var movies: MutableList<Movie>? = null

    init {
        movies = ArrayList()
    }

    internal fun getMovies(): List<Movie>? {
        return movies
    }

    override fun getUserFromServer(page: Int) {
        val service = ApiUtils.soService
        val call = service.getServerData(ApiUtils.apiKey, "en-US", "popularity.desc", page)
        call.enqueue(object : Callback<MvResult> {
            override fun onResponse(call: Call<MvResult>, response: Response<MvResult>) {
                movies = ArrayList()
                movies!!.addAll(response.body()!!.movieList!!)
                try {
                    iView.showSuccess()
                } catch (e: SQLException) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<MvResult>, t: Throwable) {
                iView.showFail()
            }
        })
    }

    override fun getContentValue(movie: Movie): ContentValues {
        val initialValues = ContentValues()
        initialValues.put(IMovieSchema.COL_ID, movie.id)
        initialValues.put(IMovieSchema.COL_BACKDROP_PATH, movie.backdropPath)
        initialValues.put(IMovieSchema.COL_OVERVIEW, movie.overview)
        initialValues.put(IMovieSchema.COL_POSTER_PATH, movie.posterPath)
        initialValues.put(IMovieSchema.COL_RELEASE_DAY, movie.releaseDate)
        initialValues.put(IMovieSchema.COL_TITLE, movie.title)
        initialValues.put(IMovieSchema.COL_VOTE_AVERAGE, movie.voteAverage)
        initialValues.put(IMovieSchema.COL_VOTE_COUNT, movie.voteCount)
        return initialValues
    }

    override fun portMovie(cursor: Cursor): Movie {
        val movie = Movie()
        movie.backdropPath = cursor.getString(cursor.getColumnIndex(IMovieSchema.COL_BACKDROP_PATH))
        movie.id = Integer.valueOf(cursor.getString(cursor.getColumnIndex(IMovieSchema.COL_ID)))
        movie.posterPath = cursor.getString(cursor.getColumnIndex(IMovieSchema.COL_POSTER_PATH))
        movie.overview = cursor.getString(cursor.getColumnIndex(IMovieSchema.COL_OVERVIEW))
        movie.title = cursor.getString(cursor.getColumnIndex(IMovieSchema.COL_TITLE))
        movie.releaseDate = cursor.getString(cursor.getColumnIndex(IMovieSchema.COL_RELEASE_DAY))
        movie.voteCount = Integer.valueOf(cursor.getString(cursor.getColumnIndex(IMovieSchema.COL_VOTE_COUNT)))
        movie.voteAverage = java.lang.Double.valueOf(cursor.getString(cursor.getColumnIndex(IMovieSchema.COL_VOTE_AVERAGE)))

        return movie
    }
}
