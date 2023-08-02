package com.example.helloworldsialtele;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.WeakHashMap;

public class Bluetooth {
    private static final String TAG = "Bluetooth";
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // Replace with your own UUID
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBluetoothDevice;
    private BluetoothSocket mBluetoothSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private boolean mIsConnected;

    public Bluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean connect(String address) {
        if (!mBluetoothAdapter.isEnabled()) {
            Log.d(TAG, "Bluetooth is not enabled");
            return false;
        }
        mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(address);
        try {
            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID);
            mBluetoothSocket.connect();
            mOutputStream = mBluetoothSocket.getOutputStream();
            mInputStream = mBluetoothSocket.getInputStream();
            mIsConnected = true;
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Failed to connect to Bluetooth device", e);
            return false;
        }
    }

    public void disconnect() {
        try {
            if (mBluetoothSocket != null) {
                mIsConnected = false;
                mBluetoothSocket.close();
            }
        } catch (IOException e) {
            Log.e(TAG, "Error closing Bluetooth socket", e);
        }
    }

    public void sendData(String data) {
        try {
            mOutputStream.write(data.getBytes());
        } catch (IOException e) {
            Log.e(TAG, "Error sending data over Bluetooth", e);
        }
    }

   public String[] receiveData() {
       String[] data = new String[5];
       try {
           InputStream inStream = mBluetoothSocket.getInputStream();

           byte[] buffer = new byte[131072];
           int bytes;

            while(true){

           bytes = inStream.read(buffer);

           System.out.println(bytes);
           String receivedData = new String(buffer, 0, bytes, "UTF-8");

           String[] dataParts = receivedData.split(",");

           if (dataParts.length == 5) {
           data[0] = dataParts[0];
           data[1] = dataParts[1];
           data[2] = dataParts[2];
           data[3] = dataParts[3];
           data[4] = dataParts[4];
           return data;
           }}

       } catch (IOException e) {
           Log.e(TAG, "Error receiving data over Bluetooth", e);
       }
       return null;
   }
  /*public String[] receiveData() {
      String startDelimiter = "<";
      String endDelimiter = ">";
      String[] data = new String[4];
      StringBuilder receivedDataBuilder = new StringBuilder();
      InputStream inStream = null;
      try {
          inStream = mBluetoothSocket.getInputStream();

      byte[] buffer = new byte[4096];
      int bytes;
      while (true) {
          // Read data into the buffer
          bytes = inStream.read(buffer);

          // Convert the buffer to a String
          String receivedData = new String(buffer, 0, bytes, "UTF-8");

          // Append the received data to the builder
          receivedDataBuilder.append(receivedData);

          // Check if the received data contains the end delimiter
          if (receivedData.contains(endDelimiter)) {
              // Extract the data between the start and end delimiters
              String completeData = receivedDataBuilder.substring(
                      receivedDataBuilder.indexOf(startDelimiter) + 1,
                      receivedDataBuilder.indexOf(endDelimiter)
              );

              // Split the complete data into separate variables based on a delimiter
              String[] dataParts = completeData.split(",");

              // Check if the dataParts array has at least 4 elements
              if (dataParts.length == 4) {
                  data[0] = dataParts[0];
                  data[1] = dataParts[1];
                  data[2] = dataParts[2];
                  data[3] = dataParts[3];
                  return data;
              }

              // Reset the receivedDataBuilder for the next data packet
              receivedDataBuilder.setLength(0);
          }
      }
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null;
  }*/






























}

