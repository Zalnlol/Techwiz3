package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;

public class RegisterActivity extends AppCompatActivity {

    Spinner spnRole;
    Button btn_Register;
    List<ModelString> modelStringList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        spnRole = (Spinner) findViewById(R.id.Register_Role);
        btn_Register = findViewById(R.id.btn_Register);
        Register_Role();
        btn_Register();
    }

    private void btn_Register() {
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, QRActivity.class));
            }
        });
    }






    private  void Register_Role(){
        List<String> list = new ArrayList<>();
        list.add("User");
        list.add("Teacher");
        list.add("Parent");


        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spnRole.setAdapter(adapter);

    }
}