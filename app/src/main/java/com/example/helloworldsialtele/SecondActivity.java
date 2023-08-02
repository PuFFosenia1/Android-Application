package com.example.helloworldsialtele;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {
    private Bluetooth bluetooth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btn_4 = findViewById(R.id.button4);
        bluetooth = new Bluetooth(); // Initialize the Bluetooth object



        TextView textView_4 = findViewById(R.id.text_view_4);

        TextView textView = findViewById(R.id.text_view);
        TextView textView_7 = findViewById(R.id.text_view_7);
        TextView textView_10 = findViewById(R.id.text_view_10);

        btn_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean connected = bluetooth.connect("00:22:05:00:45:5A"); // Replace with your own Bluetooth module's address
                if (connected) {
                    Toast.makeText(SecondActivity.this, "Connected to Bluetooth module", Toast.LENGTH_SHORT).show();
                 //   bluetooth.sendData("1");

                    // receive the data from the device
                    String[] text = bluetooth.receiveData();
                    textView.setText(text[1]);
                    textView_4.setText(text[2]);
                    textView_7.setText(text[3]);
                    textView_10.setText(text[4]);

                } else {
                    Toast.makeText(SecondActivity.this, "Failed to connect to Bluetooth module", Toast.LENGTH_SHORT).show();

                }

            }

    });


    }

}