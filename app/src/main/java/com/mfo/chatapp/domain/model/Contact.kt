package com.mfo.chatapp.domain.model

const val INVALID_NUMBER = -1

data class Contact(
    val uid: String = "",
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val number: Int = INVALID_NUMBER
)
