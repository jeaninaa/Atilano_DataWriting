package com.atilano.datawriting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etData, etFilename;
    Button btnNext;

    SharedPreferences preferences;
    FileOutputStream fos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
        etData = (EditText) findViewById(R.id.etData);
        etFilename = (EditText) findViewById(R.id.etFilename);
        btnNext = (Button) findViewById(R.id.btnNext);
    }

    public void loadPref (View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Data",etData.getText().toString());
        editor.putString("Filename",etFilename.getText().toString());
        editor.commit();

        Toast.makeText(this, "Successfully written to shared preferences", Toast.LENGTH_SHORT).show();
    }

    public void loadIStorage (View view){
        String path = etFilename.getText().toString();
        String data = "Data: " + etData.getText().toString() + " | ";
        String filename = "Filename: " + etFilename.getText().toString();

        try {
            fos = openFileOutput(path, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.write(filename.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Successfully written to internal storage", Toast.LENGTH_SHORT).show();

    }

    public void loadICache(View view){
        File folder = getCacheDir();
        String path = etFilename.getText().toString();
        String message = etData.getText().toString();

        File file = new File(folder, path);
        writeData(file, message);

        Toast.makeText(this, "Successfully written to internal cache", Toast.LENGTH_SHORT).show();
    }

    public void loadECache(View view){
        File folder = getExternalCacheDir();
        String path = etFilename.getText().toString();
        String message = etData.getText().toString();

        File file = new File(folder, path);
        writeData(file, message);

        Toast.makeText(this, "Successfully written to external cache", Toast.LENGTH_SHORT).show();
    }

    public void loadEStorage(View view){
        File folder = getExternalFilesDir("Temp");
        String path = etFilename.getText().toString();
        String message = etData.getText().toString();

        File file = new File(folder, path);
        writeData(file, message);

        Toast.makeText(this, "Successfully written to external storage", Toast.LENGTH_SHORT).show();
    }

    public void loadEPStorage(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String path = etFilename.getText().toString();
        String message = etData.getText().toString();

        File file = new File(folder, path);
        writeData(file, message);

        Toast.makeText(this, "Successfully written to external public storage", Toast.LENGTH_SHORT).show();
    }


    public void writeData(File file, String message) {
        try{
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void next (View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
