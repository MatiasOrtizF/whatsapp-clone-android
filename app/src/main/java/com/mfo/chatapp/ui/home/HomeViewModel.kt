package com.mfo.chatapp.ui.home

import androidx.lifecycle.ViewModel
import com.mfo.chatapp.data.local.ChatProvider
import com.mfo.chatapp.domain.model.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(chatProvider: ChatProvider): ViewModel() {
    private val _chatList = MutableStateFlow<List<Chat>>(emptyList())
    val chatList: StateFlow<List<Chat>> = _chatList

    init {
        _chatList.value = chatProvider.getChats()
    }
}