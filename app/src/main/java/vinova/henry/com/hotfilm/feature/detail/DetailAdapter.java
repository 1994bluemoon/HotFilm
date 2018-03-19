package vinova.henry.com.hotfilm.feature.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vinova.henry.com.hotfilm.R;
import vinova.henry.com.hotfilm.models.Trailer;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private List<Trailer> trailers;
    private Context mContext;
    private IDetailContract.IDetailView detailView;

    DetailAdapter(Context mContext, IDetailContract.IDetailView detailView) {
        trailers = new ArrayList<>();
        this.mContext = mContext;
        this.detailView = detailView;
    }

    void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_youtube_thumbnail, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvTitle.setText(trailers.get(position).getName());
        String thumbnailUrl = "https://img.youtube.com/vi/" + trailers.get(position).getKey() + "/0.jpg";
        Picasso.with(mContext)
                .load(thumbnailUrl)
                .into(holder.imThumbnail);

        holder.cvThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailView.onClickItem(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return trailers == null ? 0 : trailers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.im_thumbnail)
        ImageView imThumbnail;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.cv_thumbnail)
        CardView cvThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
