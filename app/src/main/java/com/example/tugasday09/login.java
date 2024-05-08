package com.example.tugasday09;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugasday09.data.ApiClient;
import com.example.tugasday09.data.ApiInterface;
import com.example.tugasday09.data.model.Login;
import com.example.tugasday09.data.model.LoginData;
import com.example.tugasday9.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    Button btnLogin;
    TextView tvCreateAccount;
    EditText etUsername, etPassword;
    String usernameLogin, passwordLogin;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.tugasday9.R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnlogin);
        tvCreateAccount = findViewById(R.id.tvCA);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameLogin = etUsername.getText().toString().trim();
                passwordLogin = etPassword.getText().toString().trim();
                login(usernameLogin, passwordLogin);
            }
        });

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });

    }

    private void login(String username, String password) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(username,password);

        loginCall.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    sessionManager = new SessionManager(login.this);
                    LoginData loginData = response.body().getData();
                    sessionManager.createLoginSession(loginData);

                    Toast.makeText(login.this, response.body().getData().getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}