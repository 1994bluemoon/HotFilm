package vinova.henry.com.hotfilm.server;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vinova.henry.com.hotfilm.models.TrailerResult;

public interface ITrailerService {
    @GET("videos?")
    Call<TrailerResult> getTrailerData(@Query("api_key") String api_key);
}
