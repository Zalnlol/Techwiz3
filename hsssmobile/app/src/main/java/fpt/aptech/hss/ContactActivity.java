package fpt.aptech.hss;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.ContactAdapter;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNavParent;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.Screen.AccountParentActivity;
import fpt.aptech.hss.Screen.DetailContactActivity;
import fpt.aptech.hss.Screen.MainParentActivity;
import fpt.aptech.hss.Screen.ParentAccountActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


//        setDisplay();
        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavParent callNav = new CallNavParent();
        callNav.call(bottom_navigation, R.id.page_4, ContactActivity.this);
        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, ContactActivity.this, 0.8);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_schedule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);

        n.setText("Contact");


        
        Getdata();
        Report();
        buttonBack();
//        Register_Role();
    }




    private void Report() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this, CreateReportActivity.class);

                startActivity(intent);
            }
        });


    }


    private void buttonBack(){
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this, AccountParentActivity.class);
                startActivity(intent);
            }
        });
    }

    ConfigData configData = new ConfigData();
    private void Getdata() {

        String id =configData.userId(this);
        DataAPI.api.GetContact(id).enqueue(new Callback<List<ModelString>>() {
            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                listView = findViewById(R.id.listView);
                List<ModelString> data =response.body();
                ContactAdapter baseAdapter = new ContactAdapter(ContactActivity.this,data,ContactActivity.this);
                listView.setAdapter(baseAdapter);
                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(ContactActivity.this,"Connect error",Toast.LENGTH_SHORT).show();
            }
        });
    }


}