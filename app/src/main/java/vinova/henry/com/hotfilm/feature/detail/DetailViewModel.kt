package vinova.henry.com.hotfilm.feature.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import vinova.henry.com.hotfilm.models.detail.MovieDetail
import vinova.henry.com.hotfilm.models.trailer.TrailerResponse
import vinova.henry.com.hotfilm.repo.DetailRepo

class DetailViewModel : ViewModel(){
    private var movieId: MutableLiveData<Int> = MutableLiveData()
    private val detailRepo = DetailRepo()

    var movieDetail : LiveData<MovieDetail>? = Transformations.switchMap(movieId) {
        detailRepo.getMovieDetail(it)
    }

    var trailer : LiveData<TrailerResponse> = Transformations.switchMap(movieId) {
        detailRepo.getMovieTrailers(it)
    }

    var relateMovie = Transformations.switchMap(movieId) {
        detailRepo.getRelateMovies(it, 1)
    }

    fun setMovieId(movieId: Int?){
        this.movieId.value = movieId
    }
}