package com.squirrelsaga.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

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
        fragments.add(Fragment.instantiate(this,Vue_Vitesse.class.getName()));

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

    public void afficherChoix(View view) {
        Intent intent = new Intent(this, choix_ecureuil.class);
        startActivity(intent);
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
