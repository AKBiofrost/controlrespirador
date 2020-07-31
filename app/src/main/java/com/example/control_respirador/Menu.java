package com.example.control_respirador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    // metodos
    public  void Siguiente_frecuencia(View view){

        Intent siguiente_frecuencia = new Intent(this,Frecuencia.class);
        startActivity(siguiente_frecuencia);
    }// fin metodo
    public  void Siguiente_giro(View view){

        Intent siguiente_giro = new Intent(this,Graficas.class);
        startActivity(siguiente_giro);
    }// fin metodo
    public  void Siguiente_informacion(View view){

        Intent siguiente_informacion = new Intent(this,InformacionRespirador.class);
        startActivity(siguiente_informacion);
    }// fin metodo


}
