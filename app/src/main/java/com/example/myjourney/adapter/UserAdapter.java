package com.example.myjourney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myjourney.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemViewHolder> {

    private final List<String> mData = new ArrayList<>();
    private final HashMap<String,String> mPhotoUrlsHashMap= new HashMap();
    private final LayoutInflater mInflater;
    private CoachClickListener mClickListener;

    // data is passed into the constructor
    public UserAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String userName = mData.get(position);
        holder.mUserName.setText(userName);
        if(mPhotoUrlsHashMap.containsKey(userName)){
            Picasso.get().load(mPhotoUrlsHashMap.get(userName)).noPlaceholder().into(holder.mPhoto);
        }

    }


    // total number of rows
    @Override     // Override from recycler view class
    public int getItemCount() {
        return mData.size();
    }

    public void clearAllData() {
        mData.clear();
        notifyDataSetChanged();

    }

    public void setNewItems(List<String> itemList, HashMap<String,String> profileUrls) {
        mPhotoUrlsHashMap.clear();
        mPhotoUrlsHashMap.putAll(profileUrls);
        mData.clear();
        mData.addAll(itemList);
        notifyDataSetChanged();
    }


    // stores and recycles views as they are scrolled off screen
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mUserName;
        ImageView mPhoto;

        ItemViewHolder(View itemView) {
            super(itemView);
            mPhoto = itemView.findViewById(R.id.photoUrl);
            mUserName = itemView.findViewById(R.id.userNameTextView);
            mUserName.setOnClickListener(v -> mClickListener.onUserClicked(mData.get(getAdapterPosition())));
        }
    }


    // allows clicks events to be caught
    public void setClickListener(CoachClickListener CoachClickListener) {
        this.mClickListener = CoachClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface CoachClickListener {
        void onUserClicked(String userName);
    }
}
