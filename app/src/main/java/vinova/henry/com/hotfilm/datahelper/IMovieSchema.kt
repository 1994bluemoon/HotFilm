package vinova.henry.com.hotfilm.datahelper

import android.net.Uri

interface IMovieSchema {
    companion object {

        val AUTHORITY = "vinova.henry.com.hotfilm.provider"
        val BASE_PATH = "movies"
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$BASE_PATH")

        val DATABASE_NAME = "moviedata"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "tb_movie"

        val COL_ID = "id"
        val COL_VOTE_COUNT = "vote_count"
        val COL_VOTE_AVERAGE = "vote_average"
        val COL_TITLE = "title"
        val COL_POSTER_PATH = "poster_path"
        val COL_BACKDROP_PATH = "backdrop_path"
        val COL_OVERVIEW = "overview"
        val COL_RELEASE_DAY = "release_date"

        val CREATE_MOVIE_TABLE = ("CREATE TABLE IF NOT EXISTS "
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
                + " TEXT )")

        val MOVIE_COLUMN = arrayOf(COL_ID, COL_TITLE, COL_VOTE_AVERAGE, COL_VOTE_COUNT, COL_POSTER_PATH, COL_BACKDROP_PATH, COL_OVERVIEW, COL_RELEASE_DAY)
    }

}
