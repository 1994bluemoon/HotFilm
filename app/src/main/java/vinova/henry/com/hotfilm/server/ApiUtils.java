package vinova.henry.com.hotfilm.server;

public class ApiUtils {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static IMoviService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(IMoviService.class);
    }
}
