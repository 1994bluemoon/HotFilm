package vinova.henry.com.hotfilm.server

import java.net.URL

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*ApiUtils {
    private val BASE_URL = "https://api.themoviedb.org/3/"
    val apiKey = "54715436e1813692f121feb9fd97709e"

   *//* val soService: IMovieService
        get() {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(IMovieService::class.java)
        }

    fun getTrailerService(movieId: Long): ITrailerService {
        val url = BASE_URL + "movie/" + movieId + "/"
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ITrailerService::class.java!!)*//*
    }
}*/
