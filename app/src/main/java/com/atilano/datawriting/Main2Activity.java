package com.atilano.datawriting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    TextView tvDisplay;
    EditText etFilenameLoad;

    SharedPreferences preferences;
    FileInputStream fis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);

        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        etFilenameLoad = (EditText) findViewById(R.id.etFilenameLoad);
    }

    public void sharedPref (View view){
        String data = preferences.getString("Data","");
        String filename = preferences.getString("Filename","");

        tvDisplay.setText("Data: " + data + " | Filename: " + filename);
    }

    public void intStorage(View view){
        String path = etFilenameLoad.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try {
            fis = openFileInput(path);
            while ((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void intCache(View view) {
        String path = etFilenameLoad.getText().toString();
        StringBuffer buffer = new StringBuffer();
        File directory = getCacheDir();
        int read = 0;

        try {
            fis = new FileInputStream(new File(directory, path));
            while ((read = fis.read()) != -1) {
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void extCache(View view) {
        String path = etFilenameLoad.getText().toString();
        StringBuffer buffer = new StringBuffer();
        File directory = getExternalCacheDir();
        int read = 0;

        try {
            fis = new FileInputStream(new File(directory, path));
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void extStorage(View view) {
        String path = etFilenameLoad.getText().toString();
        StringBuffer buffer = new StringBuffer();
        File directory = getExternalFilesDir("Temp");
        int read = 0;

        try {
            fis = new FileInputStream(new File(directory, path));
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void extPubStorage(View view) {
        String path = etFilenameLoad.getText().toString();
        StringBuffer buffer = new StringBuffer();
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        int read = 0;

        try {
            fis = new FileInputStream(new File(directory, path));
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void clearDisplay (View view){
        tvDisplay.setText("");
    }


    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}