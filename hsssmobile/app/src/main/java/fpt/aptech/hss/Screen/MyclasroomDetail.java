package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.R;

public class MyclasroomDetail extends AppCompatActivity {
    String idSelect="";
    Button btn_Schedule;

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
        String data_  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("idSelect");
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText(data_);

        btn_Schedule = findViewById(R.id.btn_Schedule);

        buttonBack();
        btn_Schedule();


    }

    private void btn_Schedule() {
        btn_Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyclasroomDetail.this, ScheduleActivity.class);
                intent.putExtra("idSelect", idSelect);
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