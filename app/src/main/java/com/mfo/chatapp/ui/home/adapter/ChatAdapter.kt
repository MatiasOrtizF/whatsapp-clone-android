package com.mfo.chatapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfo.chatapp.R
import com.mfo.chatapp.domain.model.Chat

class ChatAdapter(private var chatList: List<Chat> = emptyList(), private val onItemSelected:(Chat) -> Unit): RecyclerView.Adapter<ChatViewHolder>() {
    fun updateList(list: List<Chat>) {
        chatList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatList[position], onItemSelected)
    }

}