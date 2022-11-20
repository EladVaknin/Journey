package com.example.myjourney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myjourney.R;
import com.example.myjourney.useful.CacheUtilities;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.Map;

public class BmiActivity extends AppCompatActivity {
    private Button mUpdateDetailsButton ,mCalculateAgainButton;
    private TextView mBMIResult ,mBMIclassify;
    private ProgressBar mBmiProgressBar;
    private static final String USERS_TABLE = "users";
    private final DatabaseReference mDbUser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);    // tha argument is view type
        initViews ();
    }


    private void initViews() {
        mBmiProgressBar = findViewById(R.id.progressBarBmi);

        mBMIclassify = findViewById(R.id.ClassifyBmiTextView);
        mBMIResult = findViewById(R.id.BmiTextView);
        calculateBmi();

        mUpdateDetailsButton=findViewById(R.id.UpdateDetailsButton);
        mUpdateDetailsButton.setOnClickListener(v -> redictToUpdateDetailsScreen ());

        mCalculateAgainButton=findViewById(R.id.CalculateAgainButton);
        mCalculateAgainButton.setOnClickListener(v -> redictToCalculateAgain());

    }

    private void redictToCalculateAgain() {
        Intent intent = new Intent(BmiActivity.this,BmiActivity.class);
        startActivity(intent);
    }


    // base on - https://en.wikipedia.org/wiki/Body_mass_index
    private void calculateBmi() {

        final String BMI_CATEGORY_UNDERWEIGHT = "Underweight";
        final String BMI_CATEGORY_HEALTHY = "Healthy Weight Range";
        final String BMI_CATEGORY_OVERWEIGHT = "Overweight";
        final String BMI_CATEGORY_OBESE = "Obese";
        final int heightInCM = 100;   //cm in meter
        final DecimalFormat df = new DecimalFormat("0.00");

        String gender ;
        double BmiResult;
        double height,weight;
        int age;

        age =Integer.parseInt(CacheUtilities.getAge(this)) ;
        gender =CacheUtilities.getGender(this);
        height = Double.parseDouble(CacheUtilities.getHeight(this));
        weight=Double.parseDouble(CacheUtilities.getWeight(this));
        height = height/heightInCM;

        BmiResult = (weight/(Math.pow(height,2)));
        mBMIResult.setText(mBMIResult.getText()+df.format(BmiResult));


        // classify BMI
        if (BmiResult < 18.5) {
            mBMIclassify.setText(mBMIclassify.getText()+BMI_CATEGORY_UNDERWEIGHT);
        } else if (BmiResult >= 18.5 && BmiResult < 25) {
            mBMIclassify.setText(mBMIclassify.getText()+BMI_CATEGORY_HEALTHY);
        } else if (BmiResult >= 25 && BmiResult < 30){
            mBMIclassify.setText(mBMIclassify.getText()+BMI_CATEGORY_OVERWEIGHT);
        } else {
            mBMIclassify.setText(mBMIclassify.getText()+BMI_CATEGORY_OBESE);
        }

    }

    private void redictToUpdateDetailsScreen() {
        Intent intent =new Intent(BmiActivity.this,UpdateDetailsActivity.class);
        startActivity(intent);
    }


    private void handleProgressBar(boolean shouldDisplay) {
        mBmiProgressBar.setVisibility(shouldDisplay ? View.VISIBLE : View.INVISIBLE);
    }


}
