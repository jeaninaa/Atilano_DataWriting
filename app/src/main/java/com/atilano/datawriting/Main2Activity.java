package com.atilano.datawriting;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    TextView tvDisplay;
    FileInputStream fis = null;
    String fileStorage = null;

    int read = 0;

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
    }

    private void intCache(View view) {
        fileStorage = "data1.txt";
        StringBuffer buffer = new StringBuffer();
        File directory = getCacheDir();
        read = 0;

        try {
            fis = new FileInputStream(new File(directory, fileStorage));
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

    private void extCache(View view) {
        fileStorage = "data2.txt";
        StringBuffer buffer = new StringBuffer();
        File directory = getExternalCacheDir();
        read = 0;

        try {
            fis = new FileInputStream(new File(directory, fileStorage));
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

    private void extStorage(View view) {
        fileStorage = "data3.txt";
        StringBuffer buffer = new StringBuffer();
        File directory = getExternalFilesDir("Temp");
        read = 0;

        try {
            fis = new FileInputStream(new File(directory, fileStorage));
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

    private void extPubStorage(View view) {
        fileStorage = "data1.txt";
        StringBuffer buffer = new StringBuffer();
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        read = 0;

        try {
            fis = new FileInputStream(new File(directory, fileStorage));
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


    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}