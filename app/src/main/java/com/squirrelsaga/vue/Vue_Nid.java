package com.squirrelsaga.vue;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import junit.framework.Assert;

/**
 * Created by juliette on 14/01/2015.
 */
public class Vue_Nid extends Fragment {

    ImageView image_ecureuil;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_vue_nid, container, false);

        image_ecureuil = (ImageView) rootView.findViewById(R.id.Image_ecureuil);

        //TODO Changer le nom en fonction de l'écrueil choisi
        String imageName = "choix2";

        image_ecureuil.setImageResource(
                getResources().getIdentifier(
                        imageName, "drawable", getActivity().getPackageName()));

        return rootView;
    }

    public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
    }

}