package com.aaamab.uiapp.ui.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aaamab.uiapp.R;
import com.aaamab.uiapp.databinding.ActivityStorageScreenBinding;
import com.aaamab.uiapp.utils.StaticsMethod;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static android.service.controls.ControlsProviderService.TAG;
import static com.aaamab.uiapp.utils.StaticsMethod.PICK_IMAGE_PERMISSION_REQUEST;
import static com.aaamab.uiapp.utils.StaticsMethod.PICK_IMAGE_REQUEST;

public class StorageScreen extends AppCompatActivity {


    ActivityStorageScreenBinding binding;
    MyHandler handler;


    private Uri filePath;


    //firebase var
    FirebaseStorage storage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_storage_screen);
        handler = new MyHandler(this);
        binding.setHandler(handler);
        binding.btnUpload.setEnabled(false);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        checkPermissions();
    }

    public class MyHandler {
        Context context;

        public MyHandler(Context context) {
            this.context = context;
        }

        public void choose(View v) {
            SelectImage();
        }

        public void upload(View v) {
            uploadImage();
        }

        public void download(View v) {
            StorageReference imgRef = storageReference.child("images/" + StaticsMethod.UID);

            imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(binding.imgView);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }

    }

    public void SelectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + StaticsMethod.UID);

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(StorageScreen.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(StorageScreen.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST) {

            if (resultCode == RESULT_OK) {

                if (data != null) {

                    if (data.getData() != null) {

                        filePath = data.getData();
                        try {

                            // Setting image on image view using Bitmap
                            Bitmap bitmap = MediaStore
                                    .Images
                                    .Media
                                    .getBitmap(
                                            getContentResolver(),
                                            filePath);
                            binding.imgView.setImageBitmap(bitmap);
                            binding.btnUpload.setEnabled(true);
                        } catch (IOException e) {
                            // Log the exception
                            e.printStackTrace();
                        }


                    }


                }


            } else if (resultCode == RESULT_CANCELED) {
                if (filePath != null) {
                    binding.btnUpload.setEnabled(true);
                } else {
                    Toast.makeText(this, "Please Select an Image ", Toast.LENGTH_SHORT).show();
                    binding.btnUpload.setEnabled(false);
                }

            }


        }

    }


    public void checkPermissions() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (ActivityCompat.checkSelfPermission(StorageScreen.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(StorageScreen.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_IMAGE_PERMISSION_REQUEST);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PICK_IMAGE_PERMISSION_REQUEST) {

            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Log.e(TAG, "onRequestPermissionsResult: Denied");
                Toast.makeText(this, "you must allow for the permissions to use our app", Toast.LENGTH_SHORT).show();
            }

        }
    }


}