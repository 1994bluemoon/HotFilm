package vinova.henry.com.hotfilm.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vinova.henry.com.hotfilm.BASE_URL

class ServiceGenerator{
    companion object {
        val theMovieDBService: ITheMovieDBService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ITheMovieDBService::class.java)
    }
}