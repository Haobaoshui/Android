package cn.edu.bistu.cs.se.databaseapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;



public class WordsProvider extends ContentProvider {


    //UriMathcher匹配结果码
    private static final int MULTIPLE_WORDS = 1;//
    private static final int SINGLE_WORD = 2;


    WordsDBHelper mDbHelper;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(Words.AUTHORITY, Words.Word.PATH_SINGLE, SINGLE_WORD);
        uriMatcher.addURI(Words.AUTHORITY, Words.Word.PATH_MULTIPLE, MULTIPLE_WORDS);
    }

    public WordsProvider() {
    }

    //删除数据
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        int count  = 0;
        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS:
                count = db.delete(Words.Word.TABLE_NAME, selection, selectionArgs);
                break;
            case SINGLE_WORD:
                String whereClause=Words.Word._ID+"="+uri.getPathSegments().get(1);
                count = db.delete(Words.Word.TABLE_NAME, whereClause, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }

        //通知ContentResolver,数据已经发生改变
        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    //返回Uri对应的数据的MIME类型
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS://多条数据记录
                return Words.Word.MINE_TYPE_MULTIPLE;
            case SINGLE_WORD://单条数据记录
                return Words.Word.MINE_TYPE_SINGLE;
            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        long id = db.insert(Words.Word.TABLE_NAME, null, values);
        if ( id > 0 ){
            //在已有的Uri后面添加id
            Uri newUri = ContentUris.withAppendedId(Words.Word.CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Failed to insert row into " + uri);

    }

    @Override
    public boolean onCreate() {
        //创建SQLiteOpenHelper对象
        mDbHelper = new WordsDBHelper(this.getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(Words.Word.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS:
                return db.query(Words.Word.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

            case SINGLE_WORD:
                qb.appendWhere(Words.Word._ID + "=" + uri.getPathSegments().get(1));
                return qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);

            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS:
                count = db.update(Words.Word.TABLE_NAME, values, selection, selectionArgs);
                break;
            case SINGLE_WORD:
                String segment = uri.getPathSegments().get(1);
                count = db.update(Words.Word.TABLE_NAME, values, Words.Word._ID+"="+segment, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }

        //通知ContentResolver,数据已经发生改变
        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }
}
