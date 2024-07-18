package com.mfo.chatapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.mfo.chatapp.R
import com.mfo.chatapp.databinding.ActivityLoginBinding
import com.mfo.chatapp.databinding.ActivitySignupBinding
import com.mfo.chatapp.ui.signup.SignUpViewModel
import com.mfo.chatapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        loginViewModel.loginState.observe(this) { state ->
            when(state) {
                is Resource.Success -> {
                    handleLoading(isLoading = false)
                    Toast.makeText(
                        this,
                        "Login success",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Error -> {
                    handleLoading(isLoading = false)
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
    }

    private fun initListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                handleLogin()
            }
        }
    }

    private fun handleLogin() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        loginViewModel.login(email, password)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.apply {
            if(isLoading) {
                pbSignUp.isVisible = true
                etEmail.isVisible = false
                etPassword.isVisible = false
                btnLogin.isVisible = false
            } else {
                pbSignUp.isVisible = false
                etEmail.isVisible = true
                etPassword.isVisible = true
                btnLogin.isVisible = true
            }
        }
    }
}