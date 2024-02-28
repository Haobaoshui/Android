package cn.edu.bistu.cs.se.composechatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.updateBounds
import cn.edu.bistu.cs.se.composechatapp.ui.theme.ComposeChatAppTheme

val listMsg = mutableListOf<UserMessage>(
    UserMessage(
        "发送 消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1消息1",
        TYPE_SEND
    ),
    UserMessage("发送 消息2消息2消息2", TYPE_SEND),
    UserMessage("收到 消息2消息2消息2消息2消息2消息2消息2消息2", TYPE_RECEIVE),
    UserMessage("发送 消息2", TYPE_SEND),
    UserMessage("收到 消息2", TYPE_RECEIVE),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(listMsg)
                }
            }
        }
    }
}

@Composable
fun sendMessage(message: UserMessage) {
    //compose目前对于9-patch支持不是很好
    val bgImg = ContextCompat.getDrawable(
        LocalContext.current,
        R.drawable.right_chat
    )

    Row(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End

    ) {
        Box(
            Modifier
                .drawBehind {
                    bgImg?.updateBounds(0, 0, size.width.toInt(), size.height.toInt())
                    bgImg?.draw(drawContext.canvas.nativeCanvas)
                }
                .padding(20.dp, 20.dp)) {
            Text(
                text = message.content,
                Modifier.wrapContentSize()
            )
        }
    }
}

@Composable
fun receivedMessage(message: UserMessage) {
    val bgImg = ContextCompat.getDrawable(
        LocalContext.current,
        R.drawable.left_chat
    )

    Row(
        Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start

    ) {
        Box(
            Modifier
                .drawBehind {
                    bgImg?.updateBounds(0, 0, size.width.toInt(), size.height.toInt())
                    bgImg?.draw(drawContext.canvas.nativeCanvas)
                }
                .padding(10.dp, 10.dp)) {
            Text(
                text = message.content,
                Modifier.wrapContentSize()
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(messages: List<UserMessage>, modifier: Modifier = Modifier) {
    var userInputMessage by remember { mutableStateOf("") }
    /**
     * 这里简单起见，使用了Column()，但存在很大缺陷
     * 改进：使用约束布局
     * https://developer.android.google.cn/jetpack/compose/layouts/constraintlayout?hl=zh-cn
     */
    Column() {
        LazyColumn {
            items(
                messages
            ) { message ->
                if (message.type == TYPE_SEND)
                    sendMessage(message)
                else
                    receivedMessage(message)
            }
        }
        Row() {
            TextField(value = userInputMessage, onValueChange = { userInputMessage = it })
            Button(onClick = { }) {
                listMsg.add(UserMessage("发送 " + userInputMessage, TYPE_SEND))
                listMsg.add(UserMessage("接收到 " + userInputMessage, TYPE_RECEIVE))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeChatAppTheme {
        Greeting(listMsg)
    }
}