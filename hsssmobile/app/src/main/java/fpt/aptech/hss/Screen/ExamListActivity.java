package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.ExamListBase;
import fpt.aptech.hss.BaseAdapter.SubjectListBase;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamListActivity extends AppCompatActivity {
    String idSelect="";
    String data_="";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_teacher_ubject_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_2, ExamListActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, ExamListActivity.this, 0.8);

        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        Intent intent = getIntent();
        data_  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("idSelect");
        n.setText(data_);
        n.setText("Exam "+data_);
        buttonBack();
        Adddata();
    }

    private void buttonBack() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamListActivity.this, SubjectDetailActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
            }
        });
    }
    private void Adddata() {
        listView = findViewById(R.id.Classroom_listview);

        DataAPI.api.GetExem(idSelect).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                List<ModelString> list = response.body();


                ExamListBase baseAdapter = new ExamListBase(ExamListActivity.this,list, data_,idSelect);
                listView.setAdapter(baseAdapter);
                baseAdapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(ExamListActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}