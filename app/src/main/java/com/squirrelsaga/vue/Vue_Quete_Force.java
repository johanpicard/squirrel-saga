package com.squirrelsaga.vue;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squirrelsaga.modele.QueteForce;

public class Vue_Quete_Force extends AbstractQueteActivity {

    @Override
    /**
     * Initialise la vue en affichant la question
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quete_force);

        Log.i("SSAGA", getActiveQuest().toString());

        QueteForce quete = getActiveQuest();
        String objectif1 = quete.getObjectif1();
        int valeur1 = quete.getValeur1();
        String objectif2 = quete.getObjectif2();
        int valeur2 = quete.getValeur2();

        Typeface font = Typeface.createFromAsset(getAssets(), "GrandHotel-Regular.otf");

        TextView text_info = (TextView)findViewById(R.id.text_info);
        text_info.setTypeface(font);
        TextView text_a_ramasser1 = (TextView)findViewById(R.id.text_a_ramasser1);
        text_a_ramasser1.setTypeface(font);
        TextView text_a_ramasser2 = (TextView)findViewById(R.id.text_a_ramasser2);
        text_a_ramasser2.setTypeface(font);
        // TODO affichage dynamique des texte à ramasser

        Button btn_ramasser1 = (Button)findViewById(R.id.BtnRamasser1);
        btn_ramasser1.setTypeface(font);
        Button btn_ramasser2 = (Button)findViewById(R.id.BtnRamasser2);
        btn_ramasser2.setTypeface(font);

    }

    /**
     * Permet de récupérer la quête sélectionnée par l'utilisateur sur la carte
     * @return QueteIntelligence
     */
    protected QueteForce getActiveQuest() {
        return QueteForce.findById(QueteForce.class,queteId);
    }

    /**
     * Permet de vérifier la réponse
     * @param v View
     * @return int 0 si erreur ou 1 si vrai
     */
    public void verifierReponse(View v){
        QueteForce quete = getActiveQuest();
        /*String reponse = quete.getReponse();
        EditText reponseText = (EditText)findViewById(R.id.editText);

        if (reponse.toUpperCase().equals(reponseText.getText().toString().toUpperCase()))
        {
            //On informe l'utilisateur de la réussite
            AlertDialog.Builder builder = new AlertDialog.Builder(VueQueteForce.this);
            builder.setMessage("Vous avez réussi cette quête")
                    .setTitle("Bravo")
                    .setPositiveButton("Retour à la carte", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // go to a new activity of the app
                            Intent retourCarte = new Intent(getApplicationContext(),
                                    Carte.class);
                            startActivity(retourCarte);
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();

            //On indique que la quête a été réussie
            quete.setReussie();
            Ecureuil ecureuil = Controleur.getEcureuil();
            ecureuil.mange(quete.getNoisette());
            ecureuil.intelligenceLevelUp(quete.getRecompense());

            //Ecureuil set Intelligence
        }else{
            //On informe l'utilisateur de l'échec, il peut réasser
            AlertDialog.Builder builder = new AlertDialog.Builder(VueQueteForce.this);
            builder.setMessage("Vous n'avez pas saisi la bonne réponse, vérifiez l'ortographe, ou proposez une autre réponse. ")
                    .setTitle("Perdu").
                    setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // cancel the alert box and put a Toast to the user
                            dialog.cancel();
                        }
                    });

                AlertDialog dialog = builder.create();
            dialog.show();
        }*/
    }
}
