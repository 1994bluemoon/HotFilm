package vinova.henry.com.hotfilm.dao

import vinova.henry.com.hotfilm.models.Movie

interface IDao {
    fun fetchAllMovie(): List<Movie>
    fun addMovie(movies: List<Movie>): Boolean
    fun clearDatabase(): Boolean

}
