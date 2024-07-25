package com.mfo.chatapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfo.chatapp.domain.model.User
import com.mfo.chatapp.domain.repository.UserRepository
import com.mfo.chatapp.domain.usecase.FirebaseLoginUseCase
import com.mfo.chatapp.domain.usecase.FirebaseSignUpUseCase
import com.mfo.chatapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.PrimitiveIterator
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val loginUseCase: FirebaseLoginUseCase,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginState: MutableLiveData<Resource<User>> = MutableLiveData()
    val loginState: LiveData<Resource<User>>
        get() = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password).onEach {state ->
                _loginState.value = state
            }.launchIn(viewModelScope)
        }
    }

    /*fun saveUserSelection(userId: String) {
        viewModelScope.launch {
            userRepository.saveUserId(userId)
        }
    }*/
}