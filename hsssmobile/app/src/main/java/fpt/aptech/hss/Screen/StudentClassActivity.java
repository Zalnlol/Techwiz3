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
import fpt.aptech.hss.BaseAdapter.StudentClassroomAdapter;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentClassActivity extends AppCompatActivity {
    SharedPreferences sharedPreferencesProfile;
    List<ModelString> StudentList;
    RecyclerView recyclerStudent;
    String data_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_student_class);
        Intent intent = getIntent();
        data_  = intent.getStringExtra("data");
        recyclerStudent=findViewById(R.id.StidentClass_listview);
        showClass();
    }
    private void showClass(){
        DataAPI.api.ListStudenByClass(data_).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                if (response.isSuccessful()){
                    StudentList  = response.body();

                    StudentClassAdapter adapter = new StudentClassAdapter(StudentList,StudentClassActivity.this);
                    Context context = getApplicationContext();

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerStudent.setLayoutManager(mLayoutManager);
                    recyclerStudent.setItemAnimator(new DefaultItemAnimator());
                    recyclerStudent.setAdapter(adapter);
                    System.out.println(StudentList);
                }else {
                    Toast.makeText(StudentClassActivity.this,"Faill",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(StudentClassActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}