package cn.edu.bistu.cs.se.intentchooserapp

import android.content.Intent
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import cn.edu.bistu.cs.se.intentchooserapp.ui.theme.IntentChooserAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentChooserAppTheme {
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
Column() {
    Button(onClick = {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TITLE,"标题信息")
        sendIntent.putExtra(Intent.EXTRA_TEXT,"内容信息")
        sendIntent.putExtra(Intent.EXTRA_SUBJECT,"主题信息")


        //创建Intent并显示选择框
        val chooser: Intent = Intent.createChooser(sendIntent, "选择分享应用")

        //验证原始Intent能够被至少一个Activity解析
        if (sendIntent.resolveActivity(mContext.packageManager) != null) {
            mContext.startActivity(chooser)
        }

    }) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntentChooserAppTheme {
        Greeting("Android")
    }
}