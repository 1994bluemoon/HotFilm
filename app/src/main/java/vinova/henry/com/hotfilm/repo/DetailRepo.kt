package vinova.henry.com.hotfilm.repo

import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vinova.henry.com.hotfilm.API_KEY
import vinova.henry.com.hotfilm.models.detail.MovieDetail
import vinova.henry.com.hotfilm.server.ServiceGenerator

class DetailRepo{
    fun getMovieDetail(movieId: Int): MutableLiveData<MovieDetail>?{
        val movieDetail: MutableLiveData<MovieDetail>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getMovieDetail(movieId, API_KEY, "en-US").enqueue(object : Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<MovieDetail>?, response: Response<MovieDetail>?) {
                movieDetail?.value = response?.body()
            }
        })
        return movieDetail
    }
}