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

import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.API.NotificationAPI;
import fpt.aptech.hss.API.ParentAPI;
import fpt.aptech.hss.BaseAdapter.ChildrenClassAdapter;
import fpt.aptech.hss.BaseAdapter.ChildrenTestAdapter;
import fpt.aptech.hss.BaseAdapter.MainClassroomBase;
import fpt.aptech.hss.BaseAdapter.MarkTableAdapter;
import fpt.aptech.hss.BaseAdapter.NotificationAdapter;
import fpt.aptech.hss.BaseAdapter.ParentClassAdapter;
import fpt.aptech.hss.BaseAdapter.ParentTestAdapter;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Controller.CallNavParent;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainParentActivity extends AppCompatActivity {
    RecyclerView recyclerView,recyclerClass,recyclerTest;
    List<ModelString> marklist;
    List<ModelString> Classlist,ExamList;
    SharedPreferences sharedPreferencesProfile;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);
        getSupportActionBar().hide();
//        setDisplay();
        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavParent callNav = new CallNavParent();
        callNav.call(bottom_navigation, R.id.page_1, MainParentActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, MainParentActivity.this, 0.88);
        recyclerView = findViewById(R.id.recycleviewChildrenMark);
        recyclerClass = findViewById(R.id.recycleviewChildrenClass);
        recyclerTest = findViewById(R.id.recycleviewChildrenTest);
        textView=findViewById(R.id.tv_hello_pr);
        showClasses();
        GetTokenData();
        showExam();
    }

    private void GetTokenData(){
        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferencesProfile.getString("user",null);
        ParentAPI.api.APIParentGetMark(email).enqueue(new Callback<List<ModelString>>() {
            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                if (response.isSuccessful()){
                    marklist  = response.body();
                    textView.setText(marklist.get(1).getData5());
                    SetRecycleView();
                    System.out.println(marklist);
                }else {
                    Toast.makeText(MainParentActivity.this,"Faill",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
    private void showClasses(){
        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferencesProfile.getString("user",null);
        ParentAPI.api.ParentGetClass(email).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                if (response.isSuccessful()){
                    Classlist  = response.body();
                    ParentClassAdapter adapter = new ParentClassAdapter(Classlist,MainParentActivity.this);
                    Context context = getApplicationContext();

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerClass.setLayoutManager(mLayoutManager);
                    recyclerClass.setItemAnimator(new DefaultItemAnimator());
                    recyclerClass.setAdapter(adapter);
                }else {
                    Toast.makeText(MainParentActivity.this,"Faill",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(MainParentActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showExam(){
        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferencesProfile.getString("user",null);
        ParentAPI.api.ParentGetExam(email).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                if (response.isSuccessful()){
                    ExamList  = response.body();
                    ParentTestAdapter adapter = new ParentTestAdapter(ExamList,MainParentActivity.this);
                    Context context = getApplicationContext();

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerTest.setLayoutManager(mLayoutManager);
                    recyclerTest.setItemAnimator(new DefaultItemAnimator());
                    recyclerTest.setAdapter(adapter);
                }else {
                    Toast.makeText(MainParentActivity.this,"Faill",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(MainParentActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  void SetRecycleView(){
        try {
            MarkTableAdapter adapter = new MarkTableAdapter(marklist,MainParentActivity.this);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            System.out.println(adapter.getItemCount());
        }catch (Exception e){
            System.out.println("faill");
        }


    }
}