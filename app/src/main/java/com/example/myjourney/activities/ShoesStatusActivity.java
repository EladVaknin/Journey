package com.example.myjourney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ShoesStatusActivity extends AppCompatActivity {
    private AlertDialog.Builder dialogBuildr;
    private AlertDialog dialog;
    private TextView mShoesEditText ;
    private Button mSetNewShoesButton;
    private EditText mSetShoesEditTextPopup ;
    private Button mSaveButtonPopup;
    private static final String USERS_TABLE = "users";
    private final DatabaseReference mDBuser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_status);
        initViews();
    }

    private void initViews() {
        mShoesEditText = findViewById(R.id.ShoesStatusTextView);
        ///// when i will develop the main algo i need to add the last result  to this field and represent the new range
        mShoesEditText.setText(mShoesEditText.getText()+ CacheUtilities.getShoes(this)+" km");

        mSetNewShoesButton = findViewById(R.id.SetNewShoesButton);
        mSetNewShoesButton.setOnClickListener(v -> redictToPopUp ());
    }

    private void redictToPopUp() {
        dialogBuildr = new AlertDialog.Builder(this);
        final View shoesPopupView = getLayoutInflater().inflate(R.layout.activity_popup,null);

        mSetShoesEditTextPopup = (EditText) shoesPopupView.findViewById(R.id.editTextShoesStatusPopUp);
        mSaveButtonPopup = shoesPopupView.findViewById(R.id.SaveStatusPopUpButton);

        dialogBuildr.setView(shoesPopupView);
        dialog =dialogBuildr.create();
        dialog.show();

        mSaveButtonPopup.setOnClickListener(v -> SaveAndRedictToProfile());

    }

    private void SaveAndRedictToProfile() {
        String shoes = mSetShoesEditTextPopup.getText().toString();
        if (mSetShoesEditTextPopup == null){
            Toast.makeText(this,"The field is empty",Toast.LENGTH_SHORT).show();
            return;

        }else {
            CacheUtilities.cacheShoes(this, shoes);
            mDBuser.child(mAuth.getCurrentUser().getUid()).child("shoes").setValue(shoes);
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            finish();
        }
    }

}



