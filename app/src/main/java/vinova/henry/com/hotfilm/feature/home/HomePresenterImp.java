package vinova.henry.com.hotfilm.feature.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vinova.henry.com.hotfilm.datahelper.IMovieSchema;
import vinova.henry.com.hotfilm.models.Movie;
import vinova.henry.com.hotfilm.models.MvResult;
import vinova.henry.com.hotfilm.server.ApiUtils;
import vinova.henry.com.hotfilm.server.IMovieService;

public class HomePresenterImp implements IHomeContract.IPresenter, IMovieSchema{

    private IHomeContract.IView iView;
    private Context mContext;
    private List<Movie> movies;
    //private DatabaseMovie databaseMovie;

    HomePresenterImp(Context context, IHomeContract.IView iView) {
        this.iView = iView;
        this.mContext = context;
        movies = new ArrayList<>();
        //databaseMovie = new DatabaseMovie(mContext);
    }

    List<Movie> getMovies() {
        return movies;
    }

    @Override
    public void getUserFromServer(int page) {
        IMovieService service = ApiUtils.getSOService();
        Call<MvResult> call = service.getServerData("54715436e1813692f121feb9fd97709e", "en-US", "popularity.desc", page);
        call.enqueue(new Callback<MvResult>() {
            @Override
            public void onResponse(@NonNull Call<MvResult> call, @NonNull Response<MvResult> response) {
                movies = new ArrayList<>();
                movies.addAll(response.body().getMovieList());
                try {
                    iView.showSuccess();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MvResult> call, @NonNull Throwable t) {
                iView.showFail();
            }
        });
    }

    @Override
    public ContentValues getContentValue(Movie movie) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_ID, movie.getId());
        initialValues.put(COL_BACKDROP_PATH, movie.getBackdropPath());
        initialValues.put(COL_OVERVIEW, movie.getOverview());
        initialValues.put(COL_POSTER_PATH, movie.getPosterPath());
        initialValues.put(COL_RELEASE_DAY, movie.getReleaseDate());
        initialValues.put(COL_TITLE, movie.getTitle());
        initialValues.put(COL_VOTE_AVERAGE, movie.getVoteAverage());
        initialValues.put(COL_VOTE_COUNT, movie.getVoteCount());
        return initialValues;
    }

    @Override
    public Movie portMovie(Cursor cursor) {
        Movie movie = new Movie();
        movie.setBackdropPath(cursor.getString(cursor.getColumnIndex(COL_BACKDROP_PATH)));
        movie.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(COL_ID))));
        movie.setPosterPath(cursor.getString(cursor.getColumnIndex(COL_POSTER_PATH)));
        movie.setOverview(cursor.getString(cursor.getColumnIndex(COL_OVERVIEW)));
        movie.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
        movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(COL_RELEASE_DAY)));
        movie.setVoteCount(Integer.valueOf(cursor.getString(cursor.getColumnIndex(COL_VOTE_COUNT))));
        movie.setVoteAverage(Double.valueOf(cursor.getString(cursor.getColumnIndex(COL_VOTE_AVERAGE))));

        return movie;
    }
}
