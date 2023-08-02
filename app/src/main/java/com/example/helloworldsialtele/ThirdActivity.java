package com.example.helloworldsialtele;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private Bluetooth bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        bluetooth = new Bluetooth(); // Initialize the Bluetooth object


        Button btn_3 = findViewById(R.id.button3);
        Button btn_5 = findViewById(R.id.button5);

        btn_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean connected = bluetooth.connect("00:22:05:00:45:5A"); // Replace with your own Bluetooth module's address
                if (connected) {
                   bluetooth.sendData("1");

                } else {
                    Toast.makeText(ThirdActivity.this, "Failed to transmit to Bluetooth module", Toast.LENGTH_SHORT).show();
                }

            }

        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean connected = bluetooth.connect("00:22:05:00:45:5A"); // Replace with your own Bluetooth module's address
                if (connected) {
                    bluetooth.sendData("0");

                } else {
                    Toast.makeText(ThirdActivity.this, "Failed to transmit to Bluetooth module", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}