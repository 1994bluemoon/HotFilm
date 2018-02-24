package vinova.henry.com.hotfilm.feature.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import vinova.henry.com.hotfilm.R;
import vinova.henry.com.hotfilm.adapter.MovieAdapter;
import vinova.henry.com.hotfilm.models.Movie;

public class HomeActivity extends AppCompatActivity implements IHomeContract.IView {

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
        homePresenterImp = new HomePresenterImp(this);
        homePresenterImp.getUserFromServer(1);

        adapter = new MovieAdapter(this);

        rvFilm.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        rvFilm.setHasFixedSize(true);
        rvFilm.setAdapter(adapter);

    }

    @Override
    public void showSuccess() {
        movies.addAll(homePresenterImp.getMovies());
        adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
        Log.d("getMovie", "OK");
    }

    @Override
    public void showFail() {
        Log.d("getMovie", "Failed");
    }
}
