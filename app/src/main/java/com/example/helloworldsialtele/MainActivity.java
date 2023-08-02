package com.example.helloworldsialtele;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {


   // private static String address = "00:22:05:00:45:5A"; // replace with your module's MAC address
   // UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_2 = findViewById(R.id.button2);
        Button btn_1 = findViewById(R.id.button);


        btn_2.setOnClickListener(new View.OnClickListener() {

        public void onClick(View view){

        openThird();

        }
    });

        btn_1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){

                openSecond();
            }
        });


    }


    public void openSecond(){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    public void openThird(){
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }


}