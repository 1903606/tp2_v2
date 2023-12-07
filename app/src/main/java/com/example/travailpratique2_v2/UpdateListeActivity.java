package com.example.travailpratique2_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateListeActivity extends AppCompatActivity {

    TextInputEditText tiet_courriel, tiet_mdp, tiet_mdp_confirmez, tiet_prenom, tiet_nom,tiet_gender,tiet_telephone;

    Button btn_update_info;

    private String prenom, nom,gender,telephone;

    FirebaseAuth bdAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_liste);
        tiet_prenom = findViewById(R.id.tiet_prenom);
        tiet_nom = findViewById(R.id.tiet_nom);
        tiet_gender = findViewById(R.id.tiet_gender);
        tiet_telephone = findViewById(R.id.tiet_telephone);
        tiet_courriel = findViewById(R.id.tiet_courriel_new);
        tiet_mdp = findViewById(R.id.tiet_mdp_new);
        tiet_mdp_confirmez = findViewById(R.id.tiet_mdp_confirmez_new);
        btn_update_info = findViewById(R.id.btn_update_info);
        Intent intent = getIntent();

        tiet_prenom.setText(intent.getExtras().getString("prenom"));
        tiet_nom.setText(intent.getExtras().getString("nom"));
        tiet_gender.setText(intent.getExtras().getString("gender"));
        tiet_telephone.setText(intent.getExtras().getString("telephone"));

        bdAuth = FirebaseAuth.getInstance();

        FirebaseUser usager = bdAuth.getCurrentUser();
        FirebaseAuth authProfile = FirebaseAuth.getInstance();
    }



    }