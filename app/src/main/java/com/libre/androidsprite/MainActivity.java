package com.libre.androidsprite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.libre.androidsprite.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_ASE = 1001;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Texto desde C++
        binding.textView.setText(stringFromJNI());

        // 👉 Abrir selector al tocar el TextView (puedes cambiarlo por un botón)
        binding.textView.setOnClickListener(v -> openAsePicker());
    }

    // ===============================
    // Selector de archivos (.ase)
    // ===============================
    private void openAsePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*"); // LibreSprite usa .ase
        startActivityForResult(intent, PICK_ASE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_ASE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            copyAseToInternal(uri);
        }
    }

    // ===============================
    // Copiar archivo a ruta interna
    // ===============================
    private void copyAseToInternal(Uri uri) {
        try {
            InputStream in = getContentResolver().openInputStream(uri);
            File outFile = new File(getFilesDir(), "input.ase");
            OutputStream out = new FileOutputStream(outFile);

            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            in.close();
            out.close();

            Log.i("ASE", "Archivo copiado a: " + outFile.getAbsolutePath());

        } catch (Exception e) {
            Log.e("ASE", "Error copiando archivo", e);
        }
    }

    // ===============================
    // JNI
    // ===============================
    public native String stringFromJNI();

    static {
        System.loadLibrary("myapplication");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}