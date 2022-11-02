package com.example.myjourney.activities;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.myjourney.adapter.CoachRecyclerAdapter;
import com.example.myjourney.models.UserCoach;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchCoachActivity extends AppCompatActivity implements CoachRecyclerAdapter.CoachClickListener{

    public static final String USERS_TABLE = "users";
//    private static final String TAG = "SearchActivity";
    private EditText mSearchEditText;
    private Button mSearchButton;
    private ProgressBar mProgressBar;
    private final DatabaseReference mDbUser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);
    private CoachRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;




    /////////////////////////////////////////////////////////////////////
    ///////////- change item to coach logic
    /////// need to made tests

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
        mSearchButton.setOnClickListener(v -> performSearch());

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CoachRecyclerAdapter(this);
        mAdapter.setClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
    }


    private void recyclerViewShow(boolean shouldShow) {
        if (mRecyclerView != null && mProgressBar != null) {
            mRecyclerView.setVisibility(shouldShow ? View.VISIBLE : View.INVISIBLE);
            mProgressBar.setVisibility(shouldShow ? View.INVISIBLE : View.VISIBLE);
        }
    }
    private void performSearch() {
        recyclerViewShow(false);
        final String searchString = mSearchEditText.getText().toString();
        mDbUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<UserCoach> CoachList = new ArrayList<>();
                for (DataSnapshot CoachUser : dataSnapshot.getChildren()) {
                    if (CoachUser.child("coachUserName").exists()) {      // if coach user is in firebase
                        System.out.println("Test Test Test");
                            String searchCoachUserName = (String) CoachUser.child("coachUserName").getValue(); // search by name
                            String searchCoachAddress = (String) CoachUser.child("address").getValue(); //search by address

//                            if (searchCoachAddress.contains(searchString) || searchCoachUserName.contains(searchString)) {
                                String age = (String) CoachUser.child("age").getValue();
                                String imageUrl = (String) CoachUser.child("profileUrl").getValue();
                                String address = (String) CoachUser.child("address").getValue();
                                String experience = (String) CoachUser.child("experience").getValue();
                                String education = (String) CoachUser.child("education").getValue();
                                String CoachUserName = (String) CoachUser.child("coachUserName").getValue();
                                String gender = (String) CoachUser.child("gender").getValue();
                                // not printing - it's dont enter to the if
                                System.out.println("age  ="+age);
                                System.out.println("address  ="+address);
                                System.out.println("experience  ="+experience);
                                System.out.println("coach user name  ="+CoachUserName);
                                System.out.println("image = " + imageUrl);
//                                CoachList.add(new UserCoach(CoachUserName, imageUrl, age, address, experience,education));
                                CoachList.add(new UserCoach(imageUrl, CoachUserName, experience,education,age,address));
                            }
//                        }
                    }
                mAdapter.setNewItems(CoachList);
                recyclerViewShow(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mAdapter.clearAllData();
                recyclerViewShow(true);
            }
        });
    }

//    @Override
    public void onCoachClick(UserCoach coach) {
        Intent intent = new Intent(SearchCoachActivity.this, MessengerActivity.class);
        intent.putExtra(MessengerActivity.SEND_TO_KEY, coach.getCoachUserName());
        startActivity(intent);
    }



}
