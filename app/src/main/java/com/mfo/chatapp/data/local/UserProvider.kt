package com.mfo.chatapp.data.local

import com.mfo.chatapp.domain.model.Contact
import javax.inject.Inject

class UserProvider @Inject constructor() {
    fun getUsers(): List<Contact> {
        return listOf(
            Contact(
                uid = "Dan8afpT1tJGEKsEYt8g",
                name = "RDP7",
                description = "7",
                image = "https://firebasestorage.googleapis.com/v0/b/chat-app-d0d3e.appspot.com/o/rp7_profile.jfif?alt=media&token=c631b6e7-3beb-44fe-9501-b6f4a9e15dca",
                number = 1123456789
            ),
            Contact(
                uid = "Q5G0BO9GSDrp2c3Qmu2f",
                name = "Messi",
                description = "Campeon del mundo",
                image = "https://firebasestorage.googleapis.com/v0/b/chat-app-d0d3e.appspot.com/o/messi_profile.jpeg?alt=media&token=54df62a1-0f3a-4b5f-ac92-60ae1f2fc7eb",
                number = 1112345678
            ),
            Contact(
                uid = "UhdTTwkaeZAZAOWrvbsl",
                name = "enzo",
                description = "chelsea",
                image = "https://firebasestorage.googleapis.com/v0/b/chat-app-d0d3e.appspot.com/o/enzo_fernandez_profile.jpg?alt=media&token=266a71d4-761b-45a2-998c-57925e411533",
                number = 1198765432
            ),
            Contact(
                uid = "UxiVmmzEvPlYBxR5PJCN",
                name = "juli",
                description = "araña",
                image = "https://firebasestorage.googleapis.com/v0/b/chat-app-d0d3e.appspot.com/o/alvarez_profile.jpg?alt=media&token=327cde35-e84c-4e7f-bbad-12db81164c6d",
                number = 1155443322
            )
        )
    }
}