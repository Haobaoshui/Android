package cn.edu.bistu.cs.se.roomsimpledemo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun insertUser(user:User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM t_user")
    fun deleteAllUsers()

    @Query("SELECT * FROM t_user")
    fun loadAllUsers(): Array<User>

    @Query("SELECT * FROM t_user WHERE user_name LIKE :search")
    fun findUserWithName(search: String): List<User>

    @Query("SELECT user_name as userName,user_email as userEmail FROM t_user")
    fun loadAllUserEmails(): Array<UserEmail>

    data class UserEmail(val userName: String?, val userEmail: String?)
}
