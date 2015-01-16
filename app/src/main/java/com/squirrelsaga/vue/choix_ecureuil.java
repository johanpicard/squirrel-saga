package com.squirrelsaga.vue;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class choix_ecureuil extends ActionBarActivity {

    TextView explication_choix;
    Button btnValider;
    RadioGroup radioGroup;
    EditText nomInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_ecureuil);

        Typeface font = Typeface.createFromAsset(getAssets(), "GrandHotel-Regular.otf");
        explication_choix = (TextView)findViewById(R.id.explication_choix);
        explication_choix.setTypeface(font);

        btnValider = (Button)findViewById(R.id.BtnValider);
        btnValider.setTypeface(font);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup = (RadioGroup) findViewById(R.id.radio_group);
                nomInput = (EditText) findViewById(R.id.choixNom);
                //radioGroup.
                // TODO Stocker l'image et le nom choisis

                Intent intent = new Intent(choix_ecureuil.this, Slide_ecureuil.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choix_ecureuil, menu);
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
}
