package cn.edu.bistu.cs.se.composelistapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.edu.bistu.cs.se.composelistapp.ui.theme.ComposeListAppTheme
import kotlinx.coroutines.launch

const val TAG = "test"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MessageList()
                }
            }
        }
    }
}

@Composable
private fun ItemRow(item: String, clickEvent: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue)
    ) {
        Text(
            text = item,
            modifier = Modifier
                .clickable(onClick = clickEvent)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .height(50.dp)
        )
    }
}

val ItemClick = { fruit: String, context: Context ->
    Toast.makeText(context, fruit, Toast.LENGTH_SHORT).show()
}

@Composable
fun ScrollToTopButton(onClick: () -> Unit) {
    Box(
        Modifier
            .padding(bottom = 50.dp),
        Alignment.BottomCenter
    ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .shadow(10.dp, shape = CircleShape)
                .clip(shape = CircleShape)
                .size(65.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Green
            )
        ) {
            Icon(Icons.Filled.KeyboardArrowUp, "arrow up")
        }
    }
}




@Composable
fun MessageList(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val gradient = Brush.verticalGradient(
        listOf(Color.Red, Color.Blue, Color.Green), 0.0f, 10000.0f, TileMode.Repeated
    )

    if (listState.isScrollInProgress) {

        DisposableEffect(Unit) {
            //只会在滚动时执行一次
            Log.d(TAG, "开始滚动")
            onDispose {
                Log.d(TAG, "停止滚动")
            }
        }


        //判断滑动方向和滑动距离
        //记录上一次第一个可见item的滑动偏移
        var lastFirstVisibleItemScrollOffset by remember {
            mutableStateOf(listState.firstVisibleItemScrollOffset)
        }

        //记录上一次第一个可见item下标
        var lastFirstVisibleItemIndex by remember {
            mutableStateOf(listState.firstVisibleItemIndex)
        }
        run {
            val currentFirstVisibleItemIndex = listState.firstVisibleItemIndex
            //手指向上滑动（即正方向）时offset会变大，向下时变小
            val currentFirstVisibleItemScrollOffset = listState.firstVisibleItemScrollOffset

            //第一个可见的item改变了
            if (currentFirstVisibleItemIndex != lastFirstVisibleItemIndex) {
                if (currentFirstVisibleItemIndex < lastFirstVisibleItemIndex) {
                    Log.d(TAG, "向下滑动 ")
                } else if (currentFirstVisibleItemIndex > lastFirstVisibleItemIndex) {
                    Log.d(TAG, "向上滑动")
                }
                //更新记录的值，退出run代码块
                lastFirstVisibleItemIndex = currentFirstVisibleItemIndex
                lastFirstVisibleItemScrollOffset = currentFirstVisibleItemScrollOffset
                return@run
            }

            //第一个可见item当前的offset - 上一次记录的offset
            val offset = currentFirstVisibleItemScrollOffset - lastFirstVisibleItemScrollOffset
            if (offset < 0) {
                Log.d(TAG, "向下滑动 $offset")
            } else if (offset > 0) {
                Log.d(TAG, "向上滑动 $offset")
            }
            //记录第一个可见item当前的offset
            lastFirstVisibleItemScrollOffset = currentFirstVisibleItemScrollOffset
        }
    }


    val itemsList = (0..50).toList()
    val itemsIndexedList = listOf("A", "B", "C","D","F")
    LazyColumn(
        state = listState,
        modifier = Modifier
            .padding(10.dp)
            .background(brush = gradient),//边距、背景等
        contentPadding = PaddingValues(
            horizontal = 20.dp, vertical = 8.dp
        ),//内容内边距，此处水平方向（左侧和右侧）为20.dp，垂直方向（顶部和底部）为8.dp
        verticalArrangement = Arrangement.spacedBy(4.dp),//内容边距，此处每个列表项之间增加4.dp间距
    ) {

        items(items = itemsList, key = { item -> item }) {
            Text(
                "Item is $it",
                Modifier.clickable {
                    Toast.makeText(context, "Item is $it", Toast.LENGTH_SHORT).show()
                }, color = Color.White
            )
        }

        item(
        ) {
            val itemText = "Single item"
            ItemRow(itemText) { ItemClick(itemText, context) }
        }

        itemsIndexed(itemsIndexedList) { index, item ->
            Text("Item at index $index is $item")
        }
    }


    // Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()

    //当第一项不可见时显示“滚动到顶部”按钮
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0// 列表第一个可见项的下标
        }
    }


    AnimatedVisibility(
        visible = showButton,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        ScrollToTopButton(onClick = {
            Log.v(TAG, "滚动到顶部")
            coroutineScope.launch {
                // 以动画方式滚动到第一个条目
                listState.animateScrollToItem(index = 0)
            }
        })
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeListAppTheme {
        MessageList()
    }
}