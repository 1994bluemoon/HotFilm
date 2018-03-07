/*
package vinova.henry.com.hotfilm.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vinova.henry.com.hotfilm.datahelper.DbContentProvider;
import vinova.henry.com.hotfilm.datahelper.IMovieSchema;
import vinova.henry.com.hotfilm.models.Movie;

@SuppressLint("Registered")
public class MovieDaoImp implements IDao, IMovieSchema{
    public MovieDaoImp(Context context) {
    }

    @Override
    public List<Movie> fetchAllMovie() {
        List<Movie> movies = new ArrayList<>();
        Cursor cursor = super.query(CONTENT_URI, MOVIE_COLUMN, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                movies.add(portMovie(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
        Log.d("get movie offline", "ok");

        return movies;
    }

    @Override
    public boolean addMovie(List<Movie> movies) {
        //TODO
        for (Movie movie: movies
             ) {
            super.insert(CONTENT_URI,getContentValue(movie));
        }
        return true;
    }

    @Override
    public boolean clearDatabase() {
        super.delete(CONTENT_URI,null,null);
        return true;
    }

    private Movie portMovie(Cursor cursor){
        Movie movie = new Movie();
        movie.setBackdropPath(cursor.getString(cursor.getColumnIndex(COL_BACKDROP_PATH)));
        movie.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(COL_ID))));
        movie.setPosterPath(cursor.getString(cursor.getColumnIndex(COL_POSTER_PATH)));
        movie.setOverview(cursor.getString(cursor.getColumnIndex(COL_OVERVIEW)));
        movie.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
        movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(COL_RELEASE_DAY)));
        movie.setVoteCount(Integer.valueOf(cursor.getString(cursor.getColumnIndex(COL_VOTE_COUNT))));
        movie.setVoteAverage(Double.valueOf(cursor.getString(cursor.getColumnIndex(COL_VOTE_AVERAGE))));

        return movie;
    }

    private ContentValues getContentValue(Movie movie) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_ID, movie.getId());
        initialValues.put(COL_BACKDROP_PATH, movie.getBackdropPath());
        initialValues.put(COL_OVERVIEW, movie.getOverview());
        initialValues.put(COL_POSTER_PATH, movie.getPosterPath());
        initialValues.put(COL_RELEASE_DAY, movie.getReleaseDate());
        initialValues.put(COL_TITLE, movie.getTitle());
        initialValues.put(COL_VOTE_AVERAGE, movie.getVoteAverage());
        initialValues.put(COL_VOTE_COUNT, movie.getVoteCount());
        return initialValues;
    }
}
*/
