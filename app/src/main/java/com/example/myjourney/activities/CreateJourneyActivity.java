package com.example.myjourney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myjourney.R;
import com.example.myjourney.useful.CacheUtilities;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateJourneyActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuildr;
    private AlertDialog dialog;
    private TextView mExplainEditText ;
    private Button mNextButtonPopup;
    private static final String USERS_TABLE = "users";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_journey);
        redictToPopUp();
    }

    private void redictToPopUp() {
        dialogBuildr = new AlertDialog.Builder(this);
        final View CreatePopupView = getLayoutInflater().inflate(R.layout.activity_create_journey_popup,null);

        mExplainEditText =  CreatePopupView.findViewById(R.id.textViewExplainPopup);
        mNextButtonPopup = CreatePopupView.findViewById(R.id.NextPopUpButton);

        dialogBuildr.setView(CreatePopupView);
        dialog =dialogBuildr.create();
        dialog.show();

        mNextButtonPopup.setOnClickListener(v -> redictToTakeDetailsToCreateAJourneyScreen());

    }

    private void redictToTakeDetailsToCreateAJourneyScreen() {
        Intent intent = new Intent(CreateJourneyActivity.this ,TakeDetailsForJourneyActivity.class);
        startActivity(intent);
    }


}
