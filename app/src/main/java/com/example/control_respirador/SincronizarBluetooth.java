package com.example.control_respirador;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class SincronizarBluetooth extends AppCompatActivity {

    //private TextView fechaSalida;
    //ConnectivityManager conex = (ConnectivityManager) getSystemService((Context.CONNECTIVITY_SERVICE));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizar_bluetooth);

        Calendar c = Calendar.getInstance();
        //System.out.println(c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time

        Toast.makeText(this, formattedDate, Toast.LENGTH_LONG).show();

        //fechaSalida = (TextView)findViewById(R.id.UI_fecha);
         //Now we display formattedDate value in TextView
        //fechaSalida.setText(formattedDate);
        //fechaSalida.setGravity(Gravity.CENTER);
        //fechaSalida.setTextSize(20);


        //boolean conexion;
        //conexion = conex.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
/*
        if(!conexion){

            Toast.makeText(this," BLUETOOTH NO ACTIVADO", Toast.LENGTH_LONG).show();
        }

        else {
            Toast.makeText(this," BLUETOOTH ACTIVO", Toast.LENGTH_LONG).show();

        }
*/

    }

    public void sicronizar(View View){


        Toast.makeText(this," ELIJA UN DISPOSITIVO A VINCULAR", Toast.LENGTH_SHORT).show();

        Intent sincronizara= new Intent(this,ListaParaSincronizar.class );
        startActivity(sincronizara);


           }


    public void ver_dispositivos(View View){


        Toast.makeText(this," DISPOSITIVOS DISPONIBLE", Toast.LENGTH_SHORT).show();

        Intent dispositivos= new Intent(this,DispositivosBT.class );
        startActivity(dispositivos);






    }


    }






