package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.MainClassroomBase;
import fpt.aptech.hss.BaseAdapter.SubjectListBase;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectDetailActivity extends AppCompatActivity {
    String idSelect="";
    String data_="";
    ModelString data;
    TextView Name,Duration,Description;
    ImageView Avatars;
    Button btn_Reource,btn_Exam,btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_teacher_ubject_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_2, SubjectDetailActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, SubjectDetailActivity.this, 0.8);

        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);

        Intent intent = getIntent();
         data_  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("idSelect");
        n.setText(data_);

        Name = findViewById(R.id.Name);
        Duration = findViewById(R.id.Duration);
        Description = findViewById(R.id.Description);
        Avatars = findViewById(R.id.avatar);

        btn_Reource = findViewById(R.id.btn_Reource);
        btn_Exam = findViewById(R.id.btn_Exam);
        btn_add = findViewById(R.id.btn_add);
        btn_Reource();
        btn_Exam();
        btn_add();
        buttonBack();
        getData();

    }

    private void btn_add() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectDetailActivity.this, AddExamActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
            }
        });
    }

    private void btn_Exam() {
        btn_Exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectDetailActivity.this, ExamListActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
                finish();
            }
        });
    }

    private void btn_Reource() {
        btn_Reource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectDetailActivity.this, ResourceTeacherActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
                finish();
            }
        });
    }



    private void getData() {
        DataAPI.api.GetSubjectDetail(idSelect).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                data  = response.body();

                Name.setText(data.getData2());
                Description.setText(data.getData3());
                Duration.setText("Duratiion: " +data.getData4());

                Glide.with(SubjectDetailActivity.this)
                        .load("http://" + ConfigData.IP + ":7777/"+data.getData5())
                        .override(600, 600)
                        .into(Avatars);

            }

            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {

            }
        });
    }


    private void buttonBack() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectDetailActivity.this, SubjectListTecherActivity.class);
                startActivity(intent);
            }
        });
    }


}