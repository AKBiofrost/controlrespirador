package com.example.control_respirador;


import android.os.Bundle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class DispositivosBT extends AppCompatActivity {

    //--------------------------------------------------------------------------------------------
    private static final String TAG = "DispositivosBT";
    ListView IdLista;
    public static String EXTRA_DEVICE_ADDRESS = "DIVICE_ADDRESS";
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;

    //-------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_bt);

        Toast.makeText(this, "VERIFIQUE YA ESTA VINCULADO EL DISPOSITIVO LE INTERESA", Toast.LENGTH_SHORT).show();

    }


//-------------------------dispositivos vinculados-------------------------------------------------

    public void onResume() {

        super.onResume();
//-------------------------------------------------------------------------------------------------
        verificarEstadoBT();
        //-------------------------------------------------------------------------------------------------
        //Inicializa la array que contendra la lista de los dispositivos bluetooth vinculados
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.nombre_dispositivos);
        IdLista = (ListView) findViewById(R.id.IdListaView);
        IdLista.setAdapter(mPairedDevicesArrayAdapter);
        // IdLista.setOnItemClickListener(mDeviceClickListener);
        //Obtine el adaptador local bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        //Obtiene un conjnto de dispositivos actualmente disponible
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        //adiciona un dispositivo previo emparejado al array

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }
//--------------------------------------fin resume-------------------------------------------------



    private void verificarEstadoBT() {
//comprueba que el dispositivo tiene BLuetooth y que esta encendido

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null)
            Toast.makeText(this, "El dispositivo no soporta BLuetooth", Toast.LENGTH_SHORT).show();

        else if (mBtAdapter.isEnabled()) Log.d(TAG, "...BLURTOOTH ACTIVADO...");

        else {
            //solicita al usuario que active bluetooht
            Intent enableBIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBIntent, 1);
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

}
