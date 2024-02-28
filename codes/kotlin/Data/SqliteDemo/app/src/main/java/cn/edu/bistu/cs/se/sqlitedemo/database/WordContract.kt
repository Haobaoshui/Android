package cn.edu.bistu.cs.se.sqlitedemo.database

import android.provider.BaseColumns

object WordContract {
    object WordEntry : BaseColumns {
        const val  TABLE_NAME="t_word";//表的名字
        const val COLUMN_NAME_WORD="word";//列：单词
        const val COLUMN_NAME_MEANING="meaning";//列：单词含义
        const val COLUMN_NAME_SAMPLE="sample";//单词示例
    }
}