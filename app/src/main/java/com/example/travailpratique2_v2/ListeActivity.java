package com.example.travailpratique2_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity {

    private ListView lv_usager;
    private ArrayList<User> listeUser;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

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


    }

}