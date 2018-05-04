package vinova.henry.com.hotfilm.repo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vinova.henry.com.hotfilm.models.DiscoverMovieResult
import vinova.henry.com.hotfilm.server.ServiceGenerator

class DescoverMovieRepo{


    fun getDescoverMovie(page: Int){



        ServiceGenerator.theMovieDBService.getDiscoverMovie(ServiceGenerator.API_KEY, "en-US", "popularity.desc", page).enqueue(object : Callback<DiscoverMovieResult>{
            override fun onFailure(call: Call<DiscoverMovieResult>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<DiscoverMovieResult>?, response: Response<DiscoverMovieResult>?) {

            }

        })
    }
}