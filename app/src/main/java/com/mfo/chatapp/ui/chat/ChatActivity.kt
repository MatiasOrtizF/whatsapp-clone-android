package com.mfo.chatapp.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfo.chatapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }
}