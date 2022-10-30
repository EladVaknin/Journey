package com.example.myjourney.activities;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myjourney.R;
import com.example.myjourney.adapter.ItemRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchCoachActivity extends AppCompatActivity {

    public static final String USERS_TABLE = "users";
    private static final String TAG = "SearchActivity";
    private EditText mSearchEditText;
    private Button mSearchButton;
    private ProgressBar mProgressBar;
    private final DatabaseReference mDbUser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);
    private ItemRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;




    /////////////////////////////////////////////////////////////////////
    /////////list : - work on coach fiels (for search)
    //////////////- work on search logic (how to searc and how to pull)
    ////////// - work on coach row
    ///////////- change item to coach logic

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
    }


    private void initViews() {
        mProgressBar = findViewById(R.id.progressBar);
        mSearchEditText = findViewById(R.id.search_edit_text);

        mSearchButton = findViewById(R.id.search_button);
//        mSearchButton.setOnClickListener(v -> performSearch());

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ItemRecyclerAdapter(this);
//        mAdapter.setClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
    }


    private void recyclerViewShow(boolean shouldShow) {
        if (mRecyclerView != null && mProgressBar != null) {
            mRecyclerView.setVisibility(shouldShow ? View.VISIBLE : View.INVISIBLE);
            mProgressBar.setVisibility(shouldShow ? View.INVISIBLE : View.VISIBLE);
        }
    }
  /// need to change item to coach
//    private void performSearch() {
//        recyclerViewShow(false);
//        final String searchString = mSearchEditText.getText().toString();
//        mDbUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                final List<ClipData.Item> itemList = new ArrayList<>();
//                for (DataSnapshot user : dataSnapshot.getChildren()) {
//                    if (user.child("items").exists()) {      // item to coach
//                        for (DataSnapshot item : user.child("items").getChildren()) {
//                            String description = (String) item.child("desc").getValue(); // pull all profile
//                            Log.d(TAG, "Description -" + description);
//                            if (description.contains(searchString)) {
//                                String price = (String) item.child("price").getValue();
//                                String imageUrl = (String) item.child("imageItem").getValue();
//                                String toSwitch = (String) item.child("toSwitch").getValue();
//                                String userName = (String) user.child("userName").getValue();
//                                itemList.add(new Item(description, imageUrl, toSwitch, price, userName));
//                            }
//                        }
//
//                    }
//                }
//                mAdapter.setNewItems(itemList);
//                recyclerViewShow(true);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                mAdapter.clearAllData();
//                recyclerViewShow(true);
//            }
//        });
//    }
//
//    @Override
//    public void onItemClick(ClipData.Item item) {
////        Intent intent = new Intent(SearchActivity.this, MessengerActivity.class);
////        intent.putExtra(MessengerActivity.SEND_TO_KEY, item.getUser());
////        startActivity(intent);
//    }



}