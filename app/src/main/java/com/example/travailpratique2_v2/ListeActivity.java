package com.example.travailpratique2_v2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity {

    private ListView lv_usager;
    private ArrayList<User> listeUser;

    private Button btn_mise_a_jour,btn_supprimer;

    DatabaseReference reference;
    FirebaseAuth bdAuth;


    private int index = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        btn_mise_a_jour = findViewById(R.id.btn_mise_a_jour);
        btn_supprimer = findViewById(R.id.btn_supprimer);
        bdAuth = FirebaseAuth.getInstance();
        FirebaseUser usager = bdAuth.getCurrentUser();



        lv_usager = findViewById(R.id.lv_usager);
        listeUser = new ArrayList<User>();
        initiliasationListe();
    }

    private void initiliasationListe(){
        final ArrayAdapter<User> adapter = new ArrayAdapter<User>(ListeActivity.this, android.R.layout.simple_dropdown_item_1line, listeUser);
        reference = FirebaseDatabase.getInstance().getReference("Registered users");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listeUser.add(snapshot.getValue(User.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                listeUser.remove(snapshot.getValue(User.class));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        lv_usager.setAdapter(adapter);

        lv_usager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                User user = listeUser.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("nom", user.nom);
                bundle.putString("gender", user.gender + "");
                bundle.putString("telephone", user.telephone+ "");

                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragmentListe, ListeFragment.class, bundle)
                        .commit();
            }
        });

        btn_mise_a_jour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_update_view = new Intent(ListeActivity.this, UpdateListeActivity.class);
                FirebaseUser usager = bdAuth.getCurrentUser();
                User user = (User) listeUser.get(index);
                Bundle bundle = new Bundle();
                intent_update_view.putExtra("prenom",user.getPrenom());
                intent_update_view.putExtra("nom",user.getNom());
                intent_update_view.putExtra("gender",user.getGender());
                intent_update_view.putExtra("telephone",user.getTelephone());
                intent_update_view.putExtra("email", usager.getEmail());
                startActivity(intent_update_view);
                finish();
            }
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(

                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK)
                        {
                            Bundle bundle = result.getData().getExtras();
                            String rep;
                            //rep = bundle.get("REP1");
                        }
                    }
                }
        );




    }

}