package com.mfo.chatapp.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.mfo.chatapp.R
import com.mfo.chatapp.databinding.ActivitySignupBinding
import com.mfo.chatapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        signUpViewModel.singUpState.observe(this) { state ->
            when(state) {
                is Resource.Success -> {
                    handleLoading(isLoading = false)
                    Toast.makeText(
                        this,
                        "Sign up success",
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
            btnRegister.setOnClickListener {
                handleSignUp()
            }
        }
    }

    private fun handleSignUp() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        signUpViewModel.signUp(email, password)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.apply {
            if(isLoading) {
                pbSignUp.isVisible = true
                etEmail.isVisible = false
                etPassword.isVisible = false
                btnRegister.isVisible = false
            } else {
                pbSignUp.isVisible = false
                etEmail.isVisible = true
                etPassword.isVisible = true
                btnRegister.isVisible = true
            }
        }
    }
}