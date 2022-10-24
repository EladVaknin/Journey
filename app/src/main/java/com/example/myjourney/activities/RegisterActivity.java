package com.example.myjourney.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myjourney.R;
import com.example.myjourney.models.UserRegular;
import com.example.myjourney.useful.CacheUtilities;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUserNameEditText, mPasswordEditText, mEmailEditText, mGenderEditText,mAgeEditText,mWeightEditText,mGHeightEditText;
    private Button mRegisterButton,mCoachRegisterButton;
    private ProgressBar mProgressBarRegister;
    public static final String USER_TABLE = "users";
    private final DatabaseReference mDBuser = FirebaseDatabase.getInstance().getReference(USER_TABLE);
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }



    private void initViews() {
        mProgressBarRegister = findViewById(R.id.progressBarRegister);
        mRegisterButton = findViewById(R.id.SingInButton);
        mRegisterButton.setOnClickListener(v -> preformRegister());
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

    private void preformRegister() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String userName = mUserNameEditText.getText().toString();
        String gender = mGenderEditText.getText().toString();
        String weight = mWeightEditText.getText().toString();
        String height = mGHeightEditText.getText().toString();
        String age = mAgeEditText.getText().toString();

        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(email) ||TextUtils.isEmpty(gender) ||TextUtils.isEmpty(weight)
                ||TextUtils.isEmpty(height)||TextUtils.isEmpty(age)|| TextUtils.isEmpty(password)){
            Toast.makeText(this,"One of the fields is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (ValidChecksEmailAndPassword(this, email, password)) {
            handleProgressBar(true);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                handleProgressBar(false);
                if (task.isSuccessful()) {
                    UserRegular user = new UserRegular(mAuth.getCurrentUser().getUid(),userName,email, gender,weight,height,age);
                    CacheUtilities.cacheUserName(this, userName);
                    CacheUtilities.cacheGender(this, gender);
                    CacheUtilities.cacheWeight(this, weight);
                    CacheUtilities.cacheHeight(this, height);
                    CacheUtilities.cacheAge(this, age);
                    mDBuser.child(mAuth.getCurrentUser().getUid()).setValue(user);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }else {
                    Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void handleProgressBar(boolean shouldDisplay) {
        mProgressBarRegister.setVisibility(shouldDisplay ? View.VISIBLE : View.INVISIBLE);
    }



    public void redirectToCoachUsScreen (){
        Intent intent = new Intent(RegisterActivity.this, CoachRegisterActivity.class);
        startActivity(intent);
    }



    public static boolean ValidChecksEmailAndPassword(Context context, String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(context, "Empty password or Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 8) {
            Toast.makeText(context, "Password length must be bigger than 8", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



}