package com.example.myjourney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myjourney.R;

public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton ,mRegisterButton ,mAbousUsButton;
    private EditText mEmailEditText, mPasswordEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        mLoginButton = findViewById(R.id.LoginButton);
        mRegisterButton = findViewById(R.id.RegisterButton);
        mRegisterButton.setOnClickListener(v -> redirectToRegisterScreen());
        mAbousUsButton =findViewById(R.id.aboutUsButtom);
        mAbousUsButton.setOnClickListener(v -> redirectToAboutUsScreen() );
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
}
