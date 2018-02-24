package vinova.henry.com.hotfilm.server;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vinova.henry.com.hotfilm.models.MvResult;

public interface IMoviService {
    @GET("discover/movie?")
    Call<MvResult> getServerData(@Query("api_key") String api_key, @Query("language") String language, @Query("sort_by") String sort_by, @Query("page") int page);

}
