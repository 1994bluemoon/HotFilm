package vinova.henry.com.hotfilm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vinova.henry.com.hotfilm.R;
import vinova.henry.com.hotfilm.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context mContext;
    List<Movie> movies;

    public MovieAdapter(Context mContext) {
        this.mContext = mContext;
        movies = new ArrayList<>();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_movie_cardview, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String poster = String.format("https://image.tmdb.org/t/p/w500/" + movies.get(position).getPosterPath());
        Picasso.with(mContext)
                .load(poster)
                .into(holder.imBackdrop);
        holder.tvTitle.setText(movies.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.im_backdrop)
        ImageView imBackdrop;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
