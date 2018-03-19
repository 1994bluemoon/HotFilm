package vinova.henry.com.hotfilm.feature.detail;


import android.view.View;

import java.util.List;

import vinova.henry.com.hotfilm.models.Trailer;

public interface IDetailContract {
    interface IDetailView{
        void onClickItem(int position);

    }
    interface IDetailPresenter{
        String[] createSpinnerItem(List<Trailer> trailers);
    }
}
