package com.example.schmotify.ui.login;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schmotify.Account;
import com.example.schmotify.LoginActivity;
import com.example.schmotify.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.*;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;


public class RegisterFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        TextView reg2Sign = (TextView) rootView.findViewById(R.id.returnToLogin);
        reg2Sign.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if (getActivity() instanceof LoginActivity) {
                    ((LoginActivity) getActivity()).onLogSwapClicked();
                }
            }
        });

        Button registerButton = (Button) rootView.findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                // check for non-null username
                // check for non-null initial password
                // check for matching passwords
                // createAccount(username, password);
                // go to MainActivity;
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

    public void createAccount(String username, String password) {

        // do a check and make sure that username is proprietary
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("usernames/");
//        if (ref == null) {
//            Toast.makeText(getActivity(), "Username already exists.",
//                    Toast.LENGTH_SHORT).show();
//            return;
//        }

        LoginActivity loginPage = (LoginActivity) getActivity();
        assert loginPage != null;
        loginPage.onRegisterClicked();
        JSONObject profileInfo = loginPage.getProfileInfo();

        String email = null;

        try {
            email = profileInfo.getString("email");
        } catch (Exception e) {
            Log.w(TAG, "getUserProfileJSONObject:failure", e);
            Toast.makeText(getActivity(), "There is no email associated with this account.",
                    Toast.LENGTH_SHORT).show();
        }

        // if profileInto.email exists, say no
//        Toast.makeText(getActivity(), "This spotify account is already associated with an account.",
//                    Toast.LENGTH_SHORT).show();
//            return;
        //

        String finalEmail = email;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();


                            assert user != null;
                            Account newAccount = new Account(user.getUid(), username, finalEmail, password);
                            // push that user account to the firebase database
                            // Switch activities
                        } else {
                            // If creation fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed. Email retrieved from spotify invalid or bad password.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}