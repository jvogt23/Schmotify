package com.example.schmotify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.schmotify.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

        private ActivityMainBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                binding = ActivityMainBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());

        }

}
