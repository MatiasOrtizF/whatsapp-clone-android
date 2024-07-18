package com.mfo.chatapp.utils

sealed class Resource <out R>{
    data class Success<out T>(val date: T): Resource<T>()
    data class Error(val message: String): Resource<Nothing>()
    object Loading: Resource<Nothing>()
    object Finished: Resource<Nothing>()
}