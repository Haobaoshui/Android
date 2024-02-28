package cn.edu.bistu.cs.se.hellomessage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TYPE_SEND=0
const val TYPE_RECEIVE=1

/**
 * 定义数据库实体，对应表"t_user_message"，该表保存用户消息
 *
 */
@Entity(tableName = "t_user_message")
data class UserMessage(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "message_type") val messageType: Int,
    @ColumnInfo(name = "message_content") val messageContent: String?
)