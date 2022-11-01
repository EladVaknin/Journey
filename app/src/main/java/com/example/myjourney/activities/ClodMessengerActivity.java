package com.example.myjourney.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.myjourney.adapter.UserAdapter;
import com.example.myjourney.useful.CacheUtilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClodMessengerActivity extends AppCompatActivity implements UserAdapter.CoachClickListener {
    private static final String TAG = "SearchActivity";
    private static final String USERS_TABLE = "users";
    private final DatabaseReference mDbUser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);
    private EditText mSearchEditText;
    private Button mSearchButton;
    private ProgressBar mProgressBar;
    final List<String> allUsers = new ArrayList<>();
    private HashMap<String, String> userPhotos = new HashMap<>();
    private final FirebaseFirestore mDb = FirebaseFirestore.getInstance();


    private UserAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloudmessanger);
        initViews();
        fetchData();
    }

    private void fetchData() {
        mDbUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    if (user.child("profileUrl").exists()) {
                        userPhotos.put((String) user.child("userName").getValue(), (String) user.child("profileUrl").getValue());
                    }

                }
                String clientUserName = CacheUtilities.getUserName(ClodMessengerActivity.this);
                mDb.collection("Chat").

                        whereEqualTo(MessengerActivity.KEY_SEND_TO, clientUserName).

                        get().

                        addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String otherUser = (String) document.getData().get(MessengerActivity.KEY_FROM_MSG);
                                        if (!allUsers.contains(otherUser)) {
                                            allUsers.add(otherUser);
                                        }
                                    }
                                    mDb.collection("Chat").whereEqualTo(MessengerActivity.KEY_FROM_MSG, clientUserName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    String otherUser = (String) document.getData().get(MessengerActivity.KEY_SEND_TO);
                                                    if (!allUsers.contains(otherUser)) {
                                                        allUsers.add(otherUser);
                                                    }
                                                }
                                                mAdapter.setNewItems(allUsers, userPhotos);
                                                recyclerViewShow(true);
                                            }
                                        }
                                    });
                                }
                            }
                        });
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    private void initViews() {
        mProgressBar = findViewById(R.id.progressBar);
        mSearchEditText = findViewById(R.id.search_edit_text);
        mSearchButton = findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(v -> performSearch());
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UserAdapter(this);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        recyclerViewShow(false);
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
        ArrayList<String> filterArray = new ArrayList<>();
        for (String str : allUsers) {
            if (str.contains(searchString)) {
                filterArray.add(str);
            }
        }
        mAdapter.setNewItems(filterArray,userPhotos);
        recyclerViewShow(true);

    }

    @Override
    public void onUserClicked(String userName) {
        Intent intent = new Intent(ClodMessengerActivity.this, MessengerActivity.class);
        intent.putExtra(MessengerActivity.SEND_TO_KEY, userName);
        startActivity(intent);
    }
}
