package vinova.henry.com.hotfilm.datahelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbOpenHelper extends SQLiteOpenHelper{

    DbOpenHelper(Context context) {
        super(context, IMovieSchema.DATABASE_NAME, null, IMovieSchema.DATABASE_VERSION);
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(IMovieSchema.CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("MyDatabase", "Upgrading database from version "
                + oldVersion + " to "
                + newVersion + " which destroys all old data");

        db.execSQL("DROP TABLE IF EXISTS "
                + IMovieSchema.TABLE_NAME);
        onCreate(db);
    }
}
