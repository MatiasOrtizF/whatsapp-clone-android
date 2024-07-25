package com.mfo.chatapp.data.local

import android.content.SharedPreferences
import com.mfo.chatapp.data.remote.util.Constants
import com.mfo.chatapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val sharedPref: SharedPreferences
): UserRepository {
    override fun saveUserId(userId: String) {
        sharedPref.edit().putString(Constants.USER_ID_PREF, userId).apply()
    }

    override fun getUserId(): String {
        return sharedPref.getString(Constants.USER_ID_PREF, "")?: ""
    }
}