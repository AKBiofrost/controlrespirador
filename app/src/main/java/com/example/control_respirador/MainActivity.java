package com.example.control_respirador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getBaseContext(), "BIENVENIDO AL CONTROL DEL RESPIRADOR - SIGA LAS INSTRUCCIONES PARA EL TEST ", Toast.LENGTH_LONG).show();

    }
// metodos
public  void Siguiente(View view){

    Intent siguiente = new Intent(this,SincronizarBluetooth.class);
    startActivity(siguiente);
}// fin metodo

    public  void Siguiente_informacion(View view){

        Intent siguiente_informacion = new Intent(this,InformacionRespirador.class);
        startActivity(siguiente_informacion);
    }// fin metodo

}
