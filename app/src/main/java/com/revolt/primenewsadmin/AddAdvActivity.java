package com.revolt.primenewsadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.BitSet;
import java.util.UUID;

public class AddAdvActivity extends AppCompatActivity {
    ImageView imageView;
    Button uploadadv, choosepic;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adv);

        imageView = findViewById(R.id.advimage);
        uploadadv = findViewById(R.id.uploadadv);
        choosepic = findViewById(R.id.chooseadv);

        choosepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });

        uploadadv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null) {
                    uploadToFirebase(imageUri);
                } else {
                    Toast.makeText(AddAdvActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

                imageUri = data.getData();
                imageView.setImageURI(imageUri);

            }
        }

        private void uploadToFirebase (Uri uri){

            final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
            fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            Model model = new Model(uri.toString());
                            String modelId = root.push().getKey();
                            root.child(modelId).setValue(model);
                            Toast.makeText(AddAdvActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddAdvActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddAdvActivity.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private String getFileExtension (Uri mUri){

            ContentResolver cr = getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(cr.getType(mUri));

        }


    }



