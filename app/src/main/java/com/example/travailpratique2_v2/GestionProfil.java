package com.example.travailpratique2_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GestionProfil extends AppCompatActivity {

    TextView tv_bienvenue, tv_prenom,tv_nom;
    FirebaseAuth bdAuth;

    TextInputEditText tiet_courriel, tiet_mdp, tiet_mdp_confirmez, tiet_prenom, tiet_nom,tiet_gender,tiet_telephone;

    private String prenom, nom,gender,telephone;
    Button btn_update_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_profil);

        tv_bienvenue = findViewById(R.id.bienvenue);
        tv_prenom = findViewById(R.id.tv_prenom);
        tv_nom = findViewById(R.id.tv_nom);

        btn_update_info = findViewById(R.id.btn_update_info);
        tiet_courriel = findViewById(R.id.tiet_courriel_new);
        tiet_mdp = findViewById(R.id.tiet_mdp_new);
        tiet_mdp_confirmez = findViewById(R.id.tiet_mdp_confirmez_new);
        tiet_prenom = findViewById(R.id.tiet_prenom);
        tiet_nom = findViewById(R.id.tiet_nom);
        tiet_gender = findViewById(R.id.tiet_gender);
        tiet_telephone = findViewById(R.id.tiet_telephone);
        bdAuth = FirebaseAuth.getInstance();

        FirebaseUser usager = bdAuth.getCurrentUser();
        FirebaseAuth authProfile = FirebaseAuth.getInstance();
        showUserProfile(usager);



        btn_update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courriel = tiet_courriel.getText().toString();
                String mdp = tiet_mdp.getText().toString();
                String mdp_confirmez = tiet_mdp_confirmez.getText().toString();
                String prenom = tiet_prenom.getText().toString();
                String nom = tiet_nom.getText().toString();

                if (TextUtils.isEmpty(prenom)) {
                    Toast.makeText(GestionProfil.this, "Please enter your first name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nom)) {
                    Toast.makeText(GestionProfil.this, "Please enter last name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(courriel)) {
                    Toast.makeText(GestionProfil.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mdp)) {
                    Toast.makeText(GestionProfil.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mdp_confirmez)) {
                    Toast.makeText(GestionProfil.this, "Please validate your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!mdp.equals(mdp_confirmez)) {
                    Toast.makeText(GestionProfil.this, "Please enter same password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mdp.length() < 10) {
                    Toast.makeText(GestionProfil.this, "Please minimum 10 caracters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mdp_confirmez.length() < 10) {
                    Toast.makeText(GestionProfil.this, "Please minimum 10 caracters", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Patterns.EMAIL_ADDRESS.matcher(courriel).matches()) {
                    if (mdp.matches(mdp_confirmez) && mdp.length() >= 10) {
                        updateEmail();

                    }

                }

            }
        });

    }

    private void showUserProfile(FirebaseUser firebaseUser){
        String userId = firebaseUser.getUid();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered users");
        referenceProfile.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User readUserDetails = snapshot.getValue(User.class);
                if(readUserDetails != null){
                    prenom = readUserDetails.prenom;
                    nom = readUserDetails.nom;
                    gender = readUserDetails.gender;
                    telephone = readUserDetails.telephone;

                    tv_prenom.setText(prenom);
                    tv_nom.setText(nom);

                    tiet_prenom.setText(prenom);
                    tiet_nom.setText(nom);
                    tiet_gender.setText(gender);
                    tiet_telephone.setText(telephone);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GestionProfil.this, "Erreur de database", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(GestionProfil.this, "Utilisateur modifier avec succes", Toast.LENGTH_SHORT).show();
                    usager.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(GestionProfil.this, "Courriel modifier avec succes", Toast.LENGTH_SHORT).show();
                                FirebaseUser usager = bdAuth.getCurrentUser();
                                if (usager != null) {
                                    Intent intent_login_view = new Intent(GestionProfil.this, MainActivity.class);
                                    startActivity(intent_login_view);
                                    finish();
                                }
                            } else {
                                Toast.makeText(GestionProfil.this, "Modification failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(GestionProfil.this, "Modification failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });




    }


}