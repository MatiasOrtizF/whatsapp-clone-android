package com.mfo.chatapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfo.chatapp.data.remote.util.Constants.USER_ID_PREF
import com.mfo.chatapp.databinding.ActivityHomeBinding
import com.mfo.chatapp.ui.chat.ChatActivity
import com.mfo.chatapp.ui.home.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        //binding.tvTitle.text = USER_ID_PREF
    }

    private fun initUI() {
        initList()
        initListeners()
    }

    private fun initList() {
        chatAdapter = ChatAdapter(onItemSelected = {
            handleGoToChat()
        })

        binding.rvContactList.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = chatAdapter
        }
    }

    private fun initListeners() {

    }

    private fun handleGoToChat() {
        val intent = Intent(this, ChatActivity::class.java)
        startActivity(intent)
        finish()
    }
}