package com.mfo.chatapp.ui.splash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.mfo.chatapp.data.remote.util.Constants.SHARED_EMAIL
import com.mfo.chatapp.data.remote.util.Constants.SHARED_PASSWORD
import com.mfo.chatapp.ui.home.HomeActivity
import com.mfo.chatapp.ui.login.LoginActivity
import com.mfo.chatapp.ui.login.LoginViewModel
import com.mfo.chatapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()

        if(isUserSaved()) {
            loginViewModel.login(getSavedEmail()!!, getSavedPassword()!!)
        } else {
            handleGoToLogin()
        }
    }

    private fun initUI() {
        initObservers()
    }

    private fun initObservers() {
        loginViewModel.loginState.observe(this) { state ->
            when(state) {
                is Resource.Success -> {
                    handleGoToHome()
                    //loginViewModel.getUserData()
                }
                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        "Los datos alamacenados ya no son validos",
                        Toast.LENGTH_SHORT
                    ).show()
                    handleGoToLogin()
                }
                else -> Unit
            }
        }
    }

    private fun isUserSaved (): Boolean {
        return getSavedEmail()?.isNotEmpty() == true && getSavedPassword()?.isNotEmpty() == true
    }

    private fun getSavedEmail() = sharedPreferences.getString(SHARED_EMAIL, "")
    private fun getSavedPassword() = sharedPreferences.getString(SHARED_PASSWORD, "")

    private fun handleGoToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun handleGoToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}