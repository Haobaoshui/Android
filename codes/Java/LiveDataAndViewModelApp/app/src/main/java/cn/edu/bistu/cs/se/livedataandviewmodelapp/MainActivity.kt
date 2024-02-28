package cn.edu.bistu.cs.se.livedataandviewmodelapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import cn.edu.bistu.cs.se.livedataandviewmodelapp.ui.theme.LiveDataAndViewModelAppTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveDataAndViewModelAppTheme {
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

    fun onNextNumber(){

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context= LocalContext.current
    var userInputValue by remember { mutableStateOf("") }

    var viewModel by remember { mutableStateOf(GameViewModel()) }

    val userScore by viewModel.score.observeAsState()

    Column() {
        Text(
            text = "猜字母（A-E）游戏",
            modifier = modifier
        )
        TextField(
            value = userInputValue,
            onValueChange = { userInputValue = it },
            label = { Text("Enter text") },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(20.dp)
        )

        Text(
            text = "分数：$userScore",
            modifier = modifier
        )
        
        Button(onClick = {
            if(viewModel.isUserCharCorrect(userInputValue)) {
                viewModel.nextChar()
                userInputValue=""
            }
            else{
                val toast=Toast.makeText(context,"猜错了",Toast.LENGTH_SHORT)
                toast.show()
            }

        }) {
            Text(
                text = "下一个",
                modifier = modifier
            )
        }

        Button(onClick = {
            viewModel.reinitializeData()
        }) {
            Text(
                text = "重新开始游戏",
                modifier = modifier
            )
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LiveDataAndViewModelAppTheme {
        Greeting("Android")
    }
}