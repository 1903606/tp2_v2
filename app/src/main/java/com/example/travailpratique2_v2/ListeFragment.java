package com.example.travailpratique2_v2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListeFragment extends Fragment {

    TextView tv_nom, tv_gender, tv_telephone;
    String nom, gender, telephone;

    public ListeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get info form bundle
        if(getArguments() != null){
            nom = getArguments().getString("nom");
            gender = getArguments().getString("gender");
            telephone = getArguments().getString("telephone");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vue = inflater.inflate(R.layout.fragment_liste, container, false);
        tv_nom =vue.findViewById(R.id.tv_nom);
        tv_gender = vue.findViewById(R.id.tv_gender);
        tv_telephone = vue.findViewById(R.id.tv_telephone);

        tv_nom.setText(nom);
        tv_gender.setText(gender);
        tv_telephone.setText(telephone);

        return vue;

    }
}