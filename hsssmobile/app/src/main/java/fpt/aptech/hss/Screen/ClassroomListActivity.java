package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.ClassroomListBase;
import fpt.aptech.hss.BaseAdapter.MainClassroomBase;
import fpt.aptech.hss.BaseAdapter.MainReourceBase;
import fpt.aptech.hss.BaseAdapter.MainTestBase;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassroomListActivity extends AppCompatActivity {
    private MainClassroomBase  mAdapter1;
    private MainTestBase mAdapter;
    private MainReourceBase mAdapter2 ;

    Context context;

    ClassroomListBase classroomListBase;
    ListView listView;
    SharedPreferences sharedPreferencesProfile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_list);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_list_classroom);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNav callNav = new CallNav();
        callNav.call(bottom_navigation, R.id.page_2, ClassroomListActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, ClassroomListActivity.this, 0.8);

        Adddata();
    }


    private void Adddata() {
        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferencesProfile.getString("user",null);
        DataAPI.api.GetMyClasses(email).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                List<ModelString> list = response.body();
                RecyclerView recyclerView = findViewById(R.id.recycleviewListClass);
                mAdapter1 = new MainClassroomBase(list,ClassroomListActivity.this);
                context = getApplicationContext();

                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter1);


                listView = findViewById(R.id.Classroom_listview);

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
                    modelString.setData2("T12008A" + i);
                    data.add(modelString);

                    ClassroomListBase baseAdapter = new ClassroomListBase(ClassroomListActivity.this,data);
                    listView.setAdapter(baseAdapter);
                    baseAdapter.notifyDataSetChanged();
                }

                Toast.makeText(ClassroomListActivity.this, "has data!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(ClassroomListActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });



    }
}