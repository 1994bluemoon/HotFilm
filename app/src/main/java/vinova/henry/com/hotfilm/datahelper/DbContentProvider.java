package vinova.henry.com.hotfilm.datahelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DbContentProvider extends ContentProvider implements IMovieSchema {

    private static final int  MOVIE = 1;
    private static final int MOVIE_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY,BASE_PATH, MOVIE);
        uriMatcher.addURI(AUTHORITY,BASE_PATH + "/#", MOVIE_ID);
    }

    SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {
        DbOpenHelper dbHelper = new DbOpenHelper(this.getContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                cursor =  sqLiteDatabase.query(IMovieSchema.TABLE_NAME,projection, selection,selectionArgs,sortOrder,null, null);
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        //TODO
        /*switch (uriMatcher.match(uri)) {
            case MOVIE:
                return "vnd.android.cursor.dir/contacts";
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }*/
        return "";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id = sqLiteDatabase.insert(IMovieSchema.TABLE_NAME, null, values);

        if (id > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Insertion Failed for URI :" + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int delCount = 0;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                delCount =  sqLiteDatabase.delete(IMovieSchema.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return delCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updCount;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                updCount =  sqLiteDatabase.update(IMovieSchema.TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updCount;
    }

    /*private SQLiteDatabase mDb;

    public DbContentProvider(SQLiteDatabase mDb) {
        this.mDb = mDb;
    }

    public int delete(String tableName, String selection,
                      String[] selectionArgs) {
        return 0;
    }
    long insert(String tableName, ContentValues values) {
        return mDb.insert(tableName, null, values);
    }
    protected abstract <T> T cursorToEntity(Cursor cursor);

    Cursor query(String tableName, String[] columns,
                 String selection, String[] selectionArgs, String sortOrder) {

        final Cursor cursor = mDb.query(tableName, columns,
                selection, selectionArgs, null, null, sortOrder);

        return cursor;
    }
    public Cursor query(String tableName, String[] columns, String selection, String[] selectionArgs, String sortOrder,
    String limit) {

        return mDb.query(tableName, columns, selection,
                selectionArgs, null, null, sortOrder, limit);
    }

    public Cursor query(String tableName, String[] columns,
                        String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy, String limit) {

        return mDb.query(tableName, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit);
    }

    public int update(String tableName, ContentValues values,
                      String selection, String[] selectionArgs) {
        return mDb.update(tableName, values, selection,
                selectionArgs);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        return mDb.rawQuery(sql, selectionArgs);
    }*/
}
