package com.mfo.chatapp.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfo.chatapp.databinding.ItemHomeBinding
import com.mfo.chatapp.domain.model.Chat

class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemHomeBinding.bind(view)

    fun bind(chat: Chat, onItemSelected: (Chat) -> Unit) {
        val context = binding.ivProfile.context
        Glide.with(context).load(chat.image).into(binding.ivProfile)
        binding.tvName.text = chat.name
        binding.tvLastMessage.text = chat.lastMessage
        binding.tvTimeLastMessage.text = chat.timeLastMessage
    }
}