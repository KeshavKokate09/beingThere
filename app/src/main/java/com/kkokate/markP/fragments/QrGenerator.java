package com.kkokate.markP.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kkokate.markP.R;

import net.glxn.qrgen.android.QRCode;

import java.util.Calendar;

public class QrGenerator extends Fragment {
    private View view;
    private Button generateQrBtn;
    private ImageView qrImage;
    private TextView datePickerText;
    private Context appContext;
    final Calendar calender = Calendar.getInstance();


    public QrGenerator(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qr_generator, container, false);
        generateQrBtn = view.findViewById(R.id.generateBtn);
        generateQrBtn.setOnClickListener(view -> {
            if (view.equals(generateQrBtn)) {
                String date=datePickerText.getText().toString();
                Bitmap imgBitmap=generateQRCodeBitmap(date);
                qrImage.setImageBitmap(imgBitmap);
            }
        });

        qrImage = view.findViewById(R.id.qrImage);
        datePickerText = view.findViewById(R.id.datePicker);
        datePickerText.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    appContext,
                    (v, year, monthOfYear, dayOfMonth) -> {
                        datePickerText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    },calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
        return view;
    }

    public static Bitmap generateQRCodeBitmap(String qrCodeValue)
    {
        Bitmap bitmap = null;
        QRCode qrCode = QRCode.from(qrCodeValue);
        if(qrCode != null)
        {
            bitmap = qrCode.bitmap();
        }
        return bitmap;
    }
}

