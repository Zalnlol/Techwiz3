package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fpt.aptech.hss.R;

public class SuccessActivity extends AppCompatActivity {
    Button buttonBackLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        getSupportActionBar().hide();
        buttonBackLogin= findViewById(R.id.buttonBackLogin);
        buttonBackLogin();
    }

    private void buttonBackLogin() {

        buttonBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SuccessActivity.this, LoginActivity.class));

            }
        });
    }
}