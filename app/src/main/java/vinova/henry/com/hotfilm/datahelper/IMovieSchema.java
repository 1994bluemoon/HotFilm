package vinova.henry.com.hotfilm.datahelper;

import android.content.ContentValues;
import android.net.Uri;

import vinova.henry.com.hotfilm.models.Movie;

public interface IMovieSchema {

    String AUTHORITY = "vinova.henry.com.hotfilm.provider";
    String BASE_PATH = "movies";
    Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    String DATABASE_NAME = "moviedata";
    int DATABASE_VERSION = 1;
    String TABLE_NAME = "tb_movie";

    String COL_ID = "id";
    String COL_VOTE_COUNT = "vote_count";
    String COL_VOTE_AVERAGE = "vote_average";
    String COL_TITLE = "title";
    String COL_POSTER_PATH = "poster_path";
    String COL_BACKDROP_PATH = "backdrop_path";
    String COL_OVERVIEW = "overview";
    String COL_RELEASE_DAY = "release_date";

    String CREATE_MOVIE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME
            + " ("
            + COL_ID
            + " STRING PRIMARY KEY, "
            + COL_TITLE
            + " TEXT, "
            + COL_VOTE_AVERAGE
            + " TEXT, "
            + COL_VOTE_COUNT
            + " TEXT, "
            + COL_POSTER_PATH
            + " TEXT, "
            + COL_BACKDROP_PATH
            + " TEXT, "
            + COL_OVERVIEW
            + " TEXT, "
            + COL_RELEASE_DAY
            + " TEXT )";

    String[] MOVIE_COLUMN = new String[] {COL_ID, COL_TITLE, COL_VOTE_AVERAGE, COL_VOTE_COUNT, COL_POSTER_PATH, COL_BACKDROP_PATH, COL_OVERVIEW, COL_RELEASE_DAY};

}
