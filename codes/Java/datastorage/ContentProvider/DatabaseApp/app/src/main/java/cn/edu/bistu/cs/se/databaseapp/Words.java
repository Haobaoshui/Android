package cn.edu.bistu.cs.se.databaseapp;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hbs on 2015-10-5.
 */
public class Words {

    public static final String AUTHORITY = "cn.edu.bistu.cs.se.wordsprovider";//URI授权者

    public Words() {
    }

    public static abstract class Word implements BaseColumns {
        public static final String TABLE_NAME="words";//表名
        public static final String COLUMN_NAME_WORD="word";//列：单词
        public static final String COLUMN_NAME_MEANING="meaning";//列：单词含义
        public static final String COLUMN_NAME_SAMPLE="sample";//列：单词示例

        //MIME类型
        public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
        public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
        public static final String MINE_ITEM = "vnd.bistu.cs.se.word";

        public static final String MINE_TYPE_SINGLE = MIME_ITEM_PREFIX + "/" + MINE_ITEM;
        public static final String MINE_TYPE_MULTIPLE = MIME_DIR_PREFIX + "/" + MINE_ITEM;

        public static final String PATH_SINGLE = "word/#";//单条数据的路径
        public static final String PATH_MULTIPLE = "word";//多条数据的路径

        //Content Uri
        public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
        public static final Uri  CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

    }
}
