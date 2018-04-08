package vinova.henry.com.hotfilm.feature.detail


import vinova.henry.com.hotfilm.models.Trailer

interface IDetailContract {
    interface IDetailView {
        fun onClickItem(position: Int)

    }

    interface IDetailPresenter {
        fun createSpinnerItem(trailers: List<Trailer>): Array<String>
    }
}
