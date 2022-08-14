package fpt.aptech.hss.Screen;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.ClassroomListBase;
import fpt.aptech.hss.BaseAdapter.MainTeacherReourceBase;
import fpt.aptech.hss.BaseAdapter.SubjectListBase;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectListTecherActivity extends AppCompatActivity {
    ClassroomListBase classroomListBase;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list_teacher);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_list_classroom);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_2, SubjectListTecherActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, SubjectListTecherActivity.this, 0.8);

        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText("My Subject");


        Adddata();
    }
    ConfigData configData = new ConfigData();
    private void Adddata() {
        listView = findViewById(R.id.Classroom_listview);
//        Classroom_listview

        List<ModelString> data = new ArrayList<>();


        String Id = configData.userId(SubjectListTecherActivity.this).toString();

        DataAPI.api.GetSubject(Id).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                List<ModelString> list = response.body();


                SubjectListBase baseAdapter = new SubjectListBase(SubjectListTecherActivity.this,list);
                listView.setAdapter(baseAdapter);
                baseAdapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(SubjectListTecherActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });



        }






}