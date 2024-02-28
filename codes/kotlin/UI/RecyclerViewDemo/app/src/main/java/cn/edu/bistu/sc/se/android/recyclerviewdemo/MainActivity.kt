package cn.edu.bistu.sc.se.android.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private var mUserWordMessageAdapter: UserMessageAdapter? = null
    private lateinit var mEditTextUserWordMessage: EditText
    private var mUserWordMessageList = mutableListOf<UserMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEditTextUserWordMessage = findViewById(R.id.edittextUserMessage)


        mRecyclerView = findViewById(R.id.recyclerview)
        mUserWordMessageAdapter = UserMessageAdapter(mUserWordMessageList)
        val linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView.setLayoutManager(linearLayoutManager)
        mRecyclerView.setAdapter(mUserWordMessageAdapter)
    }

    /**
     * 用户按下消息发送按钮时发送消息
     * @param view
     */
    fun onButtonSendMessageClick(view: View?) {
        var content = mEditTextUserWordMessage.text.toString()
        content = content.trim { it <= ' ' }
        if (content.isEmpty()) {
            Toast.makeText(this, "文本内容不能为空", Toast.LENGTH_SHORT).show()
            return
        }

        Log.v("tag","asdf")
        addSendMessage(content)
        /**
         * 这里模拟接收对方发送的消息，在实际程序中，应该定时从网络获得相关数据
         */
        addReceiveMessage("我接收到$content")
    }

    /**
     * 增加接收消息
     * @param content
     */
    private fun addReceiveMessage(content: String) {
        val userWordMessage = UserMessage(content, TYPE_RECEIVE)
        mUserWordMessageList.add(userWordMessage)

        // 表示在消息的末尾插入内容
        mUserWordMessageAdapter!!.notifyItemInserted(mUserWordMessageList!!.size - 1)

        // 让 RecyclerView 自动滚动到最底部
        mRecyclerView!!.scrollToPosition(mUserWordMessageList!!.size - 1)
        mEditTextUserWordMessage!!.setText("")
    }

    /**
     * 增加发送消息
     * @param content
     */
    private fun addSendMessage(content: String) {
        val userWordMessage = UserMessage(content, TYPE_SEND)
        mUserWordMessageList.add(userWordMessage)

        // 表示在消息的末尾插入内容
        mUserWordMessageAdapter!!.notifyItemInserted(mUserWordMessageList!!.size - 1)

        // 让 RecyclerView 自动滚动到最底部
        mRecyclerView!!.scrollToPosition(mUserWordMessageList!!.size - 1)
        mEditTextUserWordMessage!!.setText("")
    }
}