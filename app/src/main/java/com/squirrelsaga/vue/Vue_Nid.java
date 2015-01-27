package com.squirrelsaga.vue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squirrelsaga.controleur.Controleur;

/**
 * Created by juliette on 14/01/2015.
 */
public class Vue_Nid extends Fragment {

    ImageView image_ecureuil;


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
        return rootView;
    }

/*
    public void showPopup(View anchorView) {
        int popupWidth = 200;
        int popupHeight = 150;

        LayoutInflater layoutInflater = (LayoutInflater)getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_interrogation, null);

        PopupWindow popupWindow = new PopupWindow(layout,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // Initialize more widgets from `popup_layout.xml`
        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow();
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);
        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;
        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());
        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);
        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                location[0], location[1] + anchorView.getHeight());

    }
*/
}