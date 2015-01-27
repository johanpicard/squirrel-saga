package com.squirrelsaga.vue;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import com.squirrelsaga.modele.QueteVitesse;

import java.util.concurrent.TimeUnit;


public class Vue_Quete_Vitesse extends AbstractQueteActivity {

    TextView textViewTime;

    @Override
    /**
     * Initialise la vue en affichant le timer
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quete_force);

        Log.i("SSAGA", getActiveQuest().toString());

        textViewTime = (TextView)findViewById(R.id.textViewTime);
        // TODO timer dynamique
        textViewTime.setText("00:03:00");
        final CounterClass timer = new CounterClass(180000,1000);


    }

    /**
     * Permet de récupérer la quête sélectionnée par l'utilisateur sur la carte
     * @return QueteVitesse
     */
    protected QueteVitesse getActiveQuest() {
        return QueteVitesse.findById(QueteVitesse.class, queteId);
    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            textViewTime.setText("Terminé.");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }
    }

}
