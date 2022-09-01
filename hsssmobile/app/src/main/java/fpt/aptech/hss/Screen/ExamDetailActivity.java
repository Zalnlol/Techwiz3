package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.ExamListBase;
import fpt.aptech.hss.BaseAdapter.MarkList1Base;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamDetailActivity extends AppCompatActivity {
    String idSelect="";
    String data_="", idExam_="";
    ImageView avatar;
    TextView Name,Classroom,Subject,Startday;
    Button editmark;
    List<ModelString> modelStringList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_detail);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_teacher_ubject_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_2, ExamDetailActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, ExamDetailActivity.this, 0.8);

        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);

        Name = findViewById(R.id.Name);
        avatar = findViewById(R.id.avatar);
        Classroom = findViewById(R.id.Classroom);
        Subject = findViewById(R.id.Subject);
        Startday = findViewById(R.id.Startday);

        Intent intent = getIntent();
        data_  = intent.getStringExtra("data");
        idExam_  = intent.getStringExtra("idExam");
        idSelect  = intent.getStringExtra("idSelect");
        n.setText(data_);

        editmark = findViewById(R.id.editmark);

        editmark();
        AddData();
        buttonBack();
        GetData();
    }

    private void editmark() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        editmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamDetailActivity.this,AddMarkActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idExam_", idExam_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
            }
        });
    }

    private void GetData() {
        listView = findViewById(R.id.Mark_listview);



        DataAPI.api.MarkList(idExam_,idSelect).enqueue(new Callback<List<ModelString>>() {
            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                modelStringList = response.body();

//                Toast.makeText(ExamDetailActivity.this, ""+modelStringList.size(), Toast.LENGTH_SHORT).show();


                MarkList1Base baseAdapter = new MarkList1Base(ExamDetailActivity.this,modelStringList);

                listView.setAdapter(baseAdapter);
                baseAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {

            }
        });

    }

    private void AddData() {

        DataAPI.api.ExemDetail(idExam_).enqueue(new Callback<ModelString>() {

            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {

                ModelString modelString = response.body();


                Name.setText(modelString.getData2());
                Glide.with(ExamDetailActivity.this)
                        .load("http://" + ConfigData.IP + ":7777/"+modelString.getData3())
                        .override(600, 600)

                        .into(avatar);
                Classroom.setText("Classroom: "+ modelString.getData4());
                Subject.setText("Subject: "+modelString.getData5());
                Startday.setText("Start Day: "+modelString.getData6());





            }

            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {
                Toast.makeText(ExamDetailActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void buttonBack() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamDetailActivity.this, ExamListActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
            }
        });
    }


}