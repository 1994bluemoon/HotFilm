package vinova.henry.com.hotfilm.dao

import vinova.henry.com.hotfilm.models.MovieBase

interface IDao {
    fun fetchAllMovie(): List<MovieBase>
    fun addMovie(movies: List<MovieBase>): Boolean
    fun clearDatabase(): Boolean

}
