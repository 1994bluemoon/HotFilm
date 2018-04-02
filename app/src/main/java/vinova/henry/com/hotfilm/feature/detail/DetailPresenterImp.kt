package vinova.henry.com.hotfilm.feature.detail

import java.util.ArrayList

import vinova.henry.com.hotfilm.models.Trailer

class DetailPresenterImp : IDetailContract.IDetailPresenter {
    override fun createSpinnerItem(trailers: List<Trailer>): Array<String> {

        var a = Array<String>(trailers.size){""}
        for (i in 0..trailers.size) {
            a[i] = trailers.get(i).name.toString()
        }
        return a
    }
}
