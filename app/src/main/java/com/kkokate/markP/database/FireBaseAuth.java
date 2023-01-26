package com.kkokate.markP.database;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;


public class FireBaseAuth {
    private static FirebaseAuth auth;
    public static FirebaseAuth getInstance(Context context){
        if(auth==null){
            FirebaseApp.initializeApp(context);
            auth =FirebaseAuth.getInstance();
        }
        return auth;
    }
}
