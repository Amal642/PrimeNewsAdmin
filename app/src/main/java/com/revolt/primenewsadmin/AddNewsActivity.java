package com.revolt.primenewsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddNewsActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button chooseImageBtn;
    private Button uploadBtn;
    private EditText nameEditText;
    private EditText descriptionEditText,date;
    private ImageView chosenImageView;
    //private ProgressBar uploadProgressBar;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        chooseImageBtn = findViewById(R.id.button_choose_image);
        uploadBtn = findViewById(R.id.uploadBtn);
        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById ( R.id.descriptionEditText);
        chosenImageView = findViewById(R.id.chosenImageView);
        date=findViewById(R.id.date);
        //uploadProgressBar = findViewById(R.id.progress_bar);
        mStorageRef = FirebaseStorage.getInstance().getReference("teachers_uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("teachers_uploads");
        chooseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddNewsActivity.this, "An Upload is Still in Progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
           Picasso
                    .get()
                    .load(mImageUri)
                    .into(chosenImageView);
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
            //uploadProgressBar.setVisibility(View.VISIBLE);
            //uploadProgressBar.setIndeterminate(true);
            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //uploadProgressBar.setVisibility(View.VISIBLE);
                                    //uploadProgressBar.setIndeterminate(false);
                                    // uploadProgressBar.setProgress(0);
                                }
                            }, 500);
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Toast.makeText(AddNewsActivity.this, "News Upload successful", Toast.LENGTH_LONG).show();
                                    News upload = new News(nameEditText.getText().toString().trim(),
                                            uri.toString(),
                                            descriptionEditText.getText().toString(),date.getText().toString().trim());
                                    String uploadId = mDatabaseRef.push().getKey();
                                    mDatabaseRef.child(uploadId).setValue(upload);
                                    //uploadProgressBar.setVisibility(View.INVISIBLE);
                                    openImagesActivity();
                                }
                            });
                        }
                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // uploadProgressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(AddNewsActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }else{
            Toast.makeText(this, "You haven't Selected Any file selected", Toast.LENGTH_SHORT).show();
        }
    }
    private void openImagesActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}