package com.example.myjourney.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myjourney.R;
import com.example.myjourney.useful.CacheUtilities;
import com.example.myjourney.useful.CoachCacheUtilities;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class CoachProfileActivity extends AppCompatActivity {

    private static final String USERS_TABLE = "users";
    private Button mLogoutButton,mUpdateDetailsButton,mChatButton;
    private ImageView mPictureImageView;
    private TextView mFullName ,mExperience ,mEducation;
    private final DatabaseReference mDbUser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_profile);
        initViews ();
    }

    private void initViews() {
        // need to sole the login bag - when you login as a user
        mEducation = findViewById(R.id.DescriptionTextView);
        mEducation.setText(mEducation.getText().toString() + CoachCacheUtilities.getEducation(this));


        mFullName = findViewById(R.id.coachNameTextView);
        mFullName.setText(mFullName.getText().toString() +CoachCacheUtilities.getCoachUserName(this));

        mExperience = findViewById(R.id.ExperienceTextView);
        mExperience.setText(mExperience.getText().toString() +CoachCacheUtilities.getExperience(this) +" years");

        mPictureImageView = findViewById(R.id.profile_image_view);
        mPictureImageView.setOnClickListener(v -> choosePictureFromGalleryAndUploadToTheFireBase());
        if (!TextUtils.isEmpty(CacheUtilities.getImageProfile(this))) {
            Picasso.get().load(CacheUtilities.getImageProfile(this)).noPlaceholder().into(mPictureImageView);
        }

        mLogoutButton = findViewById(R.id.LogoutButton);
        mLogoutButton.setOnClickListener(v -> redirectToLogout());
        
        
        mChatButton = findViewById(R.id.ChatCoachDetailsButton);
        mChatButton.setOnClickListener(v -> redirctToChat());

        mUpdateDetailsButton = findViewById(R.id.UpdateCoachDetailsButton);
        mUpdateDetailsButton.setOnClickListener(v -> redictToUpdateDetailsScreen());


    }

    private void redirctToChat() {
    }

    private void choosePictureFromGalleryAndUploadToTheFireBase() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        openGalleryIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        galleryActivityLauncher.launch(openGalleryIntent);
    }

    ActivityResultLauncher<Intent> galleryActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            mPictureImageView.setImageURI(data.getData());
                            uploadImageToTheFireBase(data.getData());
                        } else {
                        }
                    }
                }
            });


    private void uploadImageToTheFireBase(Uri imageUri) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images");
        String imageName = FirebaseAuth.getInstance().getCurrentUser().getUid() + "." + MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(imageUri));
        final StorageReference imageRef = storageReference.child(imageName);
        UploadTask uploadTask = imageRef.putFile(imageUri);
        uploadTask.continueWithTask(task -> imageRef.getDownloadUrl()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                mDbUser.child(FirebaseAuth.getInstance().getUid()).child("profileUrl").setValue(task.getResult().toString());
                CacheUtilities.cacheImageProfile(CoachProfileActivity.this, task.getResult().toString());
            }
        });
    }


    private void redirectToLogout() {
        FirebaseAuth.getInstance().signOut();
        CacheUtilities.clearAll(this);
        Intent intent = new Intent(CoachProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private void redictToUpdateDetailsScreen() {
        Intent intent =new Intent(CoachProfileActivity.this,UpdateCoachDetailsActivity.class);
        startActivity(intent);
    }

}
