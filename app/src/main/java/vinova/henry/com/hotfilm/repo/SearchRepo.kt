package vinova.henry.com.hotfilm.repo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vinova.henry.com.hotfilm.API_KEY
import vinova.henry.com.hotfilm.models.Movie
import vinova.henry.com.hotfilm.models.MovieResponse
import vinova.henry.com.hotfilm.server.ServiceGenerator

class SearchRepo {

    fun getSearchResult(query: String, page: Int) : MutableLiveData<MovieResponse>?{
        val movies = MutableLiveData<MovieResponse>()
        ServiceGenerator.theMovieDBService.getSearchResult(API_KEY, "en-US", page, "true", query).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.d("search", "fail")
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies.value = response?.body()
            }

        })

        return movies
    }
}
