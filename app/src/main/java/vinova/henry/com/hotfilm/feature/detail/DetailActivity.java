package vinova.henry.com.hotfilm.feature.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import vinova.henry.com.hotfilm.R;
import vinova.henry.com.hotfilm.models.Movie;

public class DetailActivity extends AppCompatActivity {

    Movie movie;

    @BindView(R.id.im_backdrop)
    ImageView imBackdrop;
    @BindView(R.id.im_poster)
    ImageView imPoster;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_detail)
    TextView tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

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

    }
}
