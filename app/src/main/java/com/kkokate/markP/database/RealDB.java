package com.kkokate.markP.database;

import com.google.firebase.database.FirebaseDatabase;

public class RealDB {
    private static FirebaseDatabase database;
    public static FirebaseDatabase getInstance(){
        if(database==null){
            database = FirebaseDatabase.getInstance("" +
                    "https://markp-13d38-default-rtdb.asia-southeast1.firebasedatabase.app/");
        }
        return database;
    }
}
