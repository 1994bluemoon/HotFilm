package vinova.henry.com.hotfilm.feature.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import vinova.henry.com.hotfilm.repo.SearchRepo

class SearchViewModel : ViewModel(){

    val searchRepo = SearchRepo()
    var page: MutableLiveData<Int> = MutableLiveData()
    var query: MutableLiveData<String> = MutableLiveData()

    var movies = Transformations.switchMap(query, {
        searchRepo.getSearchResult(it, page.value ?: 1)
    })

    fun setQuery(query: String?){
        this.query.value = query
        this.page.value = 1
    }

    fun setPage(page: Int?){
        this.page.value = page
        movies = searchRepo.getSearchResult(this.query.value ?: "", this.page.value ?: 1)
    }
}