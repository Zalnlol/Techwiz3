package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.MainClassroomBase;
import fpt.aptech.hss.BaseAdapter.MainReourceBase;
import fpt.aptech.hss.BaseAdapter.MainTeacherReourceBase;
import fpt.aptech.hss.BaseAdapter.MainTeacherTestBase;
import fpt.aptech.hss.BaseAdapter.MainTestBase;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainTecherScreenActivity extends AppCompatActivity {


    private MainClassroomBase  mAdapter1;
    private MainTeacherTestBase mAdapter;
    private MainTeacherReourceBase mAdapter2 ;
    SharedPreferences sharedPreferencesProfile;
    SharedPreferences.Editor editor;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_techer_screen);

        getSupportActionBar().hide();
//        setDisplay();
        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_1, MainTecherScreenActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, MainTecherScreenActivity.this, 0.88);

        TextView username = findViewById(R.id.textView4);

        SharedPreferences sharedPreferencesProfile = getSharedPreferences("profilepref", MODE_PRIVATE);
        String nameKey = sharedPreferencesProfile.getString("nameKey",null);
        username.setText("Hello " + nameKey);




        AddTest();
        AddResource();


    }

    ConfigData configData = new ConfigData();

    private void AddTest() {
        List<ModelString> data = new ArrayList<>();


        String Id = configData.userId(MainTecherScreenActivity.this).toString();



        DataAPI.api.GetTeacherMyTest(Id).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                List<ModelString> list = response.body();



                RecyclerView recyclerView = findViewById(R.id.recycleviewListTest);
                mAdapter = new MainTeacherTestBase(list, MainTecherScreenActivity.this);
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(MainTecherScreenActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });






    }


    private void AddResource() {

        String Id = configData.userId(MainTecherScreenActivity.this).toString();

        DataAPI.api.GetTeacherMyResource(Id).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                List<ModelString> list = response.body();




                RecyclerView recyclerView = findViewById(R.id.recycleviewListResource);
                mAdapter2 = new MainTeacherReourceBase(list,MainTecherScreenActivity.this);
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter2);
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(MainTecherScreenActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });





    }
}