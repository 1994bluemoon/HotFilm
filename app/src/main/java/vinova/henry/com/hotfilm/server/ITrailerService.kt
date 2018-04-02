package vinova.henry.com.hotfilm.server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vinova.henry.com.hotfilm.models.TrailerResult

interface ITrailerService {
    @GET("videos?")
    fun getTrailerData(@Query("api_key") api_key: String): Call<TrailerResult>
}
