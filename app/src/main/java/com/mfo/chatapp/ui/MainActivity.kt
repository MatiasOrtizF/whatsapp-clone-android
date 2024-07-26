package com.mfo.chatapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfo.chatapp.databinding.ActivityMainBinding
import com.mfo.chatapp.ui.chat.ChatActivity
import com.mfo.chatapp.ui.login.LoginActivity
import com.mfo.chatapp.ui.signup.SignUpActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.btnGoToSignUp.setOnClickListener {
            handleGoToSignUp()
        }
        binding.btnGoToLogin.setOnClickListener {
            handleGoToLogin()
        }
        binding.btnGoToChat.setOnClickListener {
            handleGoToChat()
        }
    }

    private fun handleGoToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun handleGoToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun handleGoToChat() {
        val intent = Intent(this, ChatActivity::class.java)
        startActivity(intent)
        finish()
    }
}