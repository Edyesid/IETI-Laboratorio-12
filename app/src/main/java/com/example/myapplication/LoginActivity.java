package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.LoginWrapper;
import com.example.myapplication.model.Token;
import com.example.myapplication.services.AuthService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String MESSAGE = "This field cannot be empty";
    private AuthService authService;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:/10.0.2.2:8080") //localhost for emulator
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthService authService = retrofit.create(AuthService.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void onLogin(View view) {
        EditText email = findViewById(R.id.editTextTextPersonName);
        EditText password = findViewById(R.id.editTextTextPersonName2);
        if (email.getText().toString().isEmpty()) {
            email.setError(MESSAGE);
        }
        if (password.getText().toString().isEmpty()) {
            password.setError(MESSAGE);
        }
        LoginWrapper loginWrapper = new LoginWrapper(email.getText().toString(), password.getText().toString());
        executorService.execute(login(authService,email,password));
    }

    private Runnable login(AuthService authService, EditText email, EditText password) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Response<Token> response = authService.login(new LoginWrapper(email.getText().toString(), password.getText().toString())).execute();
                            authService.login( new LoginWrapper( "test@mail.com", "password" ) ).execute();
                    Token token = response.body();
                    SharedPreferences sharedPref =
                            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("TOKEN_KEY",token.getToken());
                    editor.commit();
                    if (token != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        finish();
                    } else {
                        email.setError("Verify Your email");
                        password.setError("verify Your Password");
                    }
                }
                catch (Exception e ) {
                    e.printStackTrace();
                }
            }
        };
    }
}
