package cn.edu.bistu.cs.se.startactivityforresultapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import cn.edu.bistu.cs.se.startactivityforresultapp.ui.theme.StartActivityForResultAppTheme

class AnotherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //获取数据
        val age=this.intent.getIntExtra("age", 20)
        val name=intent.getStringExtra("name");

        setContent {
            StartActivityForResultAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        Greeting2(::onSetResultClick,name,20,)
                }
            }
        }
    }

    private fun onSetResultClick(name: String?, age:Int=20){
        val intent=Intent(this,MainActivity::class.java).apply {
            putExtra("result", "姓名:$name 年龄$age")
        }
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}

@Composable
fun Greeting2(onSetResultClick:(String?,Int)->Unit,name: String?, age:Int=20,modifier: Modifier = Modifier) {
    val context= LocalContext.current
    Column() {
        Text(text = "姓名:$name")
        Text(text = "年龄:$age")
        Button(onClick = {
            onSetResultClick(name,age)
        }) {
            Text(
                text = "返回结果",
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    StartActivityForResultAppTheme {
        Greeting2({ _, _ ->{}},"Android",20)
    }
}