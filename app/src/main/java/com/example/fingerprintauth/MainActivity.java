 package com.example.fingerprintauth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;

import java.util.concurrent.Executor;

 public class MainActivity extends AppCompatActivity {
     private TextView textView;
     private ImageView imageView;
     LottieAnimationView lottieAnimationView;
     private FingerprintManager fingerprintManager;
     private FingerprintManager.AuthenticationCallback authenticationCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        lottieAnimationView = findViewById(R.id.fingerprint1);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        authenticationCallback = new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                textView.setText("ERROR");

                super.onAuthenticationError(errorCode, errString);
            }
            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                textView.setText("HELP");

                super.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                textView.setText("SUCCESS");
                lottieAnimationView.setAnimation(R.raw.fingerprint);
                super.onAuthenticationSucceeded(result);
            }
            @Override
            public void onAuthenticationFailed() {
                textView.setText("FAILED");
                
                super.onAuthenticationFailed();
            }
        };}
        public void scanButton(View view){
            fingerprintManager.authenticate(null, null, 0, authenticationCallback, null);
        }
    }