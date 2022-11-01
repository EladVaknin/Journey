package com.example.myjourney.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private static final String USERS_TABLE = "users";
    private Button mBmiButton ,mUpdateJourneyButton,mMyJourneyButton,mShoesStatusButton;
    private Button mCreateAJourney;
    private Button mLogoutButton,mHealthDetailsButton;
    private ImageView mPictureImageView;
    private Button mChatButton;
    private TextView mFullName ,mLastResult;
    private final DatabaseReference mDbUser = FirebaseDatabase.getInstance().getReference(USERS_TABLE);


    ///////// build the main algoritem ////
    //////// build the shoes status page/////


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
    }

    private void initViews() {
        mChatButton=findViewById(R.id.ChatButton);
        mChatButton.setOnClickListener(v -> redirectToSearchCoachScreen());

        mLogoutButton = findViewById(R.id.LogoutButton);
        mLogoutButton.setOnClickListener(v -> redirectToLogout());

        mBmiButton = findViewById(R.id.BmiButton);
        mBmiButton.setOnClickListener(v -> redirectToBmiCalScreen());
        
        mUpdateJourneyButton = findViewById(R.id.UpdateJourneyButton);
        mUpdateJourneyButton.setOnClickListener(v -> redirectToUpdateScreen());
        
        mMyJourneyButton =findViewById(R.id.UpdateJourneyButton);
        mMyJourneyButton.setOnClickListener(v -> redirectToMyAlbumScreen());
        
        mShoesStatusButton =findViewById(R.id.ShoesStatusButton);
        mShoesStatusButton.setOnClickListener(v -> redirectToShoesCalcuScreen());

        mPictureImageView = findViewById(R.id.profile_image_view);
        mPictureImageView.setOnClickListener(v -> choosePictureFromGalleryAndUploadToTheFireBase());
        if (!TextUtils.isEmpty(CacheUtilities.getImageProfile(this))) {
            Picasso.get().load(CacheUtilities.getImageProfile(this)).noPlaceholder().into(mPictureImageView);
        }

        mFullName =findViewById(R.id.userNameTextView);
        mFullName.setText(mFullName.getText() + CacheUtilities.getUserName(this));

        mLastResult = findViewById(R.id.LastResultTextView);
        // pull the last running from the album (my journey)

        mCreateAJourney = findViewById(R.id.CreatejourneyButton);
        mCreateAJourney.setOnClickListener(v -> redirectToCreateAJourney ());

        mHealthDetailsButton =findViewById(R.id.HealthDetailsButton);
        mHealthDetailsButton.setOnClickListener(v -> redirectToHealthDetailsScreen());
    }

    private void redirectToSearchCoachScreen() {
        Intent intent = new Intent(ProfileActivity.this,SearchCoachActivity.class);
        startActivity(intent);
    }

    private void redirectToHealthDetailsScreen() {
        Intent intent = new Intent(ProfileActivity.this,HealthDetailsActivity.class);
        startActivity(intent);
    }

    private void redirectToCreateAJourney() {
        Intent intent = new Intent(ProfileActivity.this,CreateJourneyActivity.class);
        startActivity(intent);
    }

    private void redirectToLogout() {
        FirebaseAuth.getInstance().signOut();
        CacheUtilities.clearAll(this);
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToShoesCalcuScreen() {
        Intent intent = new Intent(ProfileActivity.this, ShoesStatusActivity.class);
        startActivity(intent);
    }

    private void redirectToUpdateScreen() {
        Intent intent = new Intent(ProfileActivity.this, ShoesStatusActivity.class);
        startActivity(intent);
    }

    private void redirectToBmiCalScreen() {
        Intent intent = new Intent(ProfileActivity.this, BmiActivity.class);
        startActivity(intent);
    }

    private void redirectToMyAlbumScreen() {
        Intent intent = new Intent(ProfileActivity.this, MyJourneyActivity.class);
        startActivity(intent);
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
                CacheUtilities.cacheImageProfile(ProfileActivity.this, task.getResult().toString());
            }
        });
    }


}
