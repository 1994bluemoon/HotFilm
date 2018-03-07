package vinova.henry.com.hotfilm.dao;

import java.util.List;

import vinova.henry.com.hotfilm.models.Movie;

public interface IDao {
    List<Movie> fetchAllMovie();
    boolean addMovie(List<Movie> movies);
    boolean clearDatabase();

}
