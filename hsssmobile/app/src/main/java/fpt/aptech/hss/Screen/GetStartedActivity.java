package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fpt.aptech.hss.R;

public class GetStartedActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        getSupportActionBar().hide();
        btnLogin();
        btnRegister();
    }

    private void btnLogin() {
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(GetStartedActivity.this, LoginActivity.class));

            }
        });

    }

    private void btnRegister() {
        Button btnLogin = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(GetStartedActivity.this, RegisterActivity.class));

            }
        });

    }




}