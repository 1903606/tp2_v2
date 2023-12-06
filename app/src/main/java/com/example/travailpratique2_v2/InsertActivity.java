package com.example.travailpratique2_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    public void insertUserToken(String userToken) {
        SharedPreferences pref = getSharedPreferences("UserToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("userToken", userToken);
        editor.apply();
    }

}