package com.squirrelsaga.vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.squirrelsaga.modele.AbstractQuete;
import com.squirrelsaga.modele.QueteIntelligence;

public class VueQueteIntelligence extends AbstractQueteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_quete_intelligence);

        Log.i("SSAGA", getActiveQuest().toString());
    }


    protected QueteIntelligence getActiveQuest() {
        return QueteIntelligence.findById(QueteIntelligence.class,queteId);
    }

}
