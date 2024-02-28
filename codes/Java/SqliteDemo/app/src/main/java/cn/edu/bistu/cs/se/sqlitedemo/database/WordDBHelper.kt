package cn.edu.bistu.cs.se.sqlitedemo.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns


//建表SQL
private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${WordContract.WordEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${WordContract.WordEntry.COLUMN_NAME_WORD} TEXT," +
            "${WordContract.WordEntry.COLUMN_NAME_MEANING} TEXT," +
            "${WordContract.WordEntry.COLUMN_NAME_SAMPLE} TEXT)"

//删表SQL
private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${WordContract.WordEntry.TABLE_NAME}"

class WordDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        //创建数据库
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //当数据库升级时被调用
        //这里简单起见，首先删除旧表，然后调用OnCreate()创建新表。实际业务中不能这么简单处理
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        //如果数据库结构发生了变化（例如新增表或字段），则需要升级版本
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "word.db"//存储在App的私有文件夹
    }
}