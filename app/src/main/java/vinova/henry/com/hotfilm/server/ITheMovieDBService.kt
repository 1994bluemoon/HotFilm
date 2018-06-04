package vinova.henry.com.hotfilm.server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vinova.henry.com.hotfilm.models.Genre
import vinova.henry.com.hotfilm.models.MovieResponse
import vinova.henry.com.hotfilm.models.TrailerResult

interface ITheMovieDBService{

    @GET("discover/movie")
    fun getDiscoverMovie(@Query("api_key") api_key: String,
                         @Query("language") language: String,
                         @Query("sort_by") sort_by: String,
                         @Query("page") page: Int): Call<MovieResponse>

    @GET("videos")
    fun getMovieTrailer(@Query("api_key") api_key: String): Call<TrailerResult>

    @GET("genre/movie/list")
    fun getGenre(@Query("api_key") api_key: String,
                 @Query("language") language: String) : Call<Genre>

    @GET("/genre/28/movies")
    fun getActionMovie(@Query("api_key") api_key: String,
                       @Query("language") language: String,
                       @Query("sort_by") sort_by: String,
                       @Query("page") page: Int) : Call<MovieResponse>

    @GET("/genre/12/movies")
    fun getAdventureMovie(@Query("api_key") api_key: String,
                          @Query("language") language: String,
                          @Query("sort_by") sort_by: String,
                          @Query("page") page: Int) : Call<MovieResponse>


    @GET("/genre/16/movies")
    fun getAnimationMovie(@Query("api_key") api_key: String,
                          @Query("language") language: String,
                          @Query("sort_by") sort_by: String,
                          @Query("page") page: Int) : Call<MovieResponse>


    @GET("/genre/99/movies")
    fun getDocumentaryMovie(@Query("api_key") api_key: String,
                            @Query("language") language: String,
                            @Query("sort_by") sort_by: String,
                            @Query("page") page: Int) : Call<MovieResponse>


    @GET("/genre/10751/movies")
    fun getFamilyMovie(@Query("api_key") api_key: String,
                       @Query("language") language: String,
                       @Query("sort_by") sort_by: String,
                       @Query("page") page: Int) : Call<MovieResponse>


    @GET("/genre/80/movies")
    fun getCrimeMovie(@Query("api_key") api_key: String,
                      @Query("language") language: String,
                      @Query("sort_by") sort_by: String,
                      @Query("page") page: Int) : Call<MovieResponse>


    @GET("/genre/18/movies")
    fun getDramaMovie(@Query("api_key") api_key: String,
                      @Query("language") language: String,
                      @Query("sort_by") sort_by: String,
                      @Query("page") page: Int) : Call<MovieResponse>


    @GET("/genre/14/movies")
    fun getFantasyMovie(@Query("api_key") api_key: String,
                        @Query("language") language: String,
                        @Query("sort_by") sort_by: String,
                        @Query("page") page: Int) : Call<MovieResponse>


    @GET("/genre/36/movies")
    fun getHistoryMovie(@Query("api_key") api_key: String,
                        @Query("language") language: String,
                        @Query("sort_by") sort_by: String,
                        @Query("page") page: Int) : Call<MovieResponse>


    @GET("/genre/27/movies")
    fun getHorrorMovie(@Query("api_key") api_key: String,
                       @Query("language") language: String,
                       @Query("sort_by") sort_by: String,
                       @Query("page") page: Int) : Call<MovieResponse>


    @GET("/genre/10402/movies")
    fun getMusicMovie(@Query("api_key") api_key: String,
                      @Query("language") language: String,
                      @Query("sort_by") sort_by: String,
                      @Query("page") page: Int) : Call<MovieResponse>

    @GET("/genre/9648/movies")
    fun getMysteryMovie(@Query("api_key") api_key: String,
                        @Query("language") language: String,
                        @Query("sort_by") sort_by: String,
                        @Query("page") page: Int) : Call<MovieResponse>

    @GET("/genre/10794/movies")
    fun getRomanceMovie(@Query("api_key") api_key: String,
                        @Query("language") language: String,
                        @Query("sort_by") sort_by: String,
                        @Query("page") page: Int) : Call<MovieResponse>

    @GET("/genre/878/movies")
    fun getScienceMovie(@Query("api_key") api_key: String,
                        @Query("language") language: String,
                        @Query("sort_by") sort_by: String,
                        @Query("page") page: Int) : Call<MovieResponse>

    @GET("/genre/10770/movies")
    fun getTVMovie(@Query("api_key") api_key: String,
                   @Query("language") language: String,
                   @Query("sort_by") sort_by: String,
                   @Query("page") page: Int) : Call<MovieResponse>

    @GET("/genre/53/movies")
    fun getThrillerMovie(@Query("api_key") api_key: String,
                         @Query("language") language: String,
                         @Query("sort_by") sort_by: String,
                         @Query("page") page: Int) : Call<MovieResponse>

    @GET("/genre/10752/movies")
    fun getWarMovie(@Query("api_key") api_key: String,
                    @Query("language") language: String,
                    @Query("sort_by") sort_by: String,
                    @Query("page") page: Int) : Call<MovieResponse>

    @GET("/genre/37/movies")
    fun getWesternMovie(@Query("api_key") api_key: String,
                        @Query("language") language: String,
                        @Query("sort_by") sort_by: String,
                        @Query("page") page: Int) : Call<MovieResponse>

}
