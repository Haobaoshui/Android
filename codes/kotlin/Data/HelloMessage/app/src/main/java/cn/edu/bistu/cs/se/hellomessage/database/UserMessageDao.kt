package cn.edu.bistu.cs.se.hellomessage.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserMessageDao {



    @Query("SELECT * FROM t_user_message")
    fun getAll(): Flow<List<UserMessage>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userMessage: UserMessage)

    /*

    @Delete
    suspend fun delete(userMessage: UserMessage)

    @Query("DELETE FROM t_user_message")
    suspend fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(userMessage: UserMessage)
    */


}