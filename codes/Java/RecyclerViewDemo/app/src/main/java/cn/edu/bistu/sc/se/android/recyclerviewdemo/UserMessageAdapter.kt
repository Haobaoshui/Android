package cn.edu.bistu.sc.se.android.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserMessageAdapter(userWordMessageList: MutableList<UserMessage>):
        RecyclerView.Adapter<UserMessageAdapter.UserMessageViewHolder>() {
    private var mUserWordMessageList= userWordMessageList


    /**
     * 如果1000个消息，调用onCreateViewHolder多少次？
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserMessageViewHolder {
        return if (viewType == TYPE_RECEIVE) {
            //用户接收的消息，显示在左侧
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_left_layout, parent, false)
            //    Log.v(GameSystem.LOG_TAG, "left view.toString():" + view.toString());
            UserMessageViewHolder(view)
        } else {
            //用户发送的消息，显示在右侧
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.msg_right_layout, parent, false)
            UserMessageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: UserMessageViewHolder, position: Int) {
        val msg = mUserWordMessageList[position]
        // 根据消息的类型来选择不同的布局
        if (msg.type === TYPE_RECEIVE) holder.tvLeft.text = msg.content
        else holder.tvRight.text = msg.content
    }

    override fun getItemViewType(position: Int): Int {
        val msg = mUserWordMessageList[position]
        return msg.type
    }

    override fun getItemCount(): Int {
        return mUserWordMessageList.size
    }

    class UserMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvLeft: TextView =itemView.findViewById<TextView>(R.id.user_message_leftText)
        val tvRight: TextView =itemView.findViewById<TextView>(R.id.user_message_rightText)
    }
}