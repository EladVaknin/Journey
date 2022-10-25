package com.example.myjourney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myjourney.R;
import com.example.myjourney.useful.CacheUtilities;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HealthDetailsActivity extends AppCompatActivity {
    private TextView mGender ,mAge,mHeight,mWeight;
    private Button mUpdateButton;
    private static final String USERS_TABLE = "users";
    private final DatabaseReference mDbUser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_details);
        initViews();
    }

    private void initViews() {
        mAge = findViewById(R.id.ageTextView);
        mAge.setText(mAge.getText()+ CacheUtilities.getAge(this));

        mGender = findViewById(R.id.genderTextView);
        mGender.setText(mGender.getText()+CacheUtilities.getGender(this));

        mHeight = findViewById(R.id.HeightTextView);
        mHeight.setText(mHeight.getText()+CacheUtilities.getHeight(this) + "cm");

        mWeight = findViewById(R.id.WeightTextView);
        mWeight.setText(mWeight.getText()+CacheUtilities.getHeight(this) + "kg");

        mUpdateButton = findViewById(R.id.UpdateDetailsButton);
        mUpdateButton.setOnClickListener(v -> redictToUpdateDetailsScreen ());
    }

    private void redictToUpdateDetailsScreen() {
            Intent intent =new Intent(HealthDetailsActivity.this,UpdateDetailsActivity.class);
            startActivity(intent);
        }
    }

