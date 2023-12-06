package com.example.travailpratique2_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    public String getUserToken(){
        SharedPreferences pref = getSharedPreferences("UserToken", MODE_PRIVATE);

        return "SuperTokenIdentificationUser";
    }
}