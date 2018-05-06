package vinova.henry.com.hotfilm.repo

import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vinova.henry.com.hotfilm.models.DiscoverMovieResponse
import vinova.henry.com.hotfilm.models.Movie
import vinova.henry.com.hotfilm.server.ServiceGenerator

class DiscoverMovieRepo{

    var movies: MutableLiveData<List<Movie>>? = MutableLiveData()

    fun getDescoverMovie(page: Int): MutableLiveData<List<Movie>>?{

        ServiceGenerator.theMovieDBService.getDiscoverMovie(ServiceGenerator.API_KEY, "en-US", "popularity.desc", page).enqueue(object : Callback<DiscoverMovieResponse>{
            override fun onFailure(call: Call<DiscoverMovieResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<DiscoverMovieResponse>?, response: Response<DiscoverMovieResponse>?) {

                movies?.value = response?.body()?.results

            }
        })

        return movies
    }
}