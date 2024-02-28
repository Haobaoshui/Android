package cn.edu.bistu.cs.se.roomsimpledemo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_user")
data class User(
    @PrimaryKey (autoGenerate = true) val uid:Int?=null,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "user_password") val userPassword: String?,
    @ColumnInfo(name = "user_email") val userEmail: String?
)