package cn.edu.bistu.cs.se.filedemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import cn.edu.bistu.cs.se.filedemo.ui.theme.FileDemoTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FileDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReadWriteButton()
                }
            }
        }
    }

    /**
     * 将数据写入到文件中
     */
    private fun writeData() {
        val filename = "test.txt"
        val fileContents = "Hello world!"
        openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
    }

    /**
     * 从文件中读取数据
     */
    private fun readData() {
        val context = this
        val filename = "test.txt"
        openFileInput(filename).bufferedReader().useLines { lines ->
            lines.forEach {
                //日志输出
                Timber.i(it)
            }
        }
    }

    @Composable
    fun ReadWriteButton(modifier: Modifier = Modifier) {

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { writeData() }) {
                Text(stringResource(id = R.string.write_data))
            }
            Button(onClick = { readData() }) {
                Text(stringResource(id = R.string.read_data))
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        FileDemoTheme {
            ReadWriteButton()
        }
    }


}







