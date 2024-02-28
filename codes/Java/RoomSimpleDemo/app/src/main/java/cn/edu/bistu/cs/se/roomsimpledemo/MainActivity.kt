package cn.edu.bistu.cs.se.roomsimpledemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import cn.edu.bistu.cs.se.roomsimpledemo.database.AppDatabase
import cn.edu.bistu.cs.se.roomsimpledemo.database.User
import cn.edu.bistu.cs.se.roomsimpledemo.ui.theme.RoomSimpleDemoTheme
import timber.log.Timber
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    //数据库名字
    private val databaseName = "test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomSimpleDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    roomButtons()
                }
            }
        }
    }

    //增
    private fun insertData() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, databaseName
        ).allowMainThreadQueries().build()
        val user = User(userName = "a", userPassword = "123", userEmail = "a@a.com")
        val userDao = db.userDao()
        userDao.insertUser(user)
    }

    //删
    private fun deleteData() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, databaseName
        ).allowMainThreadQueries().build()

        val user = User(uid = 1, userName = "a", userPassword = "123", userEmail = "a@a.com")
        val userDao = db.userDao()
        userDao.deleteUser(user)
    }

    private fun deleteAllData(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, databaseName
        ).allowMainThreadQueries().build()


        val userDao = db.userDao()
        userDao.deleteAllUsers()
    }

    //改
    private fun updateData() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, databaseName
        ).allowMainThreadQueries().build()

        val user = User(uid = 2, userName = "b", userPassword = "456", userEmail = "b@a.com")
        val userDao = db.userDao()
        userDao.updateUser(user)
    }

    //查
    private fun queryUser() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, databaseName
        ).allowMainThreadQueries().build()
        val userDao = db.userDao()
        val arrayUsers = userDao.loadAllUsers()

        for (user in arrayUsers)
            Timber.i(user.toString())
    }

    @Composable
    fun roomButtons(modifier: Modifier = Modifier) {

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { insertData() }) {
                Text(stringResource(id = R.string.db_insert))
            }
            Button(onClick = { deleteData() }) {
                Text(stringResource(id = R.string.db_delete))
            }
            Button(onClick = { deleteAllData() }) {
                Text(stringResource(id = R.string.db_delete_all))
            }
            Button(onClick = { updateData() }) {
                Text(stringResource(id = R.string.db_update))
            }
            Button(onClick = { queryUser() }) {
                Text(stringResource(id = R.string.db_query))
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        RoomSimpleDemoTheme {
            roomButtons()
        }
    }
}

