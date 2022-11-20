package com.example.myjourney.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myjourney.R;

public class MyJourneyActivity extends AppCompatActivity {


//// in this class i will show the results of the journey calculate in recycler view (like the search class)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_journey_album);
        initViews();
    }

    private void initViews() {
    }
}
