package cn.edu.bistu.cs.se.hellomessage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserMessage::class], version = 1)
abstract class UserMessageDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: UserMessageDatabase? = null
        fun getDatabase(context: Context): UserMessageDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, UserMessageDatabase::class.java, "hello_messages.db")
                    // add migrations here
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as UserMessageDatabase
        }
    }
    abstract fun userMessageDao(): UserMessageDao
}