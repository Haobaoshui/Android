package cn.edu.bistu.cs.se.hellomessage

import android.app.Application
import cn.edu.bistu.cs.se.hellomessage.database.UserMessageDatabase
import cn.edu.bistu.cs.se.hellomessage.database.UserMessageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HelloMessageApplication : Application() {


    private val database by lazy { UserMessageDatabase.getDatabase(this) }
    val repository by lazy { UserMessageRepository(database.userMessageDao()) }
}