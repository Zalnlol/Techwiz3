package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.MainClassroomBase;
import fpt.aptech.hss.BaseAdapter.MainReourceBase;
import fpt.aptech.hss.BaseAdapter.MainTestBase;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainClassroomBase  mAdapter1;
    private MainTestBase mAdapter;
    private MainReourceBase mAdapter2 ;
    SharedPreferences sharedPreferencesProfile;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        setDisplay();
        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNav callNav = new CallNav();
        callNav.call(bottom_navigation, R.id.page_1, MainActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, MainActivity.this, 0.88);

        TextView username = findViewById(R.id.textView4);

        SharedPreferences sharedPreferencesProfile = getSharedPreferences("profilepref", MODE_PRIVATE);
        String nameKey = sharedPreferencesProfile.getString("nameKey",null);
        username.setText("Hello " + nameKey);

        showClasses();

        AddClassroom();
        AddTest();
        AddResource();
    }

    private void AddTest() {
        List<ModelString> data = new ArrayList<>();

        int d = 0;
        for (int i = 0; i < 4; i++) {

            switch (i) {
                case 0:
                    d = R.drawable.icon0;
                    break;
                case 1:
                    d = R.drawable.icon1;
                    break;
                case 2:
                    d = R.drawable.icon2;
                    break;
                default:
                    d = R.drawable.icon3;
                    break;
            }

            ModelString modelString = new ModelString();
            modelString.setData1(String.valueOf(d));
            modelString.setData2("PHP " + i);
            modelString.setData3("11/08/2022" );
            data.add(modelString);

            RecyclerView recyclerView = findViewById(R.id.recycleviewListTest);
            mAdapter = new MainTestBase(data);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
        }
    }

    private void showClasses(){
        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferencesProfile.getString("user",null);
        DataAPI.api.GetMyClasses(email).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                if (response.body().getData1().equals("Done")) {
                    sharedPreferences = getSharedPreferences("profilepref", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("getClassAPIStatus",response.body().getData1());
                    editor.putString("ID",response.body().getData2());
                    editor.putString("Classname", response.body().getData3());
                    editor.putString("Image", response.body().getData4());
                    editor.commit();
                } else {
                    Toast.makeText(MainActivity.this, response.body().getData1(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddClassroom() {
        List<ModelString> data = new ArrayList<>();
        int d = 0;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    d = R.drawable.icon0;
                    break;
                case 1:
                    d = R.drawable.icon1;
                    break;
                case 2:
                    d = R.drawable.icon2;
                    break;
                default:
                    d = R.drawable.icon3;
                    break;
            }

            ModelString modelString = new ModelString();
            modelString.setData1(String.valueOf(d));
            modelString.setData2("PHP " + i);
            data.add(modelString);
        }


        RecyclerView recyclerView = findViewById(R.id.recycleviewListClass);
        mAdapter1 = new MainClassroomBase(data);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter1);


    }

    private void AddResource() {

        List<ModelString> data = new ArrayList<>();

        int d = 0;
        for (int i = 0; i < 4; i++) {

            switch (i) {
                case 0:
                    d = R.drawable.icon0;
                    break;
                case 1:
                    d = R.drawable.icon1;
                    break;
                case 2:
                    d = R.drawable.icon2;
                    break;
                default:
                    d = R.drawable.icon3;
                    break;
            }

            ModelString modelString = new ModelString();
            modelString.setData1(String.valueOf(d));
            modelString.setData2("PHP Resource " + i + ""+i);
            data.add(modelString);
        }


        RecyclerView recyclerView = findViewById(R.id.recycleviewListResource);
        mAdapter2 = new MainReourceBase(data);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter2);


    }


}