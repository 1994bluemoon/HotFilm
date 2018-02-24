package vinova.henry.com.hotfilm.feature.home;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vinova.henry.com.hotfilm.models.Movie;
import vinova.henry.com.hotfilm.models.MvResult;
import vinova.henry.com.hotfilm.server.ApiUtils;
import vinova.henry.com.hotfilm.server.IMoviService;

public class HomePresenterImp implements IHomeContract.IPresenter{

    private IHomeContract.IView iView;
    private List<Movie> movies;

    HomePresenterImp(IHomeContract.IView iView) {
        this.iView = iView;
        movies = new ArrayList<>();
    }

    List<Movie> getMovies() {
        return movies;
    }

    @Override
    public void getUserFromServer(int page) {
        IMoviService service = ApiUtils.getSOService();
        Call<MvResult> call = service.getServerData("54715436e1813692f121feb9fd97709e", "en-US", "popularity.desc", page);
        call.enqueue(new Callback<MvResult>() {
            @Override
            public void onResponse(@NonNull Call<MvResult> call, @NonNull Response<MvResult> response) {
                movies.addAll(response.body().getMovieList());
                iView.showSuccess();
            }

            @Override
            public void onFailure(@NonNull Call<MvResult> call, @NonNull Throwable t) {
                iView.showFail();
            }
        });
    }
}
