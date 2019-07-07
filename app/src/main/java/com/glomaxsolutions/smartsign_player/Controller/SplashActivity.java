package com.glomaxsolutions.smartsign_player.Controller;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.glomaxsolutions.smartsign_player.R;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private final static int PERMISSION_REQUEST_CODE = 102;
    private static final long SPLASH_TIME_OUT = 5000;
    ProgressBar progressBar;
    String folderName = "/Smart Sign Video List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar_circuler);
        CheckPermission();
      //  SplashStartUp();
    }
    //TODO ---Check Permission Method ---- Code
    private void CheckPermission() {
        // Set Permissions
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE};
        // Check permission has Granted
        if (EasyPermissions.hasPermissions(this, permissions)) {
            // After runtime permission,
            //TODO --- set CreateFolder & SplashStartUp Method
            CreateFolder();
            SplashStartUp();
        } else {
            // does not get Permission then, get permission first time
            EasyPermissions.requestPermissions(this,
                    "We need permissions because app does not run without permission.",
                    PERMISSION_REQUEST_CODE,
                    permissions);
        }
    }
    //TODO ---Create Folder Method ---- Code
    private void CreateFolder() {
        //TODO Create Folder code
        String myFolder = Environment.getExternalStorageDirectory() + folderName;

        File file = new File(myFolder);
        if (!file.exists())
            if (!file.mkdir()) {
                Toast.makeText(this, "Allow the permission otherwise app does not run.", Toast.LENGTH_SHORT).show();

            } else {

                file.mkdir();
                Toast.makeText(this, "Starting....", Toast.LENGTH_LONG).show();
            }
    }

    //TODO Splash Start up Method
    private void SplashStartUp() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(new Intent(SplashActivity.this, CampaignActivity.class));
                progressBar.setVisibility(View.GONE);
                // close this activity
                finish();

            }

        }, SPLASH_TIME_OUT);

    }
    //TODO on Permissions Granted Method
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }
    //TODO on Permissions Denied Method
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // If Denied permission then call to Dialog again
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    //TODO on Request Permissions Result Method
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        //TODO After first time granted permission start up app
        CreateFolder();
        SplashStartUp();
    }
    //TODO on Activity Result Method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen,

            //TODO Run main code here
            CreateFolder();
            SplashStartUp();
            //Toast.makeText(this, "Wait Opening Camera", Toast.LENGTH_LONG).show();
        }
    }

}