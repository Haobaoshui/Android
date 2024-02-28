package cn.edu.bistu.cs.se.roomsimpledemo.database

import java.util.Date
import androidx.room.*
/**
 * 类型转换器
 * Room不知道如何保存Date对象，但知道如何保存long对象。
 * 因此，这里定义了两种类型的转换器：Date->Long,Long->Date
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}