package vinova.henry.com.hotfilm.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator{
    companion object {
        val BASE_URL: String = "https://api.themoviedb.org/3/"
        val API_KEY: String = "54715436e1813692f121feb9fd97709e"

        val theMovieDBService: ITheMovieDBService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ITheMovieDBService::class.java)
    }
}