package cn.edu.bistu.cs.se.hellomessage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.edu.bistu.cs.se.hellomessage.database.UserMessage
import cn.edu.bistu.cs.se.hellomessage.database.UserMessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserMessageViewModel(private val repository: UserMessageRepository) : ViewModel(){
    val allMessages: LiveData<List<UserMessage>> = repository.allMessages.asLiveData()

    fun insert(userMessage: UserMessage) = viewModelScope.launch {
        repository.insert(userMessage)
    }
}

class UserMessageViewModelFactory(private val repository: UserMessageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserMessageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserMessageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}