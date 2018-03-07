package vinova.henry.com.hotfilm.feature.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vinova.henry.com.hotfilm.R;
import vinova.henry.com.hotfilm.feature.detail.DetailActivity;
import vinova.henry.com.hotfilm.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private IOnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private Activity activity;
    private Context mContext;
    private List<Movie> movies;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    MovieAdapter(RecyclerView recyclerView, Context mContext, Activity activity) {
        this.activity = activity;
        this.mContext = mContext;
        movies = new ArrayList<>();

        final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return movies.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM){
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_movie_cardview, parent, false);
            return new ItemViewHolder(v);
        } else if (viewType == VIEW_TYPE_LOADING){
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(v);
        } else return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        if (holder instanceof ItemViewHolder){
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            String poster = "https://image.tmdb.org/t/p/w500/" + movies.get(position).getPosterPath();
            Picasso.with(mContext)
                    .load(poster)
                    .into(viewHolder.imBackdrop);
            viewHolder.tvTitle.setText(movies.get(position).getTitle());

            viewHolder.cvMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("Movie", (Serializable) movies.get(position));
                    mContext.startActivity(intent);
                }
            });
        } else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    void setOnLoadMoreListener(IOnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    void setLoaded() {
        isLoading = false;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.im_backdrop)
        ImageView imBackdrop;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.cv_movie)
        CardView cvMovie;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    class LoadingViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.progressBar1)
        ProgressBar progressBar;
        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
