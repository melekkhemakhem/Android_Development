package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.nio.channels.SelectionKey;


public class MainActivity extends AppCompatActivity {
private TextView tvAge, tvReponse;
private EditText etValeur;
private SeekBar sbAge;
private RadioButton rbIsFasting,rbIsNotFasting;
private Button btnConsulter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                     Log.i("Information","onProgressChange"+i);
                     tvAge.setText("Votre age: "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Information", "button cliqué");
                boolean verifAge = false, verifValeur = false;

                if(sbAge.getProgress()!=0)
                    verifAge = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre age !", Toast.LENGTH_SHORT).show();

                if(!etValeur.getText().toString().isEmpty())
                    verifValeur = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesurée !", Toast.LENGTH_LONG).show();

                if(verifAge && verifValeur)
                {
                    calculer();
                }
            }
        });
    }
    private void calculer(){
        Log.i("Information","OnClick sur le bbutton btnConsulter");
        int age;float valeur;
        boolean verifAge=false,verifValeur=false;
        if(sbAge.getProgress()!=0)
            verifAge=true;
        else
            Toast.makeText(MainActivity.this,"veillez saisir votre Age",Toast.LENGTH_SHORT).show();
        if(etValeur.getText().toString().isEmpty())
            Toast.makeText(MainActivity.this,"Veuillez saisir votre valeur mesurée",Toast.LENGTH_LONG).show();
        else
            verifValeur=true;
        if(verifAge && verifValeur)
        {
            age=sbAge.getProgress();
            valeur=Float.valueOf(etValeur.getText().toString());
            if(rbIsFasting.isChecked())//oui
            {
                if (age >= 13) {
                    if (valeur < 5.0)
                        tvReponse.setText("Niveau de glycémie est trop bas");
                    else if (valeur >= 5.0 && valeur <= 7.2)
                        tvReponse.setText("Niveau de glycémie est normale");
                    else
                        tvReponse.setText("Niveau de glycémie est trop élevé");
                } else if (age >= 6 && age <= 12) {
                    if (valeur < 5.0)
                        tvReponse.setText("Niveau de glycémie est trop bas");
                    else if (valeur >= 5.0 && valeur <= 10.0)
                        tvReponse.setText("Niveau de glycémie est normale");
                    else
                        tvReponse.setText("Niveau de glycémie est trop élevé");
                } else if (age < 6) {
                    if (valeur < 5.5)
                        tvReponse.setText("Niveau de glycémie est trop bas");
                    else if (valeur >= 5.5 && valeur <= 10.0)
                        tvReponse.setText("Niveau de glycémie est normale");
                    else
                        tvReponse.setText("Niveau de glycémie est trop élevé");
                }
            } else {
                if (valeur > 10.5)
                    tvReponse.setText("Niveau de glycémie est trop élevé");
                else
                    tvReponse.setText("Niveau de glycémie est normale");
            }
    }
    }
    private void init(){
        tvAge=findViewById(R.id.tv_age);
        tvReponse=findViewById(R.id.tvReponse);
        sbAge=findViewById(R.id.sbage);
        etValeur=findViewById(R.id.et_measurement_value);
        rbIsFasting=findViewById(R.id.rbtNon);
        rbIsNotFasting=findViewById(R.id.rbtNon);
        btnConsulter=findViewById(R.id.btnConsulter);
    }
}