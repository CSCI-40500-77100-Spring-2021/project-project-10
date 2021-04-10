package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.AuthException;

import services.authhandler.AmplifyService;
import services.authhandler.AuthEvents;
import services.authhandler.AuthHandler;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Log.i("MyAmplifyApp", "1st Passing on config");


        try {
            AmplifyService.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized AmplifyService");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize AmplifyService", error);
        }

        //AuthHandler.signUp("svhnstorage@gmail.com", "heygirl", "Heygirl123~");

        AuthHandler.signOut(new AuthEvents() {
            @Override
            public void onSuccess() {
                AuthHandler.signIn("heygirl", "Heygirl123~", new AuthEvents() {
                    @Override
                    public void onSuccess() {
                        Log.i("heyyyaaa","IT WORKED JESUS");
                    }

                    @Override
                    public void onFailure(AuthException authError) {
                        Log.i("heyyyaaa","IT FUCKED JESUS");
                    }
                });
            }

            @Override
            public void onFailure(AuthException authError) {

            }
        });
        //AuthHandler.confirmSignUp("heygirl", "");

        //AuthHandler.signOut();
        goToOnboardingActivity();
    }

    private void goToOnboardingActivity() {
        Intent i = new Intent(this, OnboardingActivity.class);
        startActivity(i);
        finish();
    }

}