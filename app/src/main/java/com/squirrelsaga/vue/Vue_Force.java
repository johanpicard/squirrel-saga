package com.squirrelsaga.vue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squirrelsaga.controleur.Controleur;


public class Vue_Force extends Fragment {

    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_vue_force, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        if (Controleur.getEcureuil().getForce() < 100)
            progressBar.setProgress(Controleur.getEcureuil().getForce());
        else
            progressBar.setProgress(100);

        return rootView;
    }

}
