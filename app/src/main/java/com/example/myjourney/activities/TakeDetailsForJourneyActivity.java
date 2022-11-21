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
        String gender = CacheUtilities.getGender(this);


        // checks if we got valid details
        CheckValidTarget();
        // check the  range of current Running week range and status inputs.
        CheckValidCurrentRange();


        // add the new details to firebase
        if (CheckValidTarget() == true && CheckValidCurrentRange() == true) {
            mDBuser.child(mAuth.getCurrentUser().getUid()).child("target").setValue(target);
            mDBuser.child(mAuth.getCurrentUser().getUid()).child("pace status").setValue(paceStatus);
            mDBuser.child(mAuth.getCurrentUser().getUid()).child("current running status").setValue(currentRunningStatus);
        }


        /////// main algo
        int weeks = classifyWeeks();    // how many weeks i need to prepare the runner to the target
        int group = classifyGroup();   // the groups by age,pace status, current running status.

        MainAlgo(weeks,group);
    }

    private void MainAlgo(int weeks, int group) {
        String target = mTargetText.getText().toString();
        String paceStatus = mPaceStatus.getText().toString();
        String currentRunningStatus = mRunningStatus.getText().toString();
        String age = CacheUtilities.getAge(this);
        String gender = CacheUtilities.getGender(this);

        // i need to create child in firebase that hold all the practice block

        while (weeks == 0){   // we build the program from end to start
            if (group == 1){     // or we need to check the if and then do the while
                //// do something and create practice block
                weeks--;
            }else if(group ==2){
                //// do something and create practice block
                weeks--;
            }else if(group ==3){
                //// do something and create practice block
                weeks--;
            }else if(group ==4){
                //// do something and create practice block
                weeks--;
            }else if(group ==5){
                //// do something and create practice block
                weeks--;
            }

        }


    }

    private boolean CheckValidTarget() {
        String target = mTargetText.getText().toString();
        String paceStatus = mPaceStatus.getText().toString();
        String currentRunningStatus = mRunningStatus.getText().toString();
        int minOpt = 5;
        int midOpt = 10;
        double maxOpt = 21.2;
        int intTmpTarget = Integer.parseInt(target);
        double doubleTmpTarget = Double.parseDouble(target);

        if (intTmpTarget != minOpt || intTmpTarget != midOpt){
            Toast.makeText(this,"Please chose one from the options",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (doubleTmpTarget != maxOpt|| TextUtils.isEmpty(target)){
            Toast.makeText(this,"Please chose one from the options",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(paceStatus) || TextUtils.isEmpty(currentRunningStatus)){
            Toast.makeText(this,"One of the fields is empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean CheckValidCurrentRange() {
        String paceStatus = mPaceStatus.getText().toString();
        String currentRunningStatus = mRunningStatus.getText().toString();

        int minRun =0;
        int maxRun = 99;
        double minPace = 00.00;
        double maxPace = 99.55;
        int tmpRunStatus = Integer.parseInt(currentRunningStatus);
        double tmpPace = Double.parseDouble(paceStatus);

        if (tmpRunStatus < minRun || tmpRunStatus >= maxRun){
            Toast.makeText(this,"Please enter a valid running input",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tmpPace <= minPace || tmpPace >= maxPace){
            Toast.makeText(this,"Please enter a valid pace input",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private int classifyGroup() {
        String target = mTargetText.getText().toString();
        String paceStatus = mPaceStatus.getText().toString();
        String currentRunningStatus = mRunningStatus.getText().toString();
        String age = CacheUtilities.getAge(this);
        String gender = CacheUtilities.getGender(this);
        int group = 0;

        ///// age 19-33
        if (gender == "male" && Integer.parseInt(age) >= 19 &&Integer.parseInt(age) <= 33 && Integer.parseInt(currentRunningStatus) >= 10){
            group = 1;  // good fitness
        }else if (gender == "male" && Integer.parseInt(age) >= 19 &&Integer.parseInt(age) <= 33 && Integer.parseInt(currentRunningStatus) >= 5){
            group = 2;  // still good fitness
        }else if (gender == "male" && Integer.parseInt(age) >= 19 &&Integer.parseInt(age) <= 33 && Integer.parseInt(currentRunningStatus) == 0){
            group = 3;  // young man but bad fitness

            // /age 34 -40
        }else if (gender == "male" && Integer.parseInt(age) >= 34 &&Integer.parseInt(age) <= 40 && Integer.parseInt(currentRunningStatus) >= 10) {
            group = 4;  // adult good fitness
        } else if (gender == "male" && Integer.parseInt(age) >= 34 &&Integer.parseInt(age) <= 40 && Integer.parseInt(currentRunningStatus) >= 5) {
            group = 5;  // adult still good fitness
        }else if (gender == "male" && Integer.parseInt(age) >= 34 &&Integer.parseInt(age) <= 40 && Integer.parseInt(currentRunningStatus) == 0) {
            group = 6;  // adult but bad fitness

            ///// age 41 - 45
        }else if (gender == "male" && Integer.parseInt(age) >= 41 &&Integer.parseInt(age) <= 44 && Integer.parseInt(currentRunningStatus) >= 10) {
            group = 7;  // getting older good fitness
        } else if (gender == "male" && Integer.parseInt(age) >= 41 &&Integer.parseInt(age) <= 44 && Integer.parseInt(currentRunningStatus) >= 5) {
            group = 8;  // getting older still good fitness
        }else if (gender == "male" && Integer.parseInt(age) >= 41 &&Integer.parseInt(age) <= 44 && Integer.parseInt(currentRunningStatus) == 0) {
            group = 9;  // getting older but bad fitness

            /////// age 46-50
        }else if (gender == "male" && Integer.parseInt(age) >= 46 &&Integer.parseInt(age) <= 50 && Integer.parseInt(currentRunningStatus) >= 10) {
            group = 10;  //  older good fitness
        } else if (gender == "male" && Integer.parseInt(age) >= 46 &&Integer.parseInt(age) <= 50 && Integer.parseInt(currentRunningStatus) >= 5) {
            group = 11;  //  older still good fitness
        }else if (gender == "male" && Integer.parseInt(age) >= 46 &&Integer.parseInt(age) <= 50 && Integer.parseInt(currentRunningStatus) == 0) {
            group = 12;  //  older but bad fitness
        }

        return group;
    }

    private int classifyWeeks() {
        String target = mTargetText.getText().toString();
        String gender = CacheUtilities.getGender(this);
        int weeks =0;

        if (gender=="male") {
            if (Integer.parseInt(target) >= 21) {
                weeks = 21;
            } else if (Integer.parseInt(target) == 10) {
                weeks = 13;
            } else {
                weeks = 9;    // pepper to 5 km
            }
        }if(gender == "female"){
            if (Integer.parseInt(target) >= 21) {
                weeks = 25;
            } else if (Integer.parseInt(target) == 10) {
                weeks = 18;
            } else {
                weeks = 14;    // pepper to 5 km
            }
        }

        return weeks;
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
