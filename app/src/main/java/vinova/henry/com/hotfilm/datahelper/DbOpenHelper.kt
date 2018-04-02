package vinova.henry.com.hotfilm.datahelper

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbOpenHelper internal constructor(context: Context) : SQLiteOpenHelper(context, IMovieSchema.DATABASE_NAME, null, IMovieSchema.DATABASE_VERSION) {

    @SuppressLint("SQLiteString")
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(IMovieSchema.CREATE_MOVIE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w("MyDatabase", "Upgrading database from version "
                + oldVersion + " to "
                + newVersion + " which destroys all old data")

        db.execSQL("DROP TABLE IF EXISTS " + IMovieSchema.TABLE_NAME)
        onCreate(db)
    }
}
