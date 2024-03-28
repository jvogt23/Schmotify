package com.example.schmotify.ui.login;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.example.schmotify.LoginActivity;
import com.example.schmotify.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        TextView sign2Reg = (TextView) rootView.findViewById(R.id.signToReg);
        sign2Reg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if (getActivity() instanceof LoginActivity) {
                ((LoginActivity) getActivity()).onRegSwapClicked();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            // kick to main activity and spotify API call.
        }
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Get associated account info with db query
                            // hop to main activity
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            Toast.makeText(getActivity(), "Invalid email or password.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}