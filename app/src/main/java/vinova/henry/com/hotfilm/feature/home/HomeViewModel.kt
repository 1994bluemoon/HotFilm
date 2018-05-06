package vinova.henry.com.hotfilm.feature.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import vinova.henry.com.hotfilm.models.Movie
import vinova.henry.com.hotfilm.repo.DiscoverMovieRepo

class HomeViewModel : ViewModel(){

    val discoverMovieRepo = DiscoverMovieRepo()
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