package com.example.E_VIT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Upload_DownloadActivity extends AppCompatActivity {


    Button uploadCat1,uploadCat2,uploadFat, downloadCat1,downloadCat2,downloadFat;
    Uri imageUri;
    FirebaseStorage storage;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    EditText qname;
    EditText url_Link;


    int SELECT_PICTURE = 200;
    private int storagePermissionCode = 1;
    private static int REQUEST_CODE = 2;

    int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_download);


        uploadCat1 = findViewById(R.id.uploadbtn);
        uploadCat2 = findViewById(R.id.uploadbtn2);
        uploadFat = findViewById(R.id.uploadbtn3);

        downloadCat1 = findViewById(R.id.downloadbtn);
        downloadCat2 = findViewById(R.id.downloadbtn2);
        downloadFat = findViewById(R.id.downloadbtn3);
        qname = findViewById(R.id.qnameEditText);
        url_Link = findViewById(R.id.urlEditText);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        uploadCat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Upload_DownloadActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    count=1;
                    selectCat1();

                } else {
                    requestStoragePermission();
                }


            }
        });
        uploadCat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Upload_DownloadActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    count=2;
                    selectCat1();

                } else {
                    requestStoragePermission();
                }



            }
        });
        uploadFat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Upload_DownloadActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    count=3;
                    selectCat1();

                } else {
                    requestStoragePermission();
                }



            }
        });

        downloadCat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Upload_DownloadActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    downloadCat1();

                } else {
                    askPermission();
                }
            }
        });

        downloadCat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Upload_DownloadActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    downloadCat2();

                } else {
                    askPermission();
                }
            }
        });

        downloadFat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Upload_DownloadActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    downloadFat();

                } else {
                    askPermission();
                }
            }
        });


    }

     //////Feature Methods

    public void selectCat1() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(i,100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null) {
            imageUri = data.getData();
            if(count==1)
            {
                uploadImageCat1();
            }
            else if(count == 2)
            {
                uploadImageCat2();
            }
            else if(count == 3 )
            {
                uploadImageFat();
            }
        }
    }

    private void uploadImageCat1() {

        String key = qname.getText().toString();
        StorageReference imageref = storageReference.child("Cat1/" + key);
        imageref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Upload_DownloadActivity.this, "QPaper Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_DownloadActivity.this, "QPaper Uploading Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void uploadImageCat2() {

        String key = qname.getText().toString();
        StorageReference imageref = storageReference.child("Cat2/" + key);
        imageref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Upload_DownloadActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_DownloadActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void uploadImageFat() {

        String key = qname.getText().toString();
        StorageReference imageref = storageReference.child("Fat/" + key);
        imageref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Upload_DownloadActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_DownloadActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

    }


       ///download cat1 papers
    public void downloadCat1() {

        String key = qname.getText().toString();

        StorageReference imageref = storageReference.child("Cat1/" + key);
        imageref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Log.d(TAG,"success" +uri.toString());
                Toast.makeText(Upload_DownloadActivity.this, "Succesfully Downloaded URL", Toast.LENGTH_LONG).show();
                url_Link.setText(uri.toString());
                //Toast.makeText(Upload_DownloadActivity.this, "" + uri.toString(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_DownloadActivity.this, "Please Enter correct course name/code or Qpaper Not Available", Toast.LENGTH_LONG).show();
            }
        });
    }


    ///download cat2 papers
    public void downloadCat2() {

        String key = qname.getText().toString();

        StorageReference imageref = storageReference.child("Cat2/" + key);
        imageref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Log.d(TAG,"success" +uri.toString());
                Toast.makeText(Upload_DownloadActivity.this, "Succesfully Downloaded URL", Toast.LENGTH_SHORT).show();
                url_Link.setText(uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_DownloadActivity.this, "Please Enter correct course name/code or Qpaper Not Available", Toast.LENGTH_SHORT).show();
            }
        });
    }


    ///download Fat papers
    public void downloadFat() {

        String key = qname.getText().toString();

        StorageReference imageref = storageReference.child("Fat/" + key);
        imageref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Log.d(TAG,"success" +uri.toString());
                Toast.makeText(Upload_DownloadActivity.this, "Successfuly Downloaded URL ", Toast.LENGTH_SHORT).show();
                url_Link.setText(uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_DownloadActivity.this, "Please Enter correct course name/code or Qpaper Not Available", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, storagePermissionCode);
        }
    }

    private void askPermission() {

        ActivityCompat.requestPermissions(Upload_DownloadActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadCat1();
            } else {
                Toast.makeText(Upload_DownloadActivity.this, "Please provide the required permissions", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

