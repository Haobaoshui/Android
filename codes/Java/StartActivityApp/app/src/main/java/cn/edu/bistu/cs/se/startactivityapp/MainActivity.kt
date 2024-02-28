package cn.edu.bistu.cs.se.startactivityapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import cn.edu.bistu.cs.se.startactivityapp.ui.theme.StartActivityAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartActivityAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        fun butClick() {
            val intent = Intent(this, AnotherActivity::class.java)

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val mContext = LocalContext.current
    Column() {
        Button(onClick = {
            val intent = Intent(mContext, AnotherActivity::class.java)
            mContext.startActivity(intent)
        }) {
            Text("Start activity")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StartActivityAppTheme {
        Greeting("Android")
    }
}