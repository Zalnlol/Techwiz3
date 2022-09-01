package fpt.aptech.hss;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNavParent;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.Screen.AccountParentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavParent callNav = new CallNavParent();
        callNav.call(bottom_navigation, R.id.page_4, CreateReportActivity.this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_schedule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText("Create");
        Register_Role();
        buttonBack();

        Checkvalue();

    }
    ConfigData configData = new ConfigData();
    private void Checkvalue() {
        String id =configData.userId(this);
        Button   button2  = findViewById(R.id.button2);
        Spinner spinner = findViewById(R.id.style);
        EditText Name,  Content;
        Name = findViewById(R.id.name);
        Content = findViewById(R.id.content);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Name.getText().toString();
                String content1 = Content.getText().toString();
                String spinner1 = spinner.getSelectedItem().toString();
                boolean check = true;

                if (name.equals("") || name ==null){
                    check=false;
                    Toast.makeText(CreateReportActivity.this, "Name not null", Toast.LENGTH_SHORT).show();
                }

                if (content1.equals("") || content1 ==null){
                    check=false;
                    Toast.makeText(CreateReportActivity.this, "Content not null", Toast.LENGTH_SHORT).show();
                }

                int count=0;

                switch (spinner1){
                    case "App Error":
                        count= 1;
                        break;

                    case "Error data":
                        count= 2;
                        break;

                    case "Orther":
                        count= 3;
                        break;

                }

                if(check==true){
                    DataAPI.api.CreateContact(name,content1,String.valueOf(count),id).enqueue(new Callback<ModelString>() {
                        @Override
                        public void onResponse(Call<ModelString> call, Response<ModelString> response) {

                        }

                        @Override
                        public void onFailure(Call<ModelString> call, Throwable t) {
                            Toast.makeText(CreateReportActivity.this,"Connect error",Toast.LENGTH_SHORT).show();
                        }
                    });


                }
                Intent intent = new Intent(CreateReportActivity.this, ContactActivity.class);

                startActivity(intent);



            }
        });


    }


    private void buttonBack(){
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateReportActivity.this, ContactActivity.class);

                startActivity(intent);
            }
        });
    }


    private  void Register_Role(){
        Spinner spnRole = findViewById(R.id.style);
        List<String> list = new ArrayList<>();
        list.add("Error App");
        list.add("Error data");
        list.add("Orther");


        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spnRole.setAdapter(adapter);

    }
}