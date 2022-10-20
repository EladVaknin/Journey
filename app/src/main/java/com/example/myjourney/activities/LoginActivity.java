package com.example.myjourney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myjourney.R;
import com.example.myjourney.useful.CacheUtilities;
import com.example.myjourney.useful.practical;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton ,mRegisterButton ,mAboutsUsButton;
    private EditText mEmailEditText, mPasswordEditText;
    private final DatabaseReference mDbUser = FirebaseDatabase.getInstance().getReference(RegisterActivity.USER_TABLE);
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        mLoginButton = findViewById(R.id.LoginButton);
        mLoginButton.setOnClickListener(v -> performLogin());
        mRegisterButton = findViewById(R.id.RegisterButton);
        mRegisterButton.setOnClickListener(v -> redirectToRegisterScreen());
        mAboutsUsButton =findViewById(R.id.aboutUsButtom);
        mAboutsUsButton.setOnClickListener(v -> redirectToAboutUsScreen() );
        mEmailEditText=findViewById(R.id.editTextTextEmailAddress);
        mPasswordEditText=findViewById(R.id.editTextNumberPassword);

    }




    public void redirectToRegisterScreen() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void redirectToAboutUsScreen (){
        Intent intent = new Intent(LoginActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }

    private void performLogin() {
        String Admin = "Admin@gmail.com";
        String adminPassword = "12345678";
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        if (practical.ValidChecksEmailAndPassword(this,email,password)) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
//                    if (email.equals(Admin)&&password.equals(adminPassword)){
//                        redirectToAdminScreen();
//                    }
//                    else{
                        getUserDetailsFromFireBaseAndRedirectToProfileActivity();
                    }
//                }
            });
        }
    }

    private void getUserDetailsFromFireBaseAndRedirectToProfileActivity() {
        mDbUser.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                CacheUtilities.cacheUserName(LoginActivity.this, (String) userSnapshot.child("userName").getValue());
                CacheUtilities.cacheHeight(LoginActivity.this, (String) userSnapshot.child("height").getValue());
                CacheUtilities.cacheWeight(LoginActivity.this, (String) userSnapshot.child("weight").getValue());
                CacheUtilities.cacheAge(LoginActivity.this, (String) userSnapshot.child("age").getValue());
                CacheUtilities.cacheGender(LoginActivity.this, (String) userSnapshot.child("gender").getValue());
                if (userSnapshot.child("profileUrl").exists()) {
                    CacheUtilities.cacheImageProfile(LoginActivity.this, (String) userSnapshot.child("profileUrl").getValue());
                }
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            finish();
        }
    }
}
