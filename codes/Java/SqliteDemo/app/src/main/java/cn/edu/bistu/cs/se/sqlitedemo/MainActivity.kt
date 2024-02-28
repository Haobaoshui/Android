package cn.edu.bistu.cs.se.sqlitedemo

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cn.edu.bistu.cs.se.sqlitedemo.database.WordContract
import cn.edu.bistu.cs.se.sqlitedemo.database.WordDBHelper
import cn.edu.bistu.cs.se.sqlitedemo.ui.theme.SqliteDemoTheme
import timber.log.Timber
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    private val instance by lazy{this}//延迟加载

    private val dbHelper = WordDBHelper(instance)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SqliteDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    sqlButtons()
                }
            }
        }
    }



    //增
    private fun insertWord() {
        // 数据库
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(WordContract.WordEntry.COLUMN_NAME_WORD, "dog")
            put(WordContract.WordEntry.COLUMN_NAME_MEANING,"狗")
            put(WordContract.WordEntry.COLUMN_NAME_SAMPLE,"I like dogs")
        }




        //增加新的一条记录，返回新增记录的主键
        val newRowId = db?.insert(WordContract.WordEntry.TABLE_NAME, null, values)
        Timber.i("$newRowId")
    }

    private fun insertWordBySql(){
        val db = dbHelper.writableDatabase

        val word="cat"
        val meaning="猫"
        val sample="I like cats"

        val sql="""
            insert into  t_word(word,meaning,sample) 
            values ('$word','$meaning','$sample')
        """

        db.execSQL(sql)

        Timber.i("$word-$meaning")


    }

    //删
    private fun deleteWord() {
        val db = dbHelper.writableDatabase
        //定义where子句
        val selection = "${WordContract.WordEntry.COLUMN_NAME_WORD} LIKE ?"

        val selectionArgs = arrayOf("cat")

        val deletedRows = db.delete(WordContract.WordEntry.TABLE_NAME, selection, selectionArgs)
    }

    private fun deleteWordBySql() {
        val db = dbHelper.writableDatabase

        val word="dog"


        val sql="""
            delete from t_word 
           where word=$word
        """

        db.execSQL(sql)

        Timber.i("$word")
    }

    //改
    private fun updateWord(){
        val db = dbHelper.writableDatabase


        val word = "sheepdog"
        val values = ContentValues().apply {
            put(WordContract.WordEntry.COLUMN_NAME_WORD, word)
        }


        val selection = "${WordContract.WordEntry.COLUMN_NAME_WORD} LIKE ?"
        val selectionArgs = arrayOf("dog")
        val count = db.update(
            WordContract.WordEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs)
    }
    private fun updateWordBySql(){
        val db = dbHelper.writableDatabase

        val newWord="dog"
        val oldword="sheepdog"


        val sql="""
            update t_word set 
                word='$newWord'
            where word='$oldword'
        """

        db.execSQL(sql)


    }

    //查
    private fun queryWord(){
        val db = dbHelper.readableDatabase


        //定义结果集字段"select id,word,meaning,sample"
        val projection = arrayOf(BaseColumns._ID,
            WordContract.WordEntry.COLUMN_NAME_WORD,
            WordContract.WordEntry.COLUMN_NAME_MEANING,
            WordContract.WordEntry.COLUMN_NAME_SAMPLE)


        //条件WHERE "word" = 'dog'
        val selection = "${ WordContract.WordEntry.COLUMN_NAME_WORD} = ?"
        val selectionArgs = arrayOf("dog")


        //结果集中的记录次序： "order by word DESC"
        val sortOrder = "${WordContract.WordEntry.COLUMN_NAME_WORD} DESC"

        val cursor = db.query(
            WordContract.WordEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,           // don't group the rows
            null,            // don't filter by row groups
            sortOrder               // The sort order
        )


        with(cursor) {

            Timber.i("$count")
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val word=getString(getColumnIndexOrThrow(WordContract.WordEntry.COLUMN_NAME_WORD))
                Timber.i("$itemId-$word")
            }
        }
        cursor.close()

        
    }

    private fun queryWordBySql(){
        val db = dbHelper.readableDatabase

        val word="d"


        val sql="""
            select * from t_word where word like ? order by word desc
        """

        val cursor = db.rawQuery(sql, arrayOf("%$word%"))

        with(cursor) {

            Timber.i("$count")
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val word=getString(getColumnIndexOrThrow(WordContract.WordEntry.COLUMN_NAME_WORD))
                Timber.i("$itemId-$word")
            }
        }
        cursor.close()

    }

    @Composable
    fun sqlButtons(modifier: Modifier = Modifier) {

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { insertWord() }) {
                Text(stringResource(id = R.string.db_insert_method))
            }
            Button(onClick = { deleteWord() }) {
                Text(stringResource(id = R.string.db_delete_method))
            }
            Button(onClick = { updateWord() }) {
                Text(stringResource(id = R.string.db_update_method))
            }
            Button(onClick = { queryWord() }) {
                Text(stringResource(id = R.string.db_query_method))
            }

            Button(onClick = { insertWordBySql() }) {
                Text(stringResource(id = R.string.db_insert_sql))
            }
            Button(onClick = { deleteWordBySql() }) {
                Text(stringResource(id = R.string.db_delete_sql))
            }
            Button(onClick = { updateWordBySql() }) {
                Text(stringResource(id = R.string.db_update_sql))
            }
            Button(onClick = { queryWordBySql() }) {
                Text(stringResource(id = R.string.db_query_sql))
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SqliteDemoTheme {
            sqlButtons()
        }
    }

}

