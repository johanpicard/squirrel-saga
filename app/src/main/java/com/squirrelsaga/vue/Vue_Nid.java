package com.squirrelsaga.vue;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squirrelsaga.controleur.Controleur;

/**
 * Created by juliette on 14/01/2015.
 * Vue nid
 */
public class Vue_Nid extends Fragment {

    ImageView image_ecureuil;
    TextView Bienvenue_ecureuil;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_vue_nid, container, false);

        image_ecureuil = (ImageView) rootView.findViewById(R.id.Image_ecureuil);

        String imageName = Controleur.getEcureuil().getCheminImage();

        image_ecureuil.setImageResource(
                getResources().getIdentifier(
                        imageName, "drawable", getActivity().getPackageName()));
// TODO mettre des ecureuil.save partout

        Bienvenue_ecureuil = (TextView) rootView.findViewById(R.id.textBienvenue);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "GrandHotel-Regular.otf");
        Bienvenue_ecureuil.setTypeface(font);
        // TODO changer le nom de l'écureuil
         // Bienvenue_ecureuil.setText();

        return rootView;

    }
}