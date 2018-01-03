package com.kuri.motorcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

public class ControlsActivity extends AppCompatActivity {

    private static final String TAG = "bluetooth1";;
    Button bluetoothButton, cwBtn, ccwBtn, servoBtn, cwBtn2, ccwBtn2, allcwBtn, allccwBtn;
    final int IDENTIFIER = 0;
    private final String DEVICE_ADDRESS = "98:D3:31:FD:52:00";//"F0:C7:7F:ED:FB:14";//HM-10   //"98:D3:31:FD:52:00"; //HC-06
    //UUID
    //A class that represents an immutable universally unique identifier (UUID). A UUID represents a 128-bit value.
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothSocket socket;
    private OutputStream outputStream;
    BluetoothDevice device;
    boolean connectionSet;

    String motorSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);

        bluetoothButton = (Button) findViewById(R.id.bluetooth_find);
        cwBtn = (Button) findViewById(R.id.cw);
        ccwBtn = (Button) findViewById(R.id.ccw);
        cwBtn2 = (Button) findViewById(R.id.cw_mt2);
        ccwBtn2 = (Button) findViewById(R.id.ccw_mt2);
        allcwBtn = (Button) findViewById(R.id.all_cw);
        allccwBtn = (Button) findViewById(R.id.all_ccw);
        servoBtn = (Button) findViewById(R.id.servo_180);

        connectionSet = false;


        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBluetoothDevice()){
                    if(BTconnect()){
                        connectionSet = true;
                    }
                    //Toast.makeText(MainActivity.this, "Connected check 2", Toast.LENGTH_SHORT); --> DOESN'T DISPLAY .show() missing
                }
            }
        });


        cwBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){ //MotionEvent.ACTION_DOWN is when you hold a button down
                    motorSignal = "1";

                    try {
                        outputStream.write(motorSignal.getBytes()); //transmits the value of command to the bluetooth module
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){ //MotionEvent.ACTION_UP is when you release a button
                    motorSignal = "10";
                    try{
                        outputStream.write(motorSignal.getBytes());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        ccwBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){ //MotionEvent.ACTION_DOWN is when you hold a button down
                    motorSignal = "2";

                    try{
                        outputStream.write(motorSignal.getBytes()); //transmits the value of command to the bluetooth module
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){ //MotionEvent.ACTION_UP is when you release a button
                    motorSignal = "10";
                    try{
                        outputStream.write(motorSignal.getBytes());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        cwBtn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){ //MotionEvent.ACTION_DOWN is when you hold a button down
                    motorSignal = "3";

                    try {
                        outputStream.write(motorSignal.getBytes()); //transmits the value of command to the bluetooth module
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){ //MotionEvent.ACTION_UP is when you release a button
                    motorSignal = "10";
                    try{
                        outputStream.write(motorSignal.getBytes());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        ccwBtn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){ //MotionEvent.ACTION_DOWN is when you hold a button down
                    motorSignal = "4";

                    try {
                        outputStream.write(motorSignal.getBytes()); //transmits the value of command to the bluetooth module
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){ //MotionEvent.ACTION_UP is when you release a button
                    motorSignal = "10";
                    try{
                        outputStream.write(motorSignal.getBytes());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        allcwBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){ //MotionEvent.ACTION_DOWN is when you hold a button down
                    motorSignal = "5";

                    try {
                        outputStream.write(motorSignal.getBytes()); //transmits the value of command to the bluetooth module
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){ //MotionEvent.ACTION_UP is when you release a button
                    motorSignal = "10";
                    try{
                        outputStream.write(motorSignal.getBytes());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });


        allccwBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){ //MotionEvent.ACTION_DOWN is when you hold a button down
                    motorSignal = "6";

                    try {
                        outputStream.write(motorSignal.getBytes()); //transmits the value of command to the bluetooth module
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){ //MotionEvent.ACTION_UP is when you release a button
                    motorSignal = "10";
                    try{
                        outputStream.write(motorSignal.getBytes());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });




        /*servoBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){ //MotionEvent.ACTION_DOWN is when you hold a button down
                    motorSignal = "3";

                    try{
                        outputStream.write(motorSignal.getBytes()); //transmits the value of command to the bluetooth module
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){ //MotionEvent.ACTION_UP is when you release a button
                    motorSignal = "10";
                    try{
                        outputStream.write(motorSignal.getBytes());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });*/
    }


    public boolean checkBluetoothDevice(){

        boolean present = false;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter!=null){ //check whether device supports bluetooth
            if(!bluetoothAdapter.isEnabled()) { //check whether bluetooth enabled
                Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableAdapter, IDENTIFIER);
            }
            //get nearby bluetooth devices
            Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
            if(bondedDevices.isEmpty()){ //Checks for paired bluetooth devices
                Toast.makeText(getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
            }
            else{
                for(BluetoothDevice iterator : bondedDevices){
                    if(iterator.getAddress().equals(DEVICE_ADDRESS)){
                        device = iterator;
                        present = true;
                        //Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT); --> DOESN'T WORK? .show() missing.
                        break;
                    }
                }
            }
        }else{
            Toast.makeText(ControlsActivity.this, "This device does not support Bluetooth", Toast.LENGTH_SHORT);
        }

        return present;

    }


    public boolean BTconnect(){
        boolean connected = true;

        //To create a BluetoothSocket for connecting to a known device,
        //use BluetoothDevice.createRfcommSocketToServiceRecord().
        //Then call connect() to attempt a connection to the remote device.
        //This call will block until a connection is established or the connection fails.

        //Once the socket is connected, whether initiated as a client or accepted as a server,
        //open the IO streams by calling getInputStream() and getOutputStream()
        //in order to retrieve InputStream and OutputStream objects, respectively,
        //which are automatically connected to the socket.

        try{
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID); //Creates a socket to handle the outgoing connection
            //socket = createBluetoothSocket(device);
            socket.connect();
            Toast.makeText(ControlsActivity.this, "Connection to bluetooth device successful", Toast.LENGTH_LONG).show();
        }catch(IOException e) {
            e.printStackTrace();
            connected = false;
        }

        if(connected){
            try{
                outputStream = socket.getOutputStream(); //gets the output stream of the socket
                if(socket == null){
                    Toast.makeText(ControlsActivity.this, "no socket", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(ControlsActivity.this, "output stream obtained", Toast.LENGTH_LONG).show();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }

        return connected;
    }
    //alternate socket method
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if(Build.VERSION.SDK_INT >= 10){
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
                return (BluetoothSocket) m.invoke(device, PORT_UUID);
            } catch (Exception e) {
                Log.e(TAG, "Could not create Insecure RFComm Connection",e);
            }
        }
        return  device.createRfcommSocketToServiceRecord(PORT_UUID);
    }

}
