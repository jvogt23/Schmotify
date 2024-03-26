package com.example.schmotify;

import android.os.Bundle;

import com.example.schmotify.databinding.ActivityLoginBinding;

import androidx.appcompat.app.AppCompatActivity;


import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_activity_login);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
    }

    public void onRegSwapClicked() {
        navController.navigate(R.id.action_navigation_login_to_navigation_register);
    }

    public void onLogSwapClicked() {
        navController.navigate(R.id.action_navigation_register_to_navigation_login);
    }

}
