package com.mfo.chatapp.domain.repository

import com.mfo.chatapp.domain.model.Contact

interface ContactRepository {

    suspend fun fetchAllContacts(): Result<List<Contact>>

    suspend fun getAllContactsCache(): List<Contact>
    suspend fun insertAllContactsCache(contacts: List<Contact>)
}