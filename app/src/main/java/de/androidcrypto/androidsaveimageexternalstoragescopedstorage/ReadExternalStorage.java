package de.androidcrypto.androidsaveimageexternalstoragescopedstorage;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class ReadExternalStorage extends AppCompatActivity {

    Button selectImage;
    ImageView imageView;
    private ActivityResultLauncher<String> mTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_external_storage);

        selectImage = findViewById(R.id.selectImagebtn);
        imageView = findViewById(R.id.firebaseimage);

        init();
    }

    private void init(){

        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        imageView.setImageURI(result);
                    }
                }
        );

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(
                        ReadExternalStorage.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                ){
                    mTakePhoto.launch("image/*");
                } else {
                    Toast.makeText(ReadExternalStorage.this,"Permission not Granted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}