package com.example.travailpratique2_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    TextInputEditText tiet_courriel, tiet_mdp, tiet_mdp_confirmez, tiet_prenom, tiet_nom, tiet_gender, tiet_telephone;
    Button btn_register;
    FirebaseAuth bdAuth;
    Dialog bteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tiet_courriel = findViewById(R.id.tiet_courriel);
        tiet_mdp = findViewById(R.id.tiet_mdp);
        tiet_mdp_confirmez = findViewById(R.id.tiet_mdp_confirmez);
        btn_register = findViewById(R.id.btn_register);
        tiet_prenom = findViewById(R.id.tiet_prenom);
        tiet_nom = findViewById(R.id.tiet_nom);
        tiet_gender = findViewById(R.id.tiet_gender);
        tiet_telephone = findViewById(R.id.tiet_telephone);



        bdAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courriel = tiet_courriel.getText().toString();
                String mdp = tiet_mdp.getText().toString();
                String mdp_confirmez = tiet_mdp_confirmez.getText().toString();
                String prenom = tiet_prenom.getText().toString();
                String nom = tiet_nom.getText().toString();
                String gender = tiet_gender.getText().toString();
                String telephone = tiet_telephone.getText().toString();


                if (TextUtils.isEmpty(prenom)){
                    Toast.makeText(Register.this, "Please enter your first name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nom)){
                    Toast.makeText(Register.this, "Please enter last name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(gender)){
                    Toast.makeText(Register.this, "Please enter your gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(telephone)){
                    Toast.makeText(Register.this, "Please enter your phone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(courriel)){
                    Toast.makeText(Register.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mdp)){
                    Toast.makeText(Register.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(mdp_confirmez)){
                    Toast.makeText(Register.this, "Please validate your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!mdp.equals(mdp_confirmez)){
                    Toast.makeText(Register.this, "Please enter same password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(mdp.length() < 10){
                    Toast.makeText(Register.this, "Please minimum 10 caracters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mdp_confirmez.length() < 10){
                    Toast.makeText(Register.this, "Please minimum 10 caracters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(telephone.length() < 10 && telephone.length() > 10){
                    Toast.makeText(Register.this, "Please enter 10 caracters", Toast.LENGTH_SHORT).show();
                    return;
                }



                if(Patterns.EMAIL_ADDRESS.matcher(courriel).matches()){
                    if (mdp.matches(mdp_confirmez)&& mdp.length()>= 10){
                        registerUser();
                        }

                }

            }
        });


    }
    private void registerUser(){

        tiet_courriel = findViewById(R.id.tiet_courriel);
        tiet_mdp = findViewById(R.id.tiet_mdp);
        tiet_mdp_confirmez = findViewById(R.id.tiet_mdp_confirmez);
        tiet_prenom = findViewById(R.id.tiet_prenom);
        tiet_nom = findViewById(R.id.tiet_nom);
        tiet_gender = findViewById(R.id.tiet_gender);
        tiet_telephone = findViewById(R.id.tiet_telephone);

        String courriel = tiet_courriel.getText().toString();
        String mdp = tiet_mdp.getText().toString();
        String mdp_confirmez = tiet_mdp_confirmez.getText().toString();
        String prenom = tiet_prenom.getText().toString();
        String nom = tiet_nom.getText().toString();
        String gender = tiet_gender.getText().toString();
        String telephone = tiet_telephone.getText().toString();


        bdAuth.createUserWithEmailAndPassword(courriel, mdp)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Utilisateur success", Toast.LENGTH_SHORT).show();
                            FirebaseUser usager = bdAuth.getCurrentUser();
                            User writeUserDetails = new User(prenom, nom, gender, telephone);
                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered users");

                            referenceProfile.child(usager.getUid()).setValue(writeUserDetails);
                            if(usager!=null){
                                Intent intent_login_view = new Intent(Register.this, MainActivity.class );
                                startActivity(intent_login_view);
                                finish();
                            }
                        } else {
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
