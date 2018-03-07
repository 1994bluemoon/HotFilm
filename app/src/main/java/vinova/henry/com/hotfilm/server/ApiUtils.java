package vinova.henry.com.hotfilm.server;

public class ApiUtils {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static IMovieService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(IMovieService.class);
    }
}
