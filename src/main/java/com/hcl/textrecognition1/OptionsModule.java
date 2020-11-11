package com.hcl.demoapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public abstract class OptionsModule extends AppCompatActivity {

    private static final String TAG = "MyApp";

    PermissionsModule permissionsModule = new PermissionsModule();


        //actionbar menu
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            //inflate menu
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        // handle actionbar clicks
        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            int id = item.getItemId();
            if (id == R.id.addimage) {
                Log.i(TAG, "Option Clicked!");
                ShowImageImportDialog();
            }
            if (id == R.id.settings) {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            }
            return super.onOptionsItemSelected(item);
        }


    public void ShowImageImportDialog() {
        // items in dialog
        String[] items = {" Camera"," Gallery"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Select Image");
        Log.i(TAG, "dialog");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    if(!permissionsModule.checkCameraPermission()){
                        permissionsModule.requestCameraPermission();
                    }
                    else{
                        permissionsModule.selectImage.pickCamera();
                    }

                }
                if (i == 1) {
                    if(!permissionsModule.checkStoragePermission()){
                        permissionsModule.requestStoragePermission();
                    }
                    else{
                        permissionsModule.selectImage.pickGallery();
                    }
                }
            }

        });
        dialog.create().show();
    }
}
