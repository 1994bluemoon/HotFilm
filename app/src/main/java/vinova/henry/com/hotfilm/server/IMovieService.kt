package vinova.henry.com.hotfilm.server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vinova.henry.com.hotfilm.models.MvResult
import vinova.henry.com.hotfilm.models.TrailerResult

interface IMovieService {

    @GET("discover/movie?")
    fun getDiscoverMovie(@Query("api_key") api_key: String, @Query("language") language: String, @Query("sort_by") sort_by: String, @Query("page") page: Int): Call<MvResult>

    @GET("videos?")
    fun getTrailer(@Query("api_key") api_key: String): Call<TrailerResult>

}
