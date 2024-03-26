package com.example.schmotify;

import android.os.Bundle;

import com.example.schmotify.databinding.ActivityLoginBinding;
import com.example.schmotify.ui.login.LoginFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
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


//        if (navHostFragment.getChildFragmentManager().getFragments().get(1).getView().findViewById(R.id.returnToLogin) != null) {
//            TextView reg2Sign = (TextView) findViewById(R.id.returnToLogin);
//            reg2Sign.setOnClickListener(new View.OnClickListener()
//            {
//                public void onClick(View view)
//                {
//                    navController.navigate(R.id.action_navigation_register_to_navigation_login);
//                }
//            });
//        }
//
//
//        if (navHostFragment.getChildFragmentManager().getFragments().get(2).getView().findViewById(R.id.retFromError) != null) {
//            Button retFromError = (Button) findViewById(R.id.retFromError);
//            retFromError.setOnClickListener(new View.OnClickListener()
//            {
//                public void onClick(View view)
//                {
//                    navController.navigate(R.id.action_navigation_error_to_navigation_register);
//                }
//            });
//        }

}
