package com.kkokate.markP.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import com.kkokate.markP.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerFrag extends Fragment implements ZXingScannerView.ResultHandler{

    private View view;
    private ZXingScannerView mScannerView;
    protected Context appContext;
    protected Activity activity;

    public ScannerFrag(Context appContext)
    {
        this.appContext=appContext;
        this.activity= (Activity) appContext;
        this.requestCameraPermissions();
    }

    private void requestCameraPermissions()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(appContext, Manifest.permission.CAMERA);
        if (permissionCheck < 0)
        {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 1);

        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        view= inflater.inflate(R.layout.fragment_scanner, container, false);
        mScannerView = view.findViewById(R.id.mScanner);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        try {
            String qrCodeValue = result.getText();
            if(qrCodeValue!=null){
                Toast.makeText(appContext, qrCodeValue, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(appContext, "Scan QR code only", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}