package com.example.tugasday09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tugasday9.R;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;
    TextView etMainUsername, etMainName;
    String usernameMain, nameMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.tugasday9.R.layout.activity_main);

        etMainUsername = findViewById(R.id.etMainUsername);
        etMainName = findViewById(R.id.etMainName);

        sessionManager = new SessionManager(MainActivity.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        usernameMain = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        nameMain = sessionManager.getUserDetail().get(SessionManager.NAME);

        etMainUsername.setText(usernameMain);
        etMainName.setText(nameMain);
    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionLogout){
            sessionManager.logoutSession();
            moveToLogin();
        }
        return super.onOptionsItemSelected(item);
    }
}
