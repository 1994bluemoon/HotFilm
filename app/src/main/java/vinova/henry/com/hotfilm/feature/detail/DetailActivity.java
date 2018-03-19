package vinova.henry.com.hotfilm.feature.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vinova.henry.com.hotfilm.R;
import vinova.henry.com.hotfilm.feature.trailer.YouTubeActivity;
import vinova.henry.com.hotfilm.models.Movie;
import vinova.henry.com.hotfilm.models.Trailer;
import vinova.henry.com.hotfilm.models.TrailerResult;
import vinova.henry.com.hotfilm.server.ApiUtils;
import vinova.henry.com.hotfilm.server.ITrailerService;

public class DetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, IDetailContract.IDetailView {

    Movie movie;
    boolean isClicked = false;
    List<Trailer> trailers;
    DetailPresenterImp detailPresenterImp;
    boolean isFirst = true;
    DetailAdapter detailAdapter;

    @BindView(R.id.im_backdrop)
    ImageView imBackdrop;
    @BindView(R.id.im_poster)
    ImageView imPoster;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    /*@BindView(R.id.spinner_trailer)
    Spinner spinnerTrailer;*/
    @BindView(R.id.rv_thumbnail)
    RecyclerView rvThumbnail;
    @BindView(R.id.bt_showtrailer)
    Button btShowtrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        trailers = new ArrayList<>();
        detailPresenterImp = new DetailPresenterImp();

        Bundle ex = getIntent().getExtras();
        if (ex != null) {
            movie = (Movie) ex.get("Movie");
        }

        assert movie != null;
        String posterUri = "https://image.tmdb.org/t/p/w500/" + movie.getPosterPath();
        Picasso.with(this).load(posterUri).into(imPoster);

        String backdropUri = "https://image.tmdb.org/t/p/w500/" + movie.getBackdropPath();
        Picasso.with(this).load(backdropUri).into(imBackdrop);

        tvTitle.setText(movie.getTitle());
        tvDetail.setText(movie.getOverview());

        //spinnerTrailer.setOnItemSelectedListener(this);
        detailAdapter = new DetailAdapter(DetailActivity.this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvThumbnail.setLayoutManager(linearLayoutManager);
        rvThumbnail.setHasFixedSize(true);
        rvThumbnail.setAdapter(detailAdapter);

        ITrailerService service = ApiUtils.getTrailerService(movie.getId());
        Call<TrailerResult> call = service.getTrailerData(ApiUtils.getApiKey());
        call.enqueue(new Callback<TrailerResult>() {
            @Override
            public void onResponse(@NonNull Call<TrailerResult> call, @NonNull Response<TrailerResult> response) {
                //TODO
                trailers.addAll(response.body().getTrailerList());

                /*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailActivity.this, R.layout.support_simple_spinner_dropdown_item, detailPresenterImp.createSpinnerItem(trailers));
                arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerTrailer.setAdapter(arrayAdapter);*/

                detailAdapter.setTrailers(trailers);
                detailAdapter.notifyDataSetChanged();
                btShowtrailer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(@NonNull Call<TrailerResult> call, @NonNull Throwable t) {
                //TODO
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (isFirst) {
            isFirst = false;
        } else {
            Log.d("selected", trailers.get(position).getName());
            Intent intent = new Intent(this, YouTubeActivity.class);
            intent.putExtra("Trailer", trailers.get(position));
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (trailers.size() == 1) {
            Intent intent = new Intent(this, YouTubeActivity.class);
            intent.putExtra("Trailer", trailers.get(0));
            startActivity(intent);
        }
    }

    @OnClick(R.id.bt_showtrailer)
    public void onViewClicked() {
        if (isClicked){
            isClicked = false;
            rvThumbnail.setVisibility(View.GONE);
        } else if (!isClicked){
            isClicked = true;
            rvThumbnail.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClickItem(int position) {
        Log.d("selected", trailers.get(position).getName());
        Intent intent = new Intent(this, YouTubeActivity.class);
        intent.putExtra("Trailer", trailers.get(position));
        startActivity(intent);
    }
}
