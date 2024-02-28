package com.example.sharedpreferencesdemo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sharedpreferencesdemo.ui.theme.SharedPreferencesDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedPreferencesDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReadWriteButton()
                }
            }
        }


    }

    /**
     * 写入键值对信息
     */
    private fun writeData(){
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(getString(com.example.sharedpreferencesdemo.R.string.key_name), "ZhangSan")
            putInt(getString(com.example.sharedpreferencesdemo.R.string.key_age), 20)
            putString(getString(com.example.sharedpreferencesdemo.R.string.key_address), "BeiJing")
            apply()
        }
    }

    /**
     * 读出键值对信息
     */
    private fun readData(){
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return

        val context=this
        with (sharedPref) {
           val name=getString(getString(com.example.sharedpreferencesdemo.R.string.key_name), "ZhangSan")
            val age=getInt(getString(com.example.sharedpreferencesdemo.R.string.key_age), 20)
            val address=getString(getString(com.example.sharedpreferencesdemo.R.string.key_address), "BeiJing")
            var toast=Toast.makeText(context,name,Toast.LENGTH_LONG)
            toast.show()
        }

    }

    @Composable
    fun ReadWriteButton( modifier: Modifier = Modifier) {

        Column(modifier=modifier,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { writeData() }) {
                Text(stringResource(id = R.string.write_data))
            }
            Button(onClick = { readData()}) {
                Text(stringResource(id = R.string.read_data))
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SharedPreferencesDemoTheme {
            ReadWriteButton()
        }
    }
}



