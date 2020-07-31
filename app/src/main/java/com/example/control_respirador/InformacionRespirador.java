package com.example.control_respirador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InformacionRespirador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_respirador);
    }


    public  void regreso_menu(View view){

        Intent regreso = new Intent(this,Menu.class);
        startActivity(regreso);
    }// fin metodo
}
