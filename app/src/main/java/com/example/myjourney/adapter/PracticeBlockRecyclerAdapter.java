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
import com.example.myjourney.models.PracticeBlock;
import com.example.myjourney.models.UserCoach;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class PracticeBlockRecyclerAdapter extends RecyclerView.Adapter<PracticeBlockRecyclerAdapter.PracticeBlockViewHolder> {

        private final List<PracticeBlock> mData = new ArrayList<>();
        private final LayoutInflater mInflater;
        private PracticeBlockClickListener mClickListener;


        // data is passed into the constructor
        public PracticeBlockRecyclerAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public PracticeBlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.practic_block_row, parent, false);
            return new PracticeBlockViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PracticeBlockViewHolder holder, int position) {
            PracticeBlock block = mData.get(position);
            holder.mWeekTextView.setText(holder.mWeekTextView.getText() +" " + block.getWeek());
            holder.mPaceTextView.setText(holder.mPaceTextView.getText()+" "+ block.getPace());
            holder.mDistanceTextView.setText(holder.mDistanceTextView.getText()+" "+block.getDistance());
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

        public void setNewItems(List<PracticeBlock> BlockList) {
            mData.clear();
            mData.addAll(BlockList);
            notifyDataSetChanged();
        }


        // stores and recycles views as they are scrolled off screen
        public class PracticeBlockViewHolder extends RecyclerView.ViewHolder {
            ImageView mItemImage;
            ImageView mConfirmButton;
            TextView mWeekTextView;
            TextView mDistanceTextView;
            TextView mPaceTextView;



            PracticeBlockViewHolder(View blockView) {
                super(blockView);
                mWeekTextView =blockView.findViewById(R.id.NumWeek);
                mDistanceTextView =blockView.findViewById(R.id.PracticeDistance);
                mPaceTextView =blockView.findViewById(R.id.PracticePace);
                mItemImage = blockView.findViewById(R.id.image_item);

                mConfirmButton =blockView.findViewById(R.id.Confirm_button);
                mConfirmButton.setOnClickListener(v -> mClickListener.onPracticeBlockClick(mData.get(getAdapterPosition())));

            }
        }


        // allows clicks events to be caught
        public void setClickListener(PracticeBlockClickListener practiceBlockClickListener) {
            this.mClickListener = practiceBlockClickListener;
        }



        // parent activity will implement this method to respond to click events
        public interface PracticeBlockClickListener {
            void onPracticeBlockClick(PracticeBlock Block);
        }

    }


