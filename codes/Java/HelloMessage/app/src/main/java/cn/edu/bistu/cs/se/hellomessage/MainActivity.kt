package cn.edu.bistu.cs.se.hellomessage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import cn.edu.bistu.cs.se.hellomessage.database.TYPE_RECEIVE
import cn.edu.bistu.cs.se.hellomessage.database.TYPE_SEND
import cn.edu.bistu.cs.se.hellomessage.database.UserMessage
import cn.edu.bistu.cs.se.hellomessage.ui.theme.HelloMessageTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val userMessageViewModel: UserMessageViewModel by viewModels {
        UserMessageViewModelFactory((application as HelloMessageApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloMessageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var state = userMessageViewModel.allMessages.observeAsState(arrayListOf())

                    Greeting(
                        userMessageViewModel,
                        state.value
                    )

                }
            }
        }
    }
}


/**
 * 用户按下消息发送按钮时发送消息
 * @param view
 */
fun onButtonSendMessageClick(
    userMessageViewModel: UserMessageViewModel,
    userInputValue: String?,
    context: android.content.Context
) {
    var content = userInputValue
    if (content != null) {
        if (content.isEmpty()) {
            Toast.makeText(context, "文本内容不能为空", Toast.LENGTH_SHORT).show()
            return
        }
    }
    val userMessageSend = UserMessage(0, TYPE_SEND, content)
    userMessageViewModel.insert(userMessageSend)


    /**
     * 这里模拟接收对方发送的消息，在实际程序中，应该从网络获得相关数据
     */

    val newConent = "我接收到的消息是：$content"
    val userMessageReceived = UserMessage(0, TYPE_RECEIVE, newConent)
    userMessageViewModel.insert(userMessageReceived)
}


@Composable
fun HomeScreen(
    innerPadding: PaddingValues, messages: List<UserMessage>
) {
    Log.v("test", "HomeScreen")
    LazyColumn()
    {
        items(items = messages, key = { item -> item.id }) {
            Text(
                "$it",
                color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(
    userMessageViewModel: UserMessageViewModel,
    messages: List<UserMessage>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var userInputValue by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            ConstraintLayout {
                val (button, textField) = createRefs()

                TextField(
                    value = userInputValue,
                    onValueChange = { userInputValue = it },
                    maxLines = 1,
                    modifier = Modifier.constrainAs(textField) {
                        top.linkTo(parent.top, margin = 16.dp)
                        bottom.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(button.start, margin = 16.dp)
                    }
                )

                Button(
                    onClick = {
                        onButtonSendMessageClick(
                            userMessageViewModel,
                            userInputValue,
                            context
                        )
                    },
                    // Assign reference "button" to the Button composable
                    // and constrain it to the top of the ConstraintLayout
                    modifier = Modifier.constrainAs(button) {
                        top.linkTo(parent.top, margin = 16.dp)
                        bottom.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(textField.end, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                    }
                ) {
                    Text("Add")
                }
            }
        },

        //定义用户列表数据
        content = { innerPadding ->
            HomeScreen(innerPadding, messages)

        }
    )
}

