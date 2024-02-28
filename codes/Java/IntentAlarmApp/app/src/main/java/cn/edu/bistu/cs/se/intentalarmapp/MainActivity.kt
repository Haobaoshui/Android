package cn.edu.bistu.cs.se.intentalarmapp

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import cn.edu.bistu.cs.se.intentalarmapp.ui.theme.IntentAlarmAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentAlarmAppTheme {
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
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val mContext = LocalContext.current

    Button(onClick = {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "我的闹钟")
            putExtra(AlarmClock.EXTRA_HOUR, 4)
            putExtra(AlarmClock.EXTRA_MINUTES, 10)
            putExtra(AlarmClock.EXTRA_SKIP_UI,true)
        }
        if (intent.resolveActivity(mContext.packageManager) != null) {
            mContext.startActivity(intent)
        }

     }) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntentAlarmAppTheme {
        Greeting("Android")
    }
}