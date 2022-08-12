package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.BaseAdapter.ClassroomListBase;
import fpt.aptech.hss.BaseAdapter.ScheduleListBase;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;

public class ScheduleActivity extends AppCompatActivity {
    String idSelect;
    Spinner spn_selectSem;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_schedule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNav callNav = new CallNav();
        callNav.call(bottom_navigation, R.id.page_2, ScheduleActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, ScheduleActivity.this, 0.8);

        Intent intent = getIntent();

        idSelect  = intent.getStringExtra("idSelect");
        buttonBack();

        spn_selectSem = findViewById(R.id.spn_selectSem);
        listView = findViewById(R.id.Sem_listview);
        
        AddSlect();

        GetData();

    }

    private void GetData() {

//        Classroom_listview

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
            modelString.setData2("Subject" + i);
            data.add(modelString);

            ScheduleListBase baseAdapter = new ScheduleListBase(ScheduleActivity.this,data);
            listView.setAdapter(baseAdapter);
            baseAdapter.notifyDataSetChanged();
        }
    }

    private void AddSlect() {

        List<String> list = new ArrayList<>();
        list.add("Sem 1");
        list.add("Sem 2");
        list.add("Sem 3");
        list.add("Sem 4");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spn_selectSem.setAdapter(adapter);
    }

    private void buttonBack(){
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, ClassroomListActivity.class);
                startActivity(intent);
            }
        });
    }
}