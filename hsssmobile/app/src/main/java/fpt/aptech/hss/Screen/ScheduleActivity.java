package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.ClassroomListBase;
import fpt.aptech.hss.BaseAdapter.ScheduleListBase;
import fpt.aptech.hss.BaseAdapter.SemListAdapter;
import fpt.aptech.hss.BaseAdapter.StudentClassAdapter;
import fpt.aptech.hss.BaseAdapter.StudentClassroomAdapter;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity {
    String data;
    Spinner spn_selectSem;
    ListView listView;
    List<ModelString> Semlist;
    RecyclerView recyclerView;


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

        data  = intent.getStringExtra("data");
        buttonBack();

        spn_selectSem = findViewById(R.id.spn_selectSem);
        //listView = findViewById(R.id.Sem_listview);
        recyclerView=findViewById(R.id.Sem_Recycleview);
        AddSlect();
        GetDataSemeter();

        //showCoures();
//        GetData();

    }
    private void showCoures(String name,String sname){
        DataAPI.api.ListSemeterListCourse(name,sname).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                if (response.isSuccessful()){
                    Semlist  = response.body();

                    SemListAdapter adapter = new SemListAdapter(Semlist,ScheduleActivity.this);
                    Context context = getApplicationContext();

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    System.out.println(Semlist);
                }else {
                    Toast.makeText(ScheduleActivity.this,"Faill",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(ScheduleActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });
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

        List<String> list = GetDataSemeter();
        list.add("---Select---");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spn_selectSem.setAdapter(adapter);
        spn_selectSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String items = parent.getItemAtPosition(position).toString();
                if(items.equals("---Select---")){

                }
                else {
                    showCoures(data,items);
                    Toast.makeText(ScheduleActivity.this,data,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        spn_selectSem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                spn_selectSem.getSelectedItem();
//                Toast.makeText(ScheduleActivity.this,spn_selectSem.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
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
    private ArrayList<String> GetDataSemeter(){
        ArrayList<String> list = new ArrayList<>();
        DataAPI.api.ListSemeterCourse(data).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                if (response.isSuccessful()){
                    List<ModelString> stringmodel  = response.body();

                    for (ModelString s:
                            stringmodel) {
                        list.add(s.getData1());
                    }
                    Toast.makeText(ScheduleActivity.this,list.toString(),Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ScheduleActivity.this,"Faill",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(ScheduleActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });
        return list;
    }
}