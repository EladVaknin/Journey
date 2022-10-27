package com.example.myjourney.activities;

import android.os.Bundle;
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

public class TakeDetailsForJourneyActivity extends AppCompatActivity {
    private AlertDialog.Builder dialogBuildr;
    private AlertDialog dialog;
    private Button mCalculateJourneyButton;
    private EditText mTargetText,mPaceStatus,mRunningStatus ;
    private static final String USERS_TABLE = "users";
    private final DatabaseReference mDBuser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_details_to_create_journey);
        initViews();
    }

    private void initViews() {
        mPaceStatus = findViewById(R.id.editTextPaceStatus);
        mRunningStatus = findViewById(R.id.editTextKmRangeStatus);
        mTargetText = findViewById(R.id.editTextTarget);
        
        mCalculateJourneyButton =findViewById(R.id.CalculJourneyButton);
        mCalculateJourneyButton.setOnClickListener(v -> CalculatingTheMainAlgorithm ());


    }

    private void CalculatingTheMainAlgorithm() {
        // the main algorithm build on groups by this details

        String target = mTargetText.getText().toString();
        String paceStatus = mPaceStatus.getText().toString();
        String runningStatus = mRunningStatus.getText().toString();
        String age = CacheUtilities.getAge(this);


        // checks if we have valid details







        // the groups



    }

}
