package vinova.henry.com.hotfilm.feature.home;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vinova.henry.com.hotfilm.R;
import vinova.henry.com.hotfilm.datahelper.IMovieSchema;
import vinova.henry.com.hotfilm.models.Movie;

public class HomeActivity extends AppCompatActivity implements IHomeContract.IView, IMovieSchema {

    int mPage;

    HomePresenterImp homePresenterImp;
    List<Movie> movies;
    MovieAdapter adapter;
    @BindView(R.id.rv_film)
    RecyclerView rvFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        movies = new ArrayList<>();
        homePresenterImp = new HomePresenterImp(this, this);
        mPage = 1;
        homePresenterImp.getUserFromServer(mPage);

        rvFilm.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        rvFilm.setHasFixedSize(true);
        adapter = new MovieAdapter(rvFilm,this);
        rvFilm.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new IOnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                movies.add(null);
                adapter.notifyItemInserted(movies.size() - 1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        movies.remove(movies.size() - 1);
                        adapter.notifyItemRemoved(movies.size());
                        mPage += 1;
                        homePresenterImp.getUserFromServer(mPage);
                        Log.d("page ", String.valueOf(mPage));
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void showSuccess() throws SQLException {
        movies.addAll(homePresenterImp.getMovies());
        getContentResolver().delete(CONTENT_URI, null, null);
        for (Movie m: homePresenterImp.getMovies()
             ) {
            getContentResolver().insert(CONTENT_URI, homePresenterImp.getContentValue(m));
            Log.d("save child offline", "item");
        }
        adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
        adapter.setLoaded();
        Log.d("getMovie from server", "OK");
    }

    @Override
    public void showFail() {

        movies = new ArrayList<>();

        Cursor cursor = getContentResolver().query(CONTENT_URI,MOVIE_COLUMN, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                movies.add(homePresenterImp.portMovie(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
        if (movies != null) adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
        Log.d("getMovie frome server", "Failed");
        Log.d("get offline data", "OK");
    }

    @Override
    public void showLoadDataOfflineSuccess() {
        //TODO
    }
}
