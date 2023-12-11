package com.example.travailpratique2_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateListeActivity extends AppCompatActivity {

    TextInputEditText tiet_courriel, tiet_mdp, tiet_mdp_confirmez, tiet_prenom, tiet_nom,tiet_gender,tiet_telephone;

    Button btn_update_info;
    private ArrayList<User> listeUser;


    private String prenom, nom,gender,telephone;
    private int index = -1;


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

        btn_update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courriel = tiet_courriel.getText().toString();
                String mdp = tiet_mdp.getText().toString();
                String mdp_confirmez = tiet_mdp_confirmez.getText().toString();

                if (Patterns.EMAIL_ADDRESS.matcher(courriel).matches()) {
                    if (mdp.matches(mdp_confirmez) && mdp.length() >= 10) {
                        updateEmail();

                    }
                }
            }

        });
    }

    public void updateEmail() {

        FirebaseUser usager = bdAuth.getCurrentUser();
        tiet_courriel = findViewById(R.id.tiet_courriel_new);
        tiet_mdp = findViewById(R.id.tiet_mdp_new);
        tiet_mdp_confirmez = findViewById(R.id.tiet_mdp_confirmez_new);
        tiet_prenom = findViewById(R.id.tiet_prenom);
        tiet_nom = findViewById(R.id.tiet_nom);
        tiet_gender = findViewById(R.id.tiet_gender);
        tiet_telephone = findViewById(R.id.tiet_telephone);

        String newEmail = tiet_courriel.getText().toString();
        String newMdp = tiet_mdp.getText().toString();
        String newMdp_confirm = tiet_mdp_confirmez.getText().toString();
        String newPrenom = tiet_prenom.getText().toString();
        String newNom = tiet_nom.getText().toString();
        String newGender = tiet_gender.getText().toString();
        String newTelephone = tiet_telephone.getText().toString();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().
                setDisplayName(newPrenom).setDisplayName(newNom).setDisplayName(newMdp).setDisplayName(newMdp_confirm).setDisplayName(newGender).setDisplayName(newTelephone).build();
        usager.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(UpdateListeActivity.this, "Utilisateur modifier avec succes", Toast.LENGTH_SHORT).show();
                    usager.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UpdateListeActivity.this, "Courriel modifier avec succes", Toast.LENGTH_SHORT).show();
                                FirebaseUser usager = bdAuth.getCurrentUser();
                                if (usager != null) {
                                    Intent intent_login_view = new Intent(UpdateListeActivity.this, MainActivity.class);
                                    startActivity(intent_login_view);
                                    finish();
                                }
                            } else {
                                Toast.makeText(UpdateListeActivity.this, "Modification failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(UpdateListeActivity.this, "Modification failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });




    }



    }