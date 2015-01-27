package com.squirrelsaga.vue;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squirrelsaga.controleur.Controleur;

public class choix_ecureuil extends ActionBarActivity {

    TextView explication_choix;
    Button btnValider;
    RadioGroup radioGroup;
    EditText nomInput;
    RadioButton selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_ecureuil);

        Typeface font = Typeface.createFromAsset(getAssets(), "GrandHotel-Regular.otf");
        explication_choix = (TextView)findViewById(R.id.explication_choix);
        explication_choix.setTypeface(font);

        nomInput = (EditText) findViewById(R.id.choixNom);

        btnValider = (Button)findViewById(R.id.BtnValider);
        btnValider.setTypeface(font);

        watcher(nomInput,btnValider);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup = (RadioGroup) findViewById(R.id.radio_group);
                selectedButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                int idx = radioGroup.indexOfChild(selectedButton) + 1;
                Controleur.getEcureuil().setCheminImage("choix" + idx);
                Controleur.getEcureuil().setNom(nomInput.getText().toString());
                Controleur.getEcureuil().save();
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

    void watcher(final EditText message_body,final Button Send)
    {
        message_body.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(message_body.length() == 0)
                    Send.setEnabled(false); //disable send button if no text entered
                else
                    Send.setEnabled(true);  //otherwise enable

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
        if(message_body.length() == 0) Send.setEnabled(false);//disable at app start
    }
}
