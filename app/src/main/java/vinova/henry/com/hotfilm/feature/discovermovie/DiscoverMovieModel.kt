package vinova.henry.com.hotfilm.feature.discovermovie

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import vinova.henry.com.hotfilm.repo.MovieRepo

class DiscoverMovieModel : ViewModel(){

    val discoverMovieRepo = MovieRepo()
    var page: MutableLiveData<Int> = MutableLiveData()
    var movies = Transformations.switchMap(page, {
        discoverMovieRepo.getDescoverMovie(it)
    })

    init {
        page.value = 1
    }

    fun setPage(page: Int){
        this.page.value = page
    }
}