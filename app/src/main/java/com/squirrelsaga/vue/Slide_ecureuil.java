package com.squirrelsaga.vue;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;


public class Slide_ecureuil extends FragmentActivity {

    private PagerAdapter mPagerAdapter;

    String[] title = {
            "Nid",
            "Force",
            "Intelligence",
            "Vitesse"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_slide_ecureuil);

        // Création de la liste de Fragments que fera défiler le PagerAdapter
        List fragments = new Vector();

        // Ajout des Fragments dans la liste
        fragments.add(Fragment.instantiate(this, Vue_Nid.class.getName()));
        fragments.add(Fragment.instantiate(this,Vue_Force.class.getName()));
        fragments.add(Fragment.instantiate(this,Vue_Intelligence.class.getName()));
        fragments.add(Fragment.instantiate(this,Vue_Quete_Vitesse.class.getName()));

        // Création de l'adapter qui s'occupera de l'affichage de la liste de
        // Fragments
        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);
        // Affectation de l'adapter au ViewPager
        pager.setAdapter(this.mPagerAdapter);

    }

    public void afficherCarte(View view) {
        Intent intent = new Intent(this, Carte.class);
        startActivity(intent);
    }

    public void showPopup(View anchorView) {
        int popupWidth = 200;
        int popupHeight = 150;

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popup_layout = layoutInflater.inflate(R.layout.popup_interrogation, null);

        final PopupWindow popupWindow = new PopupWindow(popup_layout,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // Clear the default translucent background
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        // Getting a reference to Close button, and close the popup when clicked.
        Button BtnClose = (Button) popup_layout.findViewById(R.id.BtnClose);
        Typeface font = Typeface.createFromAsset(getAssets(), "GrandHotel-Regular.otf");
        BtnClose.setTypeface(font);
        BtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        TextView textInfo = (TextView) popup_layout.findViewById(R.id.textInfo);
        textInfo.setTypeface(font);

        int location[] = new int[2];
        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);
        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                location[0], location[1] + anchorView.getHeight());

    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final List fragments;

        //On fournit à l'adapter la liste des fragments à afficher
        public MyPagerAdapter(FragmentManager fm, List fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }



}
