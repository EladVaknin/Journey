package com.example.myjourney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myjourney.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUserNameEditText, mPasswordEditText, mEmailEditText, mGenderEditText,mAgeEditText,mWeightEditText,mGHeightEditText;
    private Button mRegisterButton,mCoachRegisterButton;
    private ProgressBar mProgressBarRegister;
    public static final String USER_TABLE = "users";
    private final DatabaseReference mDBuser = FirebaseDatabase.getInstance().getReference(USER_TABLE);
    //private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }



    private void initViews() {
        mProgressBarRegister = findViewById(R.id.progressBarRegister);
        mRegisterButton = findViewById(R.id.SingInButton);
        mCoachRegisterButton = findViewById(R.id.RegisterCoachButton);
        mCoachRegisterButton.setOnClickListener(v -> redirectToCoachUsScreen());
        mUserNameEditText = findViewById(R.id.editTextUserNameR);
        mPasswordEditText = findViewById(R.id.editTextPassword);
        mEmailEditText = findViewById(R.id.editTextEmailAddress);
        mGenderEditText =findViewById(R.id.editTextGender);
        mAgeEditText = findViewById(R.id.editTextAge);
        mWeightEditText = findViewById(R.id.editTextWeight);
        mGHeightEditText = findViewById(R.id.editTextHeight);
    }


    private void handleProgressBar(boolean shouldDisplay) {
        mProgressBarRegister.setVisibility(shouldDisplay ? View.VISIBLE : View.INVISIBLE);
    }



    public void redirectToCoachUsScreen (){
        Intent intent = new Intent(RegisterActivity.this, CoachRegisterActivity.class);
        startActivity(intent);
    }



}