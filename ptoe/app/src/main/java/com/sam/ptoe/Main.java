package com.sam.ptoe;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class Main extends Activity implements View.OnClickListener {

    TextView tip, toe, uni, flash;

    Camera mcamera;
    boolean FlashOn = false;
    boolean Flash;
    Camera.Parameters fparams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tip = findViewById(R.id.Ttip);
        toe = findViewById(R.id.Ttoe);
        uni = findViewById(R.id.Tunitc);
        flash = findViewById(R.id.Tflash);

        tip.setOnClickListener(this);
        toe.setOnClickListener(this);
        uni.setOnClickListener(this);
        flash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Ttip:
                tipPage();
                break;
            case R.id.Ttoe:
                toePage();
                break;
            case R.id.Tunitc:
                uniPage();
                break;
            case R.id.Tflash:
                ActivityCompat.requestPermissions(Main.this, new String[] {Manifest.permission.CAMERA}, CAMERA );
                Flash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
                if (!Flash) {
                    // device doesn't support flash
                    Toast.makeText(Main.this, "No flash available on your device", Toast.LENGTH_SHORT).show();
                } else {
                    getCamera();
                    flashL();
                }
        }
    }

    public void tipPage(){
        startActivity(new Intent(this, TipCalc.class));
    }

    public void toePage(){
        startActivity(new Intent(this, ToE.class));
    }

    public void uniPage(){
        startActivity(new Intent(this, UnitConv.class));
    }

    public void flashL() {
        if (FlashOn) {
            // turn off flash
            turnOffFlash();
        } else {
            // turn on flash
            turnOnFlash();
        }
    }

    public void getCamera(){
        if (mcamera == null) {
            try {
                mcamera = Camera.open();
                fparams = mcamera.getParameters();
            } catch (RuntimeException e) {
                Log.e("Camera Error. Failed ", e.getMessage());
            }
        }
    }

    public void turnOnFlash() {
        if (mcamera == null || fparams == null) {
            return;
        }
        fparams = mcamera.getParameters();
        fparams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mcamera.setParameters(fparams);
        mcamera.startPreview();
        FlashOn = true;

        flash.setBackgroundResource(R.drawable.lanthanoids);

    }
    public void turnOffFlash() {
        if (mcamera == null || fparams == null) {
            return;
        }

        fparams = mcamera.getParameters();
        fparams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        mcamera.setParameters(fparams);
        mcamera.stopPreview();
        FlashOn = false;

        flash.setBackgroundResource(R.drawable.unknown);

    }

    @Override
    protected void onStop() {
        super.onStop();

        // on stop release the camera
        if (mcamera != null) {
            mcamera.release();
            mcamera = null;
        }
    }
}
