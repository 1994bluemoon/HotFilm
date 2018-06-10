package vinova.henry.com.hotfilm.interf

import vinova.henry.com.hotfilm.models.Movie

interface IMovieEvent{
    fun onItemRvClicked(movie: Movie?)
    fun onLoadMoreListener(currentPage: Int)
}