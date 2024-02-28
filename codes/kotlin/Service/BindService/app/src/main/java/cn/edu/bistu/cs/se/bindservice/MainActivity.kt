package cn.edu.bistu.cs.se.bindservice

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.edu.bistu.cs.se.bindservice.ui.theme.BindServiceTheme
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.lang.Integer.parseInt

class MainActivity : ComponentActivity() {
    //变量延迟初始化
    private lateinit var mService: CalcService
    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: android.content.ComponentName?,
            service: android.os.IBinder?
        ) {
            Log.v("BindService","onServiceConnected")
            val binder = service as CalcService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(name: android.content.ComponentName?) {
            Log.v("BindService","onServiceDisconnected")
            mBound = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BindServiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Intent(this, CalcService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        Log.v("BindService","bindService")
    }

    override fun onStop() {
        super.onStop()

        unbindService(connection)
        mBound = false
        Log.v("BindService","unbindService")
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        val context= LocalContext.current

        var num1 by remember { mutableStateOf("") }
        var num2 by remember { mutableStateOf("") }
        var value by remember { mutableStateOf("Hello\nWorld\nInvisible") }
        Column() {
            TextField(value = num1, onValueChange = {  num1 = it },label = { Text("Num1") },)
            TextField(value = num2, onValueChange = {  num2 = it },label = { Text("Num2") },)
            Button(onClick = {
                if (mBound) {
                    val num: Int = mService.add(parseInt(num1) , parseInt(num2))
                    Toast.makeText(context, "$num1+$num2=$num", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Add")
            }
            Button(onClick = {
                if (mBound) {
                    val num: Int = mService.sub(parseInt(num1) , parseInt(num2))
                    Toast.makeText(context, "$num1+$num2=$num", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Sub")
            }
        }


    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BindServiceTheme {
            Greeting("Android")
        }
    }
}

