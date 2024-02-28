package cn.edu.bistu.cs.se.sdfiledemo

import android.content.Context
import android.os.Bundle
import android.os.Environment
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
import cn.edu.bistu.cs.se.sdfiledemo.ui.theme.SDFileDemoTheme
import timber.log.Timber
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SDFileDemoTheme {
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


    //检查外部存储空间是否可写
    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    //检查外部存储空间是否可读
    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    /**
     * 将数据写入到文件中
     */
    private fun writeData() {


        if (!isExternalStorageWritable())
            return
        val filename = "test.txt"
        val fileContents = "Hello world!"

        val appSpecificExternalDir = File(getExternalFilesDir(null), filename)
        val fileWriter = FileWriter(appSpecificExternalDir, true)
        fileWriter.write(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()) + "\n")
        fileWriter.appendLine(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()) + "，张三")
        fileWriter.appendLine(fileContents)
        fileWriter.close()
    }

    /**
     * 从文件中读取数据
     */
    private fun readData() {

        if (!isExternalStorageReadable())
            return


        val filename = "test.txt"
        val appSpecificExternalDir = File(getExternalFilesDir(null), filename)

        var reader = FileReader(appSpecificExternalDir)
        val lines = reader.readLines()
        for (item in lines) {
            Timber.i(item)
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
        SDFileDemoTheme {
            ReadWriteButton()
        }
    }


}

