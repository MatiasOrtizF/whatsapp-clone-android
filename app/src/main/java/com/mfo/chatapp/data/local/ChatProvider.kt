package com.mfo.chatapp.data.local

import com.mfo.chatapp.domain.model.Chat
import javax.inject.Inject

class ChatProvider @Inject constructor() {
    fun getChats(): List<Chat> {
        return listOf(
            Chat(
                uid = "",
                uidUser = "Dan8afpT1tJGEKsEYt8g",
                name = "RDP7",
                image = "https://firebasestorage.googleapis.com/v0/b/chat-app-d0d3e.appspot.com/o/rp7_profile.jfif?alt=media&token=c631b6e7-3beb-44fe-9501-b6f4a9e15dca",
                lastMessage = "jajaajajajaja",
                timeLastMessage = "15:48"
            ),
            Chat(
                uid = "",
                uidUser = "Q5G0BO9GSDrp2c3Qmu2f",
                name = "Messi",
                image = "https://firebasestorage.googleapis.com/v0/b/chat-app-d0d3e.appspot.com/o/messi_profile.jpeg?alt=media&token=54df62a1-0f3a-4b5f-ac92-60ae1f2fc7eb",
                lastMessage = "Oka",
                timeLastMessage = "16:28"
            ),
            Chat(
                uid = "",
                uidUser = "UhdTTwkaeZAZAOWrvbsl",
                name = "enzo",
                image = "https://firebasestorage.googleapis.com/v0/b/chat-app-d0d3e.appspot.com/o/enzo_fernandez_profile.jpg?alt=media&token=266a71d4-761b-45a2-998c-57925e411533",
                lastMessage = "Ahi estoy",
                timeLastMessage = "18:13"
            ),
            Chat(
                uid = "",
                uidUser = "UxiVmmzEvPlYBxR5PJCN",
                name = "juli",
                image = "https://firebasestorage.googleapis.com/v0/b/chat-app-d0d3e.appspot.com/o/alvarez_profile.jpg?alt=media&token=327cde35-e84c-4e7f-bbad-12db81164c6d",
                lastMessage = "Mañana vemos",
                timeLastMessage = "18:17"
            )
        )
    }
}