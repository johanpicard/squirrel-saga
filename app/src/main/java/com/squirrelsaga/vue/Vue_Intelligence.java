package com.squirrelsaga.vue;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.squirrelsaga.controleur.Controleur;


public class Vue_Intelligence extends Fragment {

    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_vue_intelligence, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        progressBar.setProgress(Controleur.getEcureuil().getIntelligence());

        return rootView;
    }


}
