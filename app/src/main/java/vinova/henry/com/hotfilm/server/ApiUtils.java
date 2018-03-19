package vinova.henry.com.hotfilm.server;

import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "54715436e1813692f121feb9fd97709e";

    public static IMovieService getSOService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IMovieService.class);
    }

    public static ITrailerService getTrailerService(long movieId){
        String url = BASE_URL + "movie/" + movieId + "/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ITrailerService.class);
    }
    public static String getApiKey(){
        return API_KEY;
    }
}
