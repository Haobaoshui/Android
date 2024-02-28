package cn.edu.bistu.cs.se.roomsimpledemo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.*

@Database(entities = [User::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
