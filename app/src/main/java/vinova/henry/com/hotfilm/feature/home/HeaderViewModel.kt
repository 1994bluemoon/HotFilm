package vinova.henry.com.hotfilm.feature.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import vinova.henry.com.hotfilm.models.Header
import vinova.henry.com.hotfilm.repo.HeaderRepo

class HeaderViewModel : ViewModel(){
    private val headerRepo = HeaderRepo()
    var headers: MutableLiveData<List<Header>>?
    init {
        headers = headerRepo.getHeaders()
    }
}