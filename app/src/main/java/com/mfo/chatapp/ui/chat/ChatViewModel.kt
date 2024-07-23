package com.mfo.chatapp.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfo.chatapp.domain.model.Message
import com.mfo.chatapp.domain.usecase.FetchMessageRealTimeUseCase
import com.mfo.chatapp.domain.usecase.SendMessageUseCase
import com.mfo.chatapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val fetchMessageRealTimeUseCase: FetchMessageRealTimeUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _messagesListState: MutableLiveData<Resource<List<Message>>> = MutableLiveData()
    val messagesListState: LiveData<Resource<List<Message>>>
        get() = _messagesListState

    private val _sendMessageState: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val sendMessageState: LiveData<Resource<Unit>>
        get() = _sendMessageState

    init {
        val chatId = "1"

        if(!chatId.isNullOrEmpty()) {
            getMessages(chatId = chatId)
        }
    }

    private fun getMessages(chatId: String) {
        viewModelScope.launch {
            fetchMessageRealTimeUseCase("1").onEach {
                println(it)
                _messagesListState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            sendMessageUseCase(message).onEach {
                _sendMessageState.value = it
            }.launchIn(viewModelScope)
        }
    }
}