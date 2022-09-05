package com.kasimkartal866.bookmedia.list;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.kasimkartal866.bookmedia.R;

public class AddBookFragment extends Fragment {

    private ImageView imageView;
    private TextView tvBookName,tvAuthorName,tvExplanation;
    private Button buttonSave;
    View mainView;
    private Context mContext;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;
    Bitmap selectedImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView= inflater.inflate(R.layout.fragment_add_book, container, false);

        imageView = mainView.findViewById(R.id.imageView);
        tvBookName = mainView.findViewById(R.id.tvBookName);
        tvAuthorName = mainView.findViewById(R.id.tvAuthorName);
        tvExplanation = mainView.findViewById(R.id.tvExplanation);
        buttonSave = mainView.findViewById(R.id.buttonSave);

        registerLauncher();

        return mainView;
    }

    public void Save (View view ) {

    }

    public void selectImage (View view ) {
        if(ContextCompat.checkSelfPermission(mContext
                ,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity)mContext
                    ,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view ,"Permission needed for galery",Snackbar
                        .LENGTH_INDEFINITE).setAction("Give permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                }).show();

            }else {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

            }
        }else {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK
                    ,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intentToGallery);

        }
    }

    private void registerLauncher () {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts
                .StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK) {
                    Intent intentFromResult = result.getData();
                    if(intentFromResult != null) {
                      Uri imageData = intentFromResult.getData();

                      try {
                          if(Build.VERSION.SDK_INT >= 28 ) {
                              ImageDecoder.Source source = ImageDecoder.createSource
                                      (AddBookFragment.this.mContext.getContentResolver(), imageData);
                              selectedImage = ImageDecoder.decodeBitmap(source);
                              imageView.setImageBitmap(selectedImage);
                          }else {
                              selectedImage = MediaStore .Images.Media.getBitmap
                                      (AddBookFragment.this.getActivity().getContentResolver(),imageData);
                              imageView.setImageBitmap(selectedImage);

                          }

                      }catch (Exception e) {
                          e.printStackTrace();

                      }
                    }
                }
            }
        });

        permissionLauncher = registerForActivityResult(new ActivityResultContracts
                .RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result) {
                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGallery);
                }else {
                    Toast.makeText(mContext, "Permission needed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}