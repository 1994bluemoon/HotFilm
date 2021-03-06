package vinova.henry.com.hotfilm.feature.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import vinova.henry.com.hotfilm.*
import vinova.henry.com.hotfilm.models.MovieResponse
import vinova.henry.com.hotfilm.repo.MovieRepo

class HomeViewModel : ViewModel(){
    private val movieRepo = MovieRepo()

    private var pageAction: MutableLiveData<Int> = MutableLiveData()
    private var pageAdventure: MutableLiveData<Int> = MutableLiveData()
    private var pageAnimation: MutableLiveData<Int> = MutableLiveData()
    private var pageCrime: MutableLiveData<Int> = MutableLiveData()
    private var pageDocumentary: MutableLiveData<Int> = MutableLiveData()
    private var pageDrama: MutableLiveData<Int> = MutableLiveData()
    private var pageFamily: MutableLiveData<Int> = MutableLiveData()
    private var pageFantasy: MutableLiveData<Int> = MutableLiveData()
    private var pageHistory: MutableLiveData<Int> = MutableLiveData()
    private var pageHorror: MutableLiveData<Int> = MutableLiveData()
    private var pageMusic: MutableLiveData<Int> = MutableLiveData()
    private var pageMystery: MutableLiveData<Int> = MutableLiveData()
    private var pageRomance: MutableLiveData<Int> = MutableLiveData()
    private var pageScienceFiction: MutableLiveData<Int> = MutableLiveData()
    private var pageThriller: MutableLiveData<Int> = MutableLiveData()
    private var pageTVMovie: MutableLiveData<Int> = MutableLiveData()
    private var pageWar: MutableLiveData<Int> = MutableLiveData()
    private var pageWestern: MutableLiveData<Int> = MutableLiveData()

    var actionMovies : LiveData<MovieResponse>? = Transformations.switchMap(pageAction) {
        movieRepo.getMovieByGenre(it, GENRE_ACTION.id)
    }
    var adventureMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageAdventure) {
        movieRepo.getMovieByGenre(it, GENRE_ADVENTURE.id)
    }
    var animationMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageAnimation) {
        movieRepo.getMovieByGenre(it, GENRE_ANIMATION.id)
    }
    var crimeMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageCrime) {
        movieRepo.getMovieByGenre(it, GENRE_CRIME.id)
    }
    var documentaryMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageDocumentary) {
        movieRepo.getMovieByGenre(it, GENRE_DOCUMENTARY.id)
    }
    var dramaMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageDrama) {
        movieRepo.getMovieByGenre(it, GENRE_DRAMA.id)
    }
    var familyMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageFamily) {
        movieRepo.getMovieByGenre(it, GENRE_FAMILY.id)
    }
    var fantasyMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageFantasy) {
        movieRepo.getMovieByGenre(it, GENRE_FANTASY.id)
    }
    var historyMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageHistory) {
        movieRepo.getMovieByGenre(it, GENRE_HISTORY.id)
    }
    var horrorMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageHorror) {
        movieRepo.getMovieByGenre(it, GENRE_HORROR.id)
    }
    var musicMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageMusic) {
        movieRepo.getMovieByGenre(it, GENRE_MUSIC.id)
    }
    var mysteryMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageMystery) {
        movieRepo.getMovieByGenre(it, GENRE_MYSTERY.id)
    }
    var romanceMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageRomance) {
        movieRepo.getMovieByGenre(it, GENRE_ROMANCE.id)
    }
    var scienceFictionMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageScienceFiction) {
        movieRepo.getMovieByGenre(it, GENRE_SCIENCE_FICTION.id)
    }
    var tvMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageTVMovie) {
        movieRepo.getMovieByGenre(it, GENRE_TV_MOVIE.id)
    }
    var thrillerMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageThriller) {
        movieRepo.getMovieByGenre(it, GENRE_THRILLER.id)
    }
    var warMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageWar) {
        movieRepo.getMovieByGenre(it, GENRE_WAR.id)
    }
    var westernMovies : LiveData<MovieResponse>?  = Transformations.switchMap(pageWestern) {
        movieRepo.getMovieByGenre(it, GENRE_WESTERN.id)
    }

    init {
        pageAction.value = 1
        pageAdventure.value = 1
        pageAnimation.value = 1
        pageCrime.value = 1
        pageDocumentary.value = 1
        pageDrama.value = 1
        pageFamily.value = 1
        pageFantasy.value = 1
        pageHistory.value = 1
        pageHorror.value = 1
        pageMusic.value = 1
        pageMystery.value = 1
        pageRomance.value = 1
        pageScienceFiction.value = 1
        pageThriller.value = 1
        pageTVMovie.value = 1
        pageWar.value = 1
        pageWestern.value = 1
    }

    fun setPageAction(page: Int){
        this.pageAction.value = page
    }
    fun setPageAdventure(page: Int){
        this.pageAdventure.value = page
    }
    fun setPageAnimation(page: Int){
        this.pageAnimation.value = page
    }
    fun setPageCrime(page: Int){
        this.pageCrime.value = page
    }
    fun setPageDocumentary(page: Int){
        this.pageDocumentary.value = page
    }
    fun setPageDrama(page: Int){
        this.pageDrama.value = page
    }
    fun setPageFamily(page: Int){
        this.pageFamily.value = page
    }
    fun setPageFantasy(page: Int){
        this.pageFantasy.value = page
    }
    fun setPageHistory(page: Int){
        this.pageHistory.value = page
    }
    fun setPageHorror(page: Int){
        this.pageHorror.value = page
    }
    fun setPageMusic(page: Int){
        this.pageMusic.value = page
    }
    fun setPageMystery(page: Int){
        this.pageMystery.value = page
    }
    fun setPageRomance(page: Int){
        this.pageRomance.value = page
    }
    fun setPageScienceFiction(page: Int){
        this.pageScienceFiction.value = page
    }
    fun setPageTVMovie(page: Int){
        this.pageTVMovie.value = page
    }
    fun setPageThriller(page: Int){
        this.pageThriller.value = page
    }
    fun setPageWar(page: Int){
        this.pageWar.value = page
    }
    fun setPageWestern(page: Int){
        this.pageWestern.value = page
    }
}



/*var actionMovies = Transformations.switchMap(pageAction, {
        movieRepo.getActionMovie(it)
    })
    var adventureMovies = Transformations.switchMap(pageAdventure, {
        movieRepo.getAdventureMovie(it)
    })
    var animationMovies = Transformations.switchMap(pageAnimation, {
        movieRepo.getAnimationMovie(it)
    })
    var crimeMovies = Transformations.switchMap(pageCrime, {
        movieRepo.getCrimeMovie(it)
    })
    var documentaryMovies = Transformations.switchMap(pageDocumentary, {
        movieRepo.getDocumentaryMovie(it)
    })
    var dramaMovies = Transformations.switchMap(pageDrama, {
        movieRepo.getDramaMovie(it)
    })
    var familyMovies = Transformations.switchMap(pageFamily, {
        movieRepo.getFamilyMovie(it)
    })
    var fantasyMovies = Transformations.switchMap(pageFantasy, {
        movieRepo.getFantasyMovie(it)
    })
    var historyMovies = Transformations.switchMap(pageHistory, {
        movieRepo.getHistoryMovie(it)
    })
    var horrorMovies = Transformations.switchMap(pageHorror, {
        movieRepo.getHorrorMovie(it)
    })
    var musicMovies = Transformations.switchMap(pageMusic, {
        movieRepo.getMusicMovie(it)
    })
    var mysteryMovies = Transformations.switchMap(pageMystery, {
        movieRepo.getMysteryMovie(it)
    })
    var romanceMovies = Transformations.switchMap(pageRomance, {
        movieRepo.getRomanceMovie(it)
    })
    var scienceFictionMovies = Transformations.switchMap(pageScienceFiction, {
        movieRepo.getScienceMovie(it)
    })
    var tvMovies = Transformations.switchMap(pageTVMovie, {
        movieRepo.getTVMovie(it)
    })
    var thrillerMovies = Transformations.switchMap(pageThriller, {
        movieRepo.getThrillerMovie(it)
    })
    var warMovies = Transformations.switchMap(pageWar, {
        movieRepo.getWarMovie(it)
    })
    var westernMovies = Transformations.switchMap(pageWestern, {
        movieRepo.getWesternMovie(it)
    })*/