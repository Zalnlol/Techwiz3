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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.MainClassroomBase;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyclasroomDetail extends AppCompatActivity {
    String idSelect;
    String data_;
    Button btn_Schedule,btn_Student,btn_Test;
    TextView participantsNum;
    TextView subjectNum;
    TextView duration;
    ImageView classImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myclasroom_detail);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_my_clasroome);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNav callNav = new CallNav();
        callNav.call(bottom_navigation, R.id.page_2, MyclasroomDetail.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, MyclasroomDetail.this, 0.8);

        Intent intent = getIntent();
        data_  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("idSelect");
        showClassDetails();
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText(data_);

        btn_Schedule = findViewById(R.id.btn_Schedule);
        btn_Student = findViewById(R.id.btn_Student);
        btn_Test=findViewById(R.id.btn_StudentTest);
        buttonBack();
        btn_Schedule();
        btn_Student();
        btn_Test();

    }

    private void showClassDetails(){

        DataAPI.api.GetMyClassesDetails(idSelect).enqueue(new Callback<ModelString>() {

            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                ModelString modelString = response.body();

                participantsNum = findViewById(R.id.text1);
                participantsNum.setText(modelString.getData1());
                subjectNum = findViewById(R.id.text2);
                subjectNum.setText(modelString.getData2());
                duration = findViewById(R.id.text3);
                duration.setText(modelString.getData3());
            }

            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {
                Toast.makeText(MyclasroomDetail.this, "Connect error, unable to find class!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void btn_Schedule() {
        btn_Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyclasroomDetail.this, ScheduleActivity.class);

                intent.putExtra("data", data_);
                intent.putExtra("id", data_);
                startActivity(intent);
            }
        });

    }
    private void btn_Test() {
        btn_Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyclasroomDetail.this, StudentTestActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("id", idSelect);
                startActivity(intent);
            }
        });

    }
    private void btn_Student(){
        btn_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyclasroomDetail.this, StudentClassActivity.class);
                intent.putExtra("data", data_);
                startActivity(intent);
            }
        });

    }


    private void buttonBack(){
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyclasroomDetail.this, ClassroomListActivity.class);
                startActivity(intent);
            }
        });
    }
}