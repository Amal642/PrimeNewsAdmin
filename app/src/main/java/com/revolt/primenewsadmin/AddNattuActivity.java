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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddNattuActivity extends AppCompatActivity {

    EditText name,desc,phone;
    Button upload,image;
    ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 1;
    DatabaseReference DataRef;
    StorageReference storageRef;
    private Uri mImageUri;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nattu);

        name=findViewById(R.id.itemheading);
        desc=findViewById(R.id.desc);
        phone=findViewById(R.id.itemphone);
        upload=findViewById(R.id.uploaditm);
        image=findViewById(R.id.button_choose_item);
        imageView=findViewById(R.id.chosenImageViewnaatu);

        DataRef= FirebaseDatabase.getInstance().getReference().child("Nattu");
        storageRef= FirebaseStorage.getInstance().getReference().child("Naatuimage");


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddNattuActivity.this, "An Upload is Still in Progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });




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
                    .into(imageView);
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = storageRef.child(System.currentTimeMillis()
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
                                    Toast.makeText(AddNattuActivity.this, "News Upload successful", Toast.LENGTH_LONG).show();
                                    Nattu upload = new Nattu(name.getText().toString().trim(),
                                            desc.getText().toString(),uri.toString(),phone.getText().toString());
                                    String uploadId = DataRef.push().getKey();
                                    DataRef.child(uploadId).setValue(upload);
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
                            Toast.makeText(AddNattuActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        }else{
            Toast.makeText(this, "You haven't Selected Any file selected", Toast.LENGTH_SHORT).show();
        }
    }
    private void openImagesActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}