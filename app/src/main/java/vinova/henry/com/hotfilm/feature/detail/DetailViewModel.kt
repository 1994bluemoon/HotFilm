package vinova.henry.com.hotfilm.feature.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import vinova.henry.com.hotfilm.repo.DetailRepo

class DetailViewModel : ViewModel(){
    var movieId: MutableLiveData<Int> = MutableLiveData()
    val detailRepo = DetailRepo()

    var movieDetail = Transformations.switchMap(movieId, {
        detailRepo.getMovieDetail(it)
    })

    var trailer = Transformations.switchMap(movieId, {
        detailRepo.getMovieTrailers(it)
    })

    fun setMovieId(movieId: Int?){
        this.movieId.value = movieId
    }
}