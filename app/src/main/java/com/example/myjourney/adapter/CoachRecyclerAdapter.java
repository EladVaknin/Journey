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
import com.example.myjourney.models.UserCoach;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CoachRecyclerAdapter extends RecyclerView.Adapter<CoachRecyclerAdapter.CoachViewHolder> {

    private final List<UserCoach> mData = new ArrayList<>();
    private final LayoutInflater mInflater;
    private CoachClickListener mClickListener;
    private CoachClickListener mClickListener2;

    // data is passed into the constructor
    public CoachRecyclerAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CoachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.coach_row, parent, false);
        return new CoachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoachViewHolder holder, int position) {
        UserCoach Coach = mData.get(position);
        holder.mAddressTextView.setText(holder.mAddressTextView.getText()+" "+Coach.getAddress());
        holder.mAgeTextView.setText(holder.mAgeTextView.getText()+" "+Coach.getAge());
        holder.mCoachUserNameTextView.setText(holder.mCoachUserNameTextView.getText()+" "+Coach.getCoachUserName());
        holder.mEducationTextView.setText(holder.mEducationTextView.getText()+" "+Coach.getEducation());
        holder.mExpirenceTextView.setText(holder.mExpirenceTextView.getText()+" "+Coach.getExperience());
        Picasso.get().load(Coach.getPicture()).into(holder.mItemImage);
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void clearAllData() {
        mData.clear();
        notifyDataSetChanged();

    }

    public void setNewItems(List<UserCoach> CoachList) {
        mData.clear();
        mData.addAll(CoachList);
        notifyDataSetChanged();
    }


    // stores and recycles views as they are scrolled off screen
    public class CoachViewHolder extends RecyclerView.ViewHolder {
        ImageView mItemImage;
        ImageView mChatButton;
        TextView mAddressTextView;
        TextView mAgeTextView;
        TextView mCoachUserNameTextView;
        TextView mEducationTextView;
        TextView mExpirenceTextView;


        CoachViewHolder(View coachView) {
            super(coachView);
            mAddressTextView = coachView.findViewById(R.id.CoachAddress);
            mAgeTextView = coachView.findViewById(R.id.CoachAge);
            mExpirenceTextView = coachView.findViewById(R.id.CoachExperience);
            mEducationTextView = coachView.findViewById(R.id.CoachEducation);
            mCoachUserNameTextView = coachView.findViewById(R.id.CoachUserName);
            mItemImage = coachView.findViewById(R.id.image_item);

            mChatButton = coachView.findViewById(R.id.chat_button);
            mChatButton.setOnClickListener(v -> mClickListener.onCoachClick(mData.get(getAdapterPosition())));

        }
    }


    // allows clicks events to be caught
    public void setClickListener(CoachClickListener coachClickListener) {
        this.mClickListener = coachClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface CoachClickListener {
        void onCoachClick(UserCoach Coach);
    }

}
