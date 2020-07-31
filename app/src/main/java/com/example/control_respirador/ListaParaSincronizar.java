package com.example.control_respirador;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Set;




public class ListaParaSincronizar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_para_sincronizar);
    }




    //--------------------------------------------------------------------------------------------
    private static final String TAG = "ListaParaSincronizar";
    ListView IdLista;
    public static String EXTRA_DEVICE_ADDRESS = "DIVICE_ADDRESS";
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;

    //-------------------------------------------------------------------------------------------

//-------------------------dispositivos vinculados-------------------------------------------------

    public void onResume() {

        super.onResume();
//-------------------------------------------------------------------------------------------------
        verificarEstadoBT();
        //-------------------------------------------------------------------------------------------------
        //Inicializa la array que contendra la lista de los dispositivos bluetooth vinculados
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.nombre_dispositivos);
        IdLista = (ListView) findViewById(R.id.IdListabluetooth);
        IdLista.setAdapter(mPairedDevicesArrayAdapter);
        // IdLista.setOnItemClickListener(mDeviceClickListener);
        //Obtine el adaptador local bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        //Obtiene un conjunto de dispositivos actualmente disponible
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        //adiciona un dispositivo previo emparejado al array

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }




    }
//--------------------------------------fin resume-------------------------------------------------


    //Configura un (on-click) para la lista
    private AdapterView.OnItemClickListener mDeviceClickListener=new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {

            //OBTENER LA DIRECCION MAC DEL DISPOSITIVO, QUE SON LOS ULTIMOS 17 CARACTERES EN LA VISTA

        String info =((TextView) v).getText().toString();
        String address=info.substring(info.length() - 17);
//------------------------------------------------------------------------------

        Intent i =new Intent(ListaParaSincronizar.this, Frecuencia.class);
        i.putExtra(EXTRA_DEVICE_ADDRESS, address);
        Toast.makeText(ListaParaSincronizar.this," SE PROBARA SINCRONIZACION", Toast.LENGTH_SHORT).show();
        startActivity(i);
//--------------------------------------------------------------------------------

    }

        };


    //----------------------------------------------------------------------------------------//

    private void verificarEstadoBT() {
//comprueba que el dispositivo tiene BLuetooth y que esta encendido

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null)
            Toast.makeText(this, "El dispositivo no soporta Bluetooth", Toast.LENGTH_SHORT).show();

        else if (mBtAdapter.isEnabled()) Log.d(TAG, "...BLUETOOTH ACTIVADO...");

        else {
            //solicita al usuario que active bluetooth
            Intent enableBIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBIntent, 1);
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

}

