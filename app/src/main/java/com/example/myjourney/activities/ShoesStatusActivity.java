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
    private TextView mShoesEditText ;
    private Button mSetNewShoesButton;
    private EditText mSetShoesEditText ;
    private Button mSaveButton;
    private static final String USERS_TABLE = "users";
    private final DatabaseReference mDBuser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private AlertDialog.Builder dialogBuildr;
    private AlertDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_status);
        initViews();
    }

    private void initViews() {
        mShoesEditText = findViewById(R.id.ShoesStatusTextView);
        mShoesEditText.setText(mShoesEditText.getText()+ CacheUtilities.getShoes(this)+"km");
//        mShoesEditText.setText(mShoesEditText.getText()+"km");

        mSetNewShoesButton = findViewById(R.id.ShoesStatusButton);
//        mSetNewShoesButton.setOnClickListener(v -> redictToPopUp ());
    }

//    private void redictToPopUp() {
//        dialogBuildr = new AlertDialog.Builder(this);
//        final View shoesPopupView = getLayoutInflater().inflate(R.layout.activity_popup,null);
//
//        mSetShoesEditText = (EditText) shoesPopupView.findViewById(R.id.editTextShoesStatusPopUp);
//        mSaveButton = shoesPopupView.findViewById(R.id.SaveStatusPopUpButton);
//
//        dialogBuildr.setView(shoesPopupView);
//        dialog =dialogBuildr.create();
//        dialog.show();
//
//        mSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String shoes = mSetShoesEditText.getText().toString();
//
//                if (mSetShoesEditText == null){
////                    Toast.makeText(this,"The field is empty",Toast.LENGTH_SHORT).show();
//                    return;
//                }else {
////                    CacheUtilities.cacheShoes(this, shoes);
//                    mDBuser.child(mAuth.getCurrentUser().getUid()).setValue(shoes);
//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                    finish();
//                }
//
//            }
//        });
//
//
//
//
//
////        Intent intent =new Intent(ShoesStatusActivity.this,PopUpActivity.class);
////        startActivity(intent);
//    }
}
