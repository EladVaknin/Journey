package com.example.myjourney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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

public class PopUpActivity extends AppCompatActivity {

    private TextView mSetShoesEditText ;
    private Button mSaveButton;
    private static final String USERS_TABLE = "users";
    private final DatabaseReference mDBuser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private AlertDialog.Builder dialogBuildr;
    private AlertDialog dialog;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        initViews();
    }

    private void initViews() {
        mSetShoesEditText =findViewById(R.id.editTextShoesStatusPopUp);


        mSaveButton =findViewById(R.id.SaveStatusPopUpButton);
        mSaveButton.setOnClickListener(v -> preformSaveShoes());
    }

    private void preformSaveShoes() {
        String shoes = mSetShoesEditText.getText().toString();

        if (mSetShoesEditText == null){
            Toast.makeText(this,"The field is empty",Toast.LENGTH_SHORT).show();
            return;

        }else {
            CacheUtilities.cacheShoes(this, shoes);
            mDBuser.child(mAuth.getCurrentUser().getUid()).setValue(shoes);
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    }

}
