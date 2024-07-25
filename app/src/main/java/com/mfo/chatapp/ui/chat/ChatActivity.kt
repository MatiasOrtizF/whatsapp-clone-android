package com.mfo.chatapp.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.mfo.chatapp.databinding.ActivityChatBinding
import com.mfo.chatapp.domain.model.Message
import com.mfo.chatapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val chatViewModel: ChatViewModel by viewModels()

    private val messagesListAdapter by lazy {
        MessageListAdapter("1")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initList()
        initUiState()
        //initObservers()
        initListeners()
    }

    private fun initList() {
        binding.rvMessagesList.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = messagesListAdapter
        }
    }

    private fun initUiState() {
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        chatViewModel.messagesListState.observe(this) { state ->
            when(state) {
                is Resource.Success -> {
                    handleMessages(messages = state.data)
                }
                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> handleLoading(isLoading = true)
                else -> Unit
            }
        }

        chatViewModel.sendMessageState.observe(this) { state ->
            when(state) {
                is Resource.Success -> clearMessage()
                is Resource.Error -> Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        }
    }

    private fun initListeners() {
        binding.sendMessage.setOnClickListener {
            handlerSendMessage()
        }
    }

    private fun handlerSendMessage() {
        //hideKeyboard()
        chatViewModel.sendMessage(
            Message(
                chatId = "1",
                message =  binding.etMessage.text.toString(),
                senderId = "1",
                timestamp = Timestamp.now()
            )
        )
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.apply {
            pbChat.isVisible = isLoading
        }
    }

    private fun handleMessages(messages: List<Message>) {
        if (messages.isEmpty()) {
            showEmptyScreen()
        } else {
            messagesListAdapter.submitList(messages)
            showMessages()
        }
    }

    private fun showEmptyScreen() {
        handleLoading(isLoading = false)
        binding.apply {
            rvMessagesList.isVisible = false
            tvEmptyMessages.isVisible = true
        }
    }

    private fun showMessages() {
        handleLoading(isLoading = false)
        binding.apply {
            rvMessagesList.isVisible = true
            tvEmptyMessages.isVisible = false
        }
    }

    private fun clearMessage() {
        binding.etMessage.text.clear()
        binding.rvMessagesList.smoothScrollToPosition(0)
    }
}