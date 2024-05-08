package com.example.tugasday09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tugasday09.data.ApiClient;
import com.example.tugasday09.data.ApiInterface;
import com.example.tugasday09.data.model.Register;
import com.example.tugasday09.data.ApiClient;
import com.example.tugasday09.data.ApiInterface;
import com.example.tugasday09.data.model.Register;
import com.example.tugasday9.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setOnClickListener(this);

        binding.btnRegister.setOnClickListener(view -> {
            String username = binding.etRegisterUsername.getText().toString();
            String name = binding.etRegisterName.getText().toString();
            String password = binding.etRegisterPassword.getText().toString();
            register(username,name,password);
        });
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, login.class);
        startActivity(i);


    }

    private void register(String username, String name, String password){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.RegisterResponse(username, name, password);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.isSuccessful() && response.body() != null){
                    Intent i = new Intent(register.this, login.class);
                    startActivity(i);
                    Toast.makeText(register.this, "Akun Berhasil Dibuat", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(register.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(register.this, "Gagal register : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}