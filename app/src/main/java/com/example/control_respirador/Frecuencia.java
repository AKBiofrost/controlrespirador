package com.example.control_respirador;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import android.os.Handler;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class Frecuencia extends AppCompatActivity {

    private static String address = null;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket=null;
    private ConnectedThread MyconexionBT;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    Handler bluetoothIn;
    final int handlerState = 0;



    Button volumne,respiracion, inspiracion, espiracion;
    EditText volumenText, respiracionText, inspiracionText, espiracionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frecuencia);
        Toast.makeText(this," envie los parametros al respirador", Toast.LENGTH_LONG).show();

        volumne= (Button) findViewById(R.id.enviar_volumen);
        respiracion= (Button) findViewById(R.id.enviar_respiracion);
        inspiracion= (Button) findViewById(R.id.enviar_Inspiracion);
        espiracion= (Button) findViewById(R.id.enviar_espiracion);
        volumenText =(EditText)findViewById(R.id.Volumen);
        respiracionText =(EditText)findViewById(R.id.respiracion_minutos);
        espiracionText =(EditText)findViewById(R.id.espiracion);
        inspiracionText =(EditText)findViewById(R.id.Inspiracion);



        volumne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                String valorvolumen = volumenText.getText().toString();
                MyconexionBT.write(valorvolumen);

                Toast.makeText(Frecuencia.this,valorvolumen, Toast.LENGTH_LONG).show();
            }
        });


        respiracion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                String valorrespiracion = respiracionText.getText().toString();
                MyconexionBT.write(valorrespiracion);
                Toast.makeText(Frecuencia.this,valorrespiracion, Toast.LENGTH_LONG).show();
            }
        });


        inspiracion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                String valorinspiracion = inspiracionText.getText().toString();
                MyconexionBT.write(valorinspiracion);
                Toast.makeText(Frecuencia.this,valorinspiracion, Toast.LENGTH_LONG).show();
            }
        });


        espiracion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                String valor_espiracion = espiracionText.getText().toString();
                MyconexionBT.write(valor_espiracion);
                Toast.makeText(Frecuencia.this,valor_espiracion, Toast.LENGTH_LONG).show();
            }
        });





    }
    //---------------------------------------------------------------------------------------------//
    public  void regreso_menu(View view){

        Intent regreso = new Intent(this,Menu.class);
        startActivity(regreso);
    }// fin metodo


    //-----------------------------------------------------------------------//
private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
    //crea un conexion de salida segura para el dispositivo
    //usando el servicio UUID
    return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
}
//------------------------------------------------------------------------------//


    public void onResume() {

        super.onResume();

        //Consigue la direccion MAC desde DeviceListActivity via intent
        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        address = intent.getStringExtra(ListaParaSincronizar.EXTRA_DEVICE_ADDRESS);
        //Setea la direccion MAC
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try
        {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establece la conexión con el socket Bluetooth.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {}
        }
        MyconexionBT = new ConnectedThread(btSocket);
        MyconexionBT.start();
    }//fin de resumen

//--------------------------------------------------------------------------------------------------

public void onPause() {
    super.onPause();
    try
    { // Cuando se sale de la aplicación esta parte permite
        // que no se deje abierto el socket
        btSocket.close();
    } catch (IOException e2) {}
}

    //Comprueba que el dispositivo Bluetooth Bluetooth está disponible y solicita que se active si está desactivado
private void VerificarEstadoBT() {

        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }


    }


    //Crea la clase que permite crear el evento de conexion
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        //Envio de trama
        public void write(String input)
        {
            try {
                mmOutStream.write(input.getBytes());
            }
            catch (IOException e)
            {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }
//---------------------------------------------------------------------------------------------










}// fin del objeto


