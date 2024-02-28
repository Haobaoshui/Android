package cn.edu.bistu.cs.se.startactivityforresultapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

class MainActivity : ComponentActivity() {

    private val requestDataLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
        if (result.resultCode== RESULT_OK){
            val data=result.data?.getStringExtra("result")
            Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
        }
    }

    private val myResultContract=registerForActivityResult(MyResultContract()){result->
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartActivityForResultAppTheme {
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

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        val context= LocalContext.current
        Column() {
            Button(onClick = {
                val intent= Intent(context,AnotherActivity::class.java).apply {
                    putExtra("name","张三")
                    putExtra("age",20)
                }

                requestDataLauncher.launch(intent)


            }) {
                Text(
                    text = "启动另外一个Activity",
                    modifier = modifier
                )
            }

            Button(onClick = {
                myResultContract.launch("李四")
            }) {
                Text(
                    text = "启动另外一个Activity,使用自定义协议",
                    modifier = modifier
                )
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        StartActivityForResultAppTheme {
            Greeting("Android")
        }
    }
}

