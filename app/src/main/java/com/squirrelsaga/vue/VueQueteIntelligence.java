package com.squirrelsaga.vue;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.squirrelsaga.controleur.Controleur;
import com.squirrelsaga.modele.Ecureuil;
import com.squirrelsaga.modele.QueteIntelligence;

public class VueQueteIntelligence extends AbstractQueteActivity {

    @Override
    /**
     * Initialise la vue en affichant la question
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_quete_intelligence);

        Log.i("SSAGA", getActiveQuest().toString());

        QueteIntelligence quete = getActiveQuest();
        String question = quete.getQuestion();

        TextView questionText = (TextView)findViewById(R.id.question);
        questionText.setText(question);
    }

    /**
     * Permet de récupérer la quête sélectionnée par l'utilisateur sur la carte
     * @return QueteIntelligence
     */
    protected QueteIntelligence getActiveQuest() {
        return QueteIntelligence.findById(QueteIntelligence.class,queteId);
    }

    /**
     * Permet de vérifier la réponse
     * @param v View
     * @return int 0 si erreur ou 1 si vrai
     */
    public void verifierReponse(View v){
        QueteIntelligence quete = getActiveQuest();
        String reponse = quete.getReponse();

        EditText reponseText = (EditText)findViewById(R.id.editText);
        if (reponse.equals(reponseText.getText().toString()))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getApplicationContext());
            builder.setMessage("Vous avez réussi cette quête")
                    .setTitle("Bravo");
            AlertDialog dialog = builder.create();
            dialog.show();

            //On indique que la quête a été réussie
            quete.setReussie();
            Ecureuil ecureuil = Controleur.getEcureuil();
            ecureuil.mange(quete.getNoisette());
            ecureuil.intelligenceLevelUp(quete.getRecompense());

            //Ecureuil set Intelligence
        }
        //TODO on balance un intent carte ou nid en fonction d'où on veut retourner
    }
}
