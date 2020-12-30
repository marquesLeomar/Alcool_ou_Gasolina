package com.mqs.ecolhacertaalcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;

    private EditText editEtanol;
    private EditText editGasolina;
    private TextView txtResposta2;

    //private Button btCalcular;
    public double Resultado;
    double eco1, eco2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEtanol = findViewById(R.id.editEtanol);
        editGasolina = findViewById(R.id.editGasolina);
        txtResposta2 = findViewById(R.id.txtResposta2);






        MobileAds.initialize(this, new OnInitializationCompleteListener() {


            public void onInitializationComplete(InitializationStatus initializationStatus) {


            }





           /* */
        });


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4389621952707823/4463032268");


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }




    public void calcularValor(View view) {
        int vermelho = getResources().getColor(R.color.corGasolina);
        int verde = getResources().getColor(R.color.corAlcool);

        //recuperando valores digitados
        String precoEtanol = editEtanol.getText().toString();
        String precoGasolina = editGasolina.getText().toString();

        boolean camposValidados = this.validarCampos(precoEtanol , precoGasolina);
        if (camposValidados)
        {
                   /* mudartela();
                    Intent enviar = new Intent(getApplicationContext(),RespostaCalculo.class)
                    txtResposta.setText("Você deve preencher os preços primeiro");*/
                   this.calcularprecomelhor(precoEtanol , precoGasolina);


            //3txtResposta2.setText("Você");
            //mudartela();
        }
        else
        {
            txtResposta2.setText("Você deve preencher os preços primeiro");



        }

    }

    public void calcularprecomelhor(String pAlcool, String pGasolina)
    {
        int vermelho = getResources().getColor(R.color.corGasolina);
        int verde = getResources().getColor(R.color.corAlcool);
        //converter valor para numeros
        double precoAlcol =  Double.parseDouble(pAlcool);
        double precoGasolina =  Double.parseDouble(pGasolina);
        Toast.makeText(getBaseContext(), "Veja a baixo o resultado",Toast.LENGTH_LONG).show();


        if(precoAlcol<0.7*precoGasolina)
        {
            Resultado = (precoGasolina*0.7)/precoAlcol;
            eco2=(Resultado-1)*100;
            eco1 =0;
        }
        else if(precoAlcol==0.7*precoGasolina)
        {
            Resultado = 0.7;
            eco2=(Resultado-0.7)*100;
            eco1=1;
        }
        else
        {
            Resultado = precoAlcol/(precoGasolina*0.7);
            eco2=(Resultado-1)*100;
            eco1=2;
        }


        if (eco1==0)
        {
            txtResposta2.setTextColor(verde);
            txtResposta2.setText("Hoje é melhor  utilizar o ALCOOL, pois você tem uma economia de: " + eco2 + "%");


        }
        else if (eco1==1)
        {
            txtResposta2.setText("Hoje tanto faz usar ALCOOL ou GASOLINA");
        }
        else if (eco1== 2)
        {
            txtResposta2.setTextColor(vermelho);
            txtResposta2.setText("Hoje é melhor  utilizar a GASOLINA, pois você tem uma economia de: " + eco2 + "%");

        }



    }

    //validar os campos
    public boolean validarCampos(String pAlcool, String pGasolina) {
        boolean camposValidados = true;


        if (pAlcool == null || pAlcool.equals(" "))
        {

            camposValidados = false;

        }
        else if (pGasolina == null || pGasolina.equals(""))
        {

            camposValidados = false;

        }

        return camposValidados;
    }


   /* public void mudartela()
    {

        startActivity(new Intent(MainActivity.this, RespostaCalculo.class));

    }*/
}