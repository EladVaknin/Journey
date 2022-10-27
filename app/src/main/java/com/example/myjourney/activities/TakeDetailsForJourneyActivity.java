package com.example.myjourney.activities;

import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        String target = mTargetText.getText().toString();
        String paceStatus = mPaceStatus.getText().toString();
        String currentRunningStatus = mRunningStatus.getText().toString();
        String age = CacheUtilities.getAge(this);


        // checks if we got valid details
        int minOpt = 5;
        int midOpt = 10;
        double maxOpt = 21.2;
        int intTmpTarget = Integer.parseInt(target);
        double doubleTmpTarget = Double.parseDouble(target);

        if (intTmpTarget != minOpt || intTmpTarget != midOpt){
            Toast.makeText(this,"Please chose one from the options",Toast.LENGTH_SHORT).show();
            return;
        }
        if (doubleTmpTarget != maxOpt|| TextUtils.isEmpty(target)){
            Toast.makeText(this,"Please chose one from the options",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(paceStatus) || TextUtils.isEmpty(currentRunningStatus)){
            Toast.makeText(this,"One of the fields is empty",Toast.LENGTH_SHORT).show();
            return;
        }


        // check the  range of current Running week range and status inputs.
        int minRun =0;
        int maxRun = 99;
        double minPace = 00.00;
        double maxPace = 99.55;
        int tmpRunStatus = Integer.parseInt(currentRunningStatus);
        double tmpPace = Double.parseDouble(paceStatus);

        if (tmpRunStatus < minRun || tmpRunStatus >= maxRun){
            Toast.makeText(this,"Please enter a valid running input",Toast.LENGTH_SHORT).show();
            return;
        }
        if (tmpPace <= minPace || tmpPace >= maxPace){
            Toast.makeText(this,"Please enter a valid pace input",Toast.LENGTH_SHORT).show();
            return;
        }
//        CheckValid();

        // add the new details to firebase
        mDBuser.child(mAuth.getCurrentUser().getUid()).child("target").setValue(target);
        mDBuser.child(mAuth.getCurrentUser().getUid()).child("pace status").setValue(paceStatus);
        mDBuser.child(mAuth.getCurrentUser().getUid()).child("current running status").setValue(currentRunningStatus);


        // the groups by age,pace status, current running status.

    }






}









































//    private void CheckValid() {
//        String target = mTargetText.getText().toString();
//        String paceStatus = mPaceStatus.getText().toString();
//        String currentRunningStatus = mRunningStatus.getText().toString();
////        int minOpt = 5;
////        int midOpt =10;
////        double maxOpt = 21.2;
////
////        int intTmpTarget = Integer.parseInt(target);
////        double doubleTmpTarget = Double.parseDouble(target);
////
////        if (intTmpTarget != minOpt ||intTmpTarget != midOpt || doubleTmpTarget != maxOpt|| TextUtils.isEmpty(target)){
////            Toast.makeText(this,"Please chose one from the options",Toast.LENGTH_SHORT).show();
////            return;
////        }
////
////        if (TextUtils.isEmpty(paceStatus) || TextUtils.isEmpty(currentRunningStatus)){
////            Toast.makeText(this,"One of the fields is empty",Toast.LENGTH_SHORT).show();
////            return;
////        }
////
////
////        // check the  range of current Running week range and status inputs.
////        int minRun =0;
////        int maxRun = 99;
////        double minPace = 00.00;
////        double maxPace = 99.55;
////        int tmpRunStatus = Integer.parseInt(currentRunningStatus);
////        double tmpPace = Double.parseDouble(paceStatus);
////
////        if (tmpRunStatus <= minRun || tmpRunStatus >= maxRun){
////            Toast.makeText(this,"Please enter a valid running input",Toast.LENGTH_SHORT).show();
////            return;
////        }
////        if (tmpPace <= minPace || tmpPace >= maxPace){
////            Toast.makeText(this,"Please enter a valid pace input",Toast.LENGTH_SHORT).show();
////            return;
////        }
//    }
