package com.mfo.chatapp.ui.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.mfo.chatapp.data.remote.util.Constants.SHARED_EMAIL
import com.mfo.chatapp.data.remote.util.Constants.SHARED_PASSWORD
import com.mfo.chatapp.databinding.ActivityLoginBinding
import com.mfo.chatapp.ui.chat.ChatActivity
import com.mfo.chatapp.ui.home.HomeActivity
import com.mfo.chatapp.ui.signup.SignUpActivity
import com.mfo.chatapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

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
                    //loginViewModel.saveUserSelection("1")
                    manageUserLogin()
                    goToHome()
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
            btnGoToSignUp.setOnClickListener {
                handleGoToSignUp()
            }
        }
    }

    private fun manageUserLogin() {
        sharedPreferences.edit().putString(SHARED_EMAIL, binding.etEmail.text.toString().trim()).apply()
        sharedPreferences.edit().putString(SHARED_PASSWORD, binding.etPassword.text.toString().trim()).apply()
    }

    private fun handleLogin() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if(email.trim().isNotEmpty() && password.trim().isNotEmpty()) {
            loginViewModel.login(email, password)
        }
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

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun handleGoToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}