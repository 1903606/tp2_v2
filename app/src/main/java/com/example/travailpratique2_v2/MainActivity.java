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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

Button btn_register, btn_connexion;

TextInputEditText tiet_courriel, tiet_mdp;
FirebaseAuth bdAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_register = findViewById(R.id.btn_inscription);
        btn_connexion = findViewById(R.id.btn_connexion);
        bdAuth = FirebaseAuth.getInstance();
        tiet_courriel = findViewById(R.id.tiet_courriel);
        tiet_mdp = findViewById(R.id.tiet_mdp);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register_view = new Intent(MainActivity.this, Register.class );
                startActivity(intent_register_view);
                finish();

            }
        });

        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courriel = tiet_courriel.getText().toString();
                String mdp = tiet_mdp.getText().toString();

                if(Patterns.EMAIL_ADDRESS.matcher(courriel).matches() && mdp.length() >= 10){
                    bdAuth.signInWithEmailAndPassword(courriel, mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser usager = bdAuth.getCurrentUser();
                                Intent intent_gestionbd_view = new Intent(MainActivity.this, GestionBD.class);
                                startActivity(intent_gestionbd_view);
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Erreur lors de la connexion", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                } else if (!Patterns.EMAIL_ADDRESS.matcher(courriel).matches()) {
                    tiet_courriel.setError("Veuillez entrer un courriel valide");
                    tiet_courriel.requestFocus();

                } else {
                    tiet_mdp.setError("Veuillez entrez 10 caracteres et plus");
                    tiet_mdp.requestFocus();
                }

            }
        });

    }
}