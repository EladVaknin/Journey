package com.example.myjourney.activities;

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

public class UpdateCoachDetailsActivity extends AppCompatActivity  {

    private EditText mUserNameEditText, mGenderEditText,mAgeEditText,mExperienceEditText,mEducationEditText;
    private Button mUpdateButton;
    private ProgressBar mProgressBarRegister;
    public static final String USER_TABLE = "users";
    private final DatabaseReference mDBuser = FirebaseDatabase.getInstance().getReference(USER_TABLE);
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        initViews();
    }


    private void initViews() {
        mProgressBarRegister = findViewById(R.id.progressBarUpdate);

        mUpdateButton = findViewById(R.id.UpdateButton);
        mUpdateButton.setOnClickListener(v -> preformUpdate());

        mUserNameEditText = findViewById(R.id.editTextUserNameUpdate);
        mGenderEditText =findViewById(R.id.editTextGenderUpdate);
        mAgeEditText = findViewById(R.id.editTextAgeUpdate);
        mExperienceEditText = findViewById(R.id.editTextExperienceUpdate);
        mEducationEditText = findViewById(R.id.editTextEducationUpdate);
    }

    private void preformUpdate() {

        String userName = mUserNameEditText.getText().toString();
        String gender = mGenderEditText.getText().toString();
        String experience = mExperienceEditText.getText().toString();
        String eduction = mEducationEditText.getText().toString();
        String age = mAgeEditText.getText().toString();


        if (TextUtils.isEmpty(userName) ||TextUtils.isEmpty(gender) ||TextUtils.isEmpty(experience)||TextUtils.isEmpty(eduction)||TextUtils.isEmpty(age)){
            Toast.makeText(this,"One of the fields is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        handleProgressBar(true);


        mAuth.updateCurrentUser(mAuth.getCurrentUser()).addOnCompleteListener(task ->{
            handleProgressBar(false);
            if (task.isSuccessful()) {
                UserCoach coach = new UserCoach(mAuth.getCurrentUser().getUid(),userName, gender,eduction,experience,age);
                CoachCacheUtilities.CoachCacheUserName(this, userName);
                CoachCacheUtilities.cacheGender(this, gender);
                CoachCacheUtilities.cacheEduction(this,eduction);
                CoachCacheUtilities.cacheExperience(this,experience);
                CoachCacheUtilities.cacheAge(this, age);
                mDBuser.child(mAuth.getCurrentUser().getUid()).setValue(coach);
                startActivity(new Intent(getApplicationContext(), CoachProfileActivity.class));
                finish();
            }else {
                Toast.makeText(UpdateCoachDetailsActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleProgressBar(boolean shouldDisplay) {
        mProgressBarRegister.setVisibility(shouldDisplay ? View.VISIBLE : View.INVISIBLE);
    }


}

