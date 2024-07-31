package com.mfo.chatapp.domain.model

data class Chat (
    val uid: String = "",
    val uidUser: String = "",
    val image: String = "",
    val name: String = "",
    val lastMessage: String = "",
    val timeLastMessage: String = ""
)