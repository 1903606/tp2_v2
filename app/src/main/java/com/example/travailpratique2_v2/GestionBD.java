package com.example.travailpratique2_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class GestionBD extends AppCompatActivity {

    Button btn_deconnexion;
    FirebaseAuth bdAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_bd);

        bdAuth = FirebaseAuth.getInstance();
        btn_deconnexion = findViewById(R.id.btn_deconnexion);
        btn_deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bdAuth.signOut();
                Intent intent_retour_main = new Intent(GestionBD.this, MainActivity.class);
                startActivity(intent_retour_main);
                finish();
            }
        });
    }
}