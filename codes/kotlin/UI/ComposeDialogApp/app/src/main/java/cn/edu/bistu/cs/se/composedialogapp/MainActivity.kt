package cn.edu.bistu.cs.se.composedialogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.edu.bistu.cs.se.composedialogapp.ui.theme.ComposeDialogAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDialogAppTheme {
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
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        },
            title = {
                Text(text = "对话框标题")
            },
            text = {
                Text("对话框内容")
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                }
                ) {
                    Text("确认")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                }
                ) {
                    Text("取消")
                }
            }
        )
    }

    Button(onClick = {
        openDialog.value = true

    }) {
        Text("打开对话框")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeDialogAppTheme {
        Greeting("Android")
    }
}