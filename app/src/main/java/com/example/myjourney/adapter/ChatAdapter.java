package com.example.myjourney.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myjourney.R;
import com.example.myjourney.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {


    private final List<ChatMessage> chatMessageList;
    private final String senderId;
    public static final int VIEW_TYPE_SEND = 1;
    public static final int VIEW_TYPE_RECIVED = 2;

    public ChatAdapter(List<ChatMessage> chatMessageList, String senderId) {
        this.chatMessageList = chatMessageList;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SEND) {
            return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_message, parent, false));
        } else {
            return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reciver_message, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.mTextMsg.setText(chatMessageList.get(position).message);
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessageList.get(position).senderId.equals(senderId)) {
            return VIEW_TYPE_SEND;
        } else {
            return VIEW_TYPE_RECIVED;
        }
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }


    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextMsg;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextMsg = itemView.findViewById(R.id.textMsg);
        }
    }

}
