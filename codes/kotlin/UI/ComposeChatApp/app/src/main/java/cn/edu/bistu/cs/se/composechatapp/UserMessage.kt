package cn.edu.bistu.cs.se.composechatapp

const val TYPE_SEND = 1 //用户接收的消息

const val TYPE_RECEIVE = 0 //用户接收的消息

/**
 * 消息：消息内容和消息类型（发送或接收）
 */
data class UserMessage(val content:String,val type:Int)
