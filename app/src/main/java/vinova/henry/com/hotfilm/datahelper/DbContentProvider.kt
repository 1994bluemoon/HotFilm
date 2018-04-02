package vinova.henry.com.hotfilm.datahelper

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class DbContentProvider : ContentProvider(), IMovieSchema {

    internal lateinit var sqLiteDatabase: SQLiteDatabase

    override fun onCreate(): Boolean {
        val dbHelper = DbOpenHelper(this.context)
        sqLiteDatabase = dbHelper.readableDatabase
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val cursor: Cursor
        when (uriMatcher.match(uri)) {
            MOVIE -> cursor = sqLiteDatabase.query(IMovieSchema.TABLE_NAME, projection, selection, selectionArgs, sortOrder, null, null)
            else -> throw IllegalArgumentException("This is an Unknown URI $uri")
        }
        cursor.setNotificationUri(context!!.contentResolver, uri)

        return cursor
    }

    override fun getType(uri: Uri): String? {
        //TODO
        /*switch (uriMatcher.match(uri)) {
            case MOVIE:
                return "vnd.android.cursor.dir/contacts";
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }*/
        return ""
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = sqLiteDatabase.insert(IMovieSchema.TABLE_NAME, null, values)

        if (id > 0) {
            val _uri = ContentUris.withAppendedId(IMovieSchema.CONTENT_URI, id)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }
        throw SQLException("Insertion Failed for URI :$uri")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var delCount = 0
        when (uriMatcher.match(uri)) {
            MOVIE -> delCount = sqLiteDatabase.delete(IMovieSchema.TABLE_NAME, selection, selectionArgs)
            else -> throw IllegalArgumentException("This is an Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return delCount
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val updCount: Int
        when (uriMatcher.match(uri)) {
            MOVIE -> updCount = sqLiteDatabase.update(IMovieSchema.TABLE_NAME, values, selection, selectionArgs)
            else -> throw IllegalArgumentException("This is an Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return updCount
    }

    companion object {

        private val MOVIE = 1
        private val MOVIE_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(IMovieSchema.AUTHORITY, IMovieSchema.BASE_PATH, MOVIE)
            uriMatcher.addURI(IMovieSchema.AUTHORITY, IMovieSchema.BASE_PATH + "/#", MOVIE_ID)
        }
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
