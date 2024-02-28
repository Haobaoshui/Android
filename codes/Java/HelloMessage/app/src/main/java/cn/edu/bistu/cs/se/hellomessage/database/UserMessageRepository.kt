package cn.edu.bistu.cs.se.hellomessage.database

import kotlinx.coroutines.flow.Flow

class UserMessageRepository (private val userMessageDao:UserMessageDao){
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allMessages: Flow<List<UserMessage>> = userMessageDao.getAll()


    suspend fun insert(userMessageTable: UserMessage) {
        userMessageDao.insert(userMessageTable)
    }
}