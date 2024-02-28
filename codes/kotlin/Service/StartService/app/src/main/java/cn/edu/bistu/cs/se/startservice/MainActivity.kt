package cn.edu.bistu.cs.se.startservice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import cn.edu.bistu.cs.se.startservice.ui.theme.StartServiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartServiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column() {
        Button(onClick = {
            val intent = Intent(context, MyService::class.java);
            intent.putExtra("num", 10);
            context.startService(intent);
        }) {
            Text(text = "启动服务")
        }
        Button(onClick = {
            val intent = Intent(context, MyService::class.java);
            context.stopService(intent);
        }) {
            Text(text = "停止服务")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StartServiceTheme {
        Greeting("Android")
    }
}