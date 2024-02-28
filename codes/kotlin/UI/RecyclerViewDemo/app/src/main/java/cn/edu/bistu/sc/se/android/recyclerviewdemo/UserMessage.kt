package cn.edu.bistu.sc.se.android.recyclerviewdemo

const val TYPE_SEND = 1 //用户接收的消息

const val TYPE_RECEIVE = 0 //用户接收的消息


data class UserMessage(val content:String,val type:Int)
