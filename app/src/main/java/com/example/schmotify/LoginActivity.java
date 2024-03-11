package com.example.schmotify;

import android.os.Bundle;

import com.example.schmotify.databinding.ActivityLoginBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_activity_login);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();


        TextView sign2Reg = (TextView) findViewById(R.id.signToReg);
        sign2Reg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                navController.navigate(R.id.action_navigation_login_to_navigation_register);
            }
        });

        TextView reg2Sign = (TextView) findViewById(R.id.regToSign);
        reg2Sign.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                navController.navigate(R.id.action_navigation_register_to_navigation_login);
            }
        });



    }
}
