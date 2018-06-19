package vinova.henry.com.hotfilm.feature.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import vinova.henry.com.hotfilm.repo.SearchRepo

class SearchViewModel : ViewModel(){

    private val searchRepo = SearchRepo()
    private var page: MutableLiveData<Int> = MutableLiveData()
    private var query: MutableLiveData<String> = MutableLiveData()

    var moviesQueryChange = Transformations.switchMap(query) {
        searchRepo.getSearchResult(it, 1)
    }

    var moviesPageChange = Transformations.switchMap(page) {
        searchRepo.getSearchResult(this@SearchViewModel.query.value ?: "", it)
    }

    fun setQuery(query: String?){
        this.query.value = query
    }

    fun setPage(page: Int?){
        this.page.value = page
    }
}