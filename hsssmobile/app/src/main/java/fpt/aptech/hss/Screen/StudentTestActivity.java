package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.StudentClassAdapter;
import fpt.aptech.hss.BaseAdapter.StudentTestAdapter;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentTestActivity extends AppCompatActivity {
    SharedPreferences sharedPreferencesProfile;
    List<ModelString> TestList;
    RecyclerView recyclerTest;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_student_test);
        recyclerTest=findViewById(R.id.StudentTest_listview);
        Intent intent = getIntent();
        data  = intent.getStringExtra("data");
        showTest();
    }
    private void showTest() {
        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferencesProfile.getString("user", null);
        DataAPI.api.StudentExamClass(email,data).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                if (response.isSuccessful()) {
                    TestList = response.body();

                    StudentTestAdapter adapter = new StudentTestAdapter(TestList, StudentTestActivity.this);
                    Context context = getApplicationContext();

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerTest.setLayoutManager(mLayoutManager);
                    recyclerTest.setItemAnimator(new DefaultItemAnimator());
                    recyclerTest.setAdapter(adapter);
                    System.out.println(TestList);
                } else {
                    Toast.makeText(StudentTestActivity.this, "Faill", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(StudentTestActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}