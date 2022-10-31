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
import com.example.myjourney.models.UserCoach;
import com.example.myjourney.models.UserRegular;
import com.example.myjourney.useful.CacheUtilities;
import com.example.myjourney.useful.CoachCacheUtilities;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CoachRegisterActivity extends AppCompatActivity {
    private EditText mCoachUserNameEditText, mPasswordEditText, mEmailEditText, mGenderEditText,mAgeEditText,mExperienceEditText,mEducationEditText;
    private Button mRegisterButton;
    private EditText mAddress;
    private ProgressBar mProgressBarRegister;
    public static final String USER_TABLE = "users";
    private final DatabaseReference mDBuser = FirebaseDatabase.getInstance().getReference(USER_TABLE);
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_register);
        initViews();
    }

    private void initViews() {
        mProgressBarRegister = findViewById(R.id.progressBarRegister);

        mRegisterButton = findViewById(R.id.SingInButtonCoach);
        mRegisterButton.setOnClickListener(v -> preformRegister());

        mCoachUserNameEditText = findViewById(R.id.editTextUserNameCoach);
        mPasswordEditText = findViewById(R.id.editTextPassword);
        mEmailEditText = findViewById(R.id.editTextEmailAddress);
        mGenderEditText =findViewById(R.id.editTextGender);
        mAgeEditText = findViewById(R.id.editTextAge);
        mEducationEditText = findViewById(R.id.editTextEducation);
        mExperienceEditText =findViewById(R.id.editTextExperience);
        mAddress = findViewById(R.id.editTextAddress);
    }

    private void preformRegister() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String userName = mCoachUserNameEditText.getText().toString();
        String gender = mGenderEditText.getText().toString();
        String age = mAgeEditText.getText().toString();
        String experience = mExperienceEditText.getText().toString();
        String education = mEducationEditText.getText().toString();
        String address = mAddress.getText().toString();

        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(email) ||TextUtils.isEmpty(gender) ||TextUtils.isEmpty(education)
                ||TextUtils.isEmpty(experience)||TextUtils.isEmpty(age)|| TextUtils.isEmpty(password)|| TextUtils.isEmpty(address)){
            Toast.makeText(this,"Some of the fields is empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if (ValidChecksEmailAndPassword(this, email, password)) {
            handleProgressBar(true);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                handleProgressBar(false);
                if (task.isSuccessful()) {
                    UserCoach coach = new UserCoach(mAuth.getCurrentUser().getUid(),userName,email,experience,education,age, gender,address);
                    CoachCacheUtilities.CoachCacheUserName(this, userName);
                    CoachCacheUtilities.cacheGender(this, gender);
                    CoachCacheUtilities.cacheEduction(this,education);
                    CoachCacheUtilities.cacheExperience(this,experience);
                    CoachCacheUtilities.cacheAge(this, age);
                    CoachCacheUtilities.cacheAddress(this,address);
                    mDBuser.child(mAuth.getCurrentUser().getUid()).setValue(coach);
                    startActivity(new Intent(getApplicationContext(), CoachProfileActivity.class));
                    finish();
                }else {
                    Toast.makeText(CoachRegisterActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
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

    private void handleProgressBar(boolean shouldDisplay) {
        mProgressBarRegister.setVisibility(shouldDisplay ? View.VISIBLE : View.INVISIBLE);
    }

}
