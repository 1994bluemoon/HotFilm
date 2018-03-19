package vinova.henry.com.hotfilm.feature.detail;

import java.util.ArrayList;
import java.util.List;

import vinova.henry.com.hotfilm.models.Trailer;

public class DetailPresenterImp implements IDetailContract.IDetailPresenter{
    @Override
    public String[] createSpinnerItem(List<Trailer> trailers) {

        String[] a = new String[trailers.size()];
        for (int i = 0; i < trailers.size(); i++){
            a[i] = trailers.get(i).getName();
        }
        return a;
    }
}
