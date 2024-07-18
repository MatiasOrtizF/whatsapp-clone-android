package com.mfo.chatapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfo.chatapp.R
import com.mfo.chatapp.databinding.ActivityMainBinding
import com.mfo.chatapp.databinding.ActivitySignupBinding
import com.mfo.chatapp.ui.login.LoginActivity
import com.mfo.chatapp.ui.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
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
}