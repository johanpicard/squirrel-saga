package com.squirrelsaga.vue;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squirrelsaga.controleur.Controleur;
import com.squirrelsaga.service.LocationService;


public class Menu_Principal extends ActionBarActivity {

    Button BtnCommencer;
    TextView Logo1;
    TextView Logo2;
    Class<?> nextIntentClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("SSAGA", "Starting...");

        Intent intent = new Intent(this, LocationService.class);
        intent.setAction("startListening");
        startService(intent);


        nextIntentClass = Slide_ecureuil.class;
        checkFirstLaunchAndSetupApplication();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__principal);

        Typeface font = Typeface.createFromAsset(getAssets(), "GrandHotel-Regular.otf");

        BtnCommencer = (Button)findViewById(R.id.BtnCommencer);
        BtnCommencer.setTypeface(font);
        BtnCommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Principal.this, nextIntentClass);
                startActivity(intent);
            }
        });

        Logo1 = (TextView)findViewById(R.id.Logo1);
        Logo1.setTypeface(font);
        Logo2 = (TextView)findViewById(R.id.Logo2);
        Logo2.setTypeface(font);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu__principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkFirstLaunchAndSetupApplication()
    {
        Log.i("SSAGA", "Checking for first launch...");
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        boolean firstLaunch = settings.getBoolean("firstLaunch", true);
        if (firstLaunch) {
            Log.i("SSAGA", "This is first launch");
            setupApplication();
        }
    }

    private  void setupApplication() {

        Controleur.setupDatabase();

        nextIntentClass = choix_ecureuil.class;

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstLaunch", false);
        editor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
