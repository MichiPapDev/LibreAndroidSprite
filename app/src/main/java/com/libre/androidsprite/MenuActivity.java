package com.libre.androidsprite;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.libre.androidsprite.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
