package com.example.myjourney.useful;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class practical {
    public static boolean ValidChecks(Context context, String email, String password ,String gender ) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(context, "Empty password or Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 8) {
            Toast.makeText(context, "Password length must be bigger than 8", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (gender != "male" || gender != "Male" || gender != "female" || gender != "Female"){
            Toast.makeText(context, "please write male or female exactly", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    }
