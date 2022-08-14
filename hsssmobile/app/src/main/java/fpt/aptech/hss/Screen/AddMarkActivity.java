package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.ExamListBase;
import fpt.aptech.hss.BaseAdapter.MarkListBase;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMarkActivity extends AppCompatActivity {
    String idSelect="";
    String data_="", idExam_="";
    List<ModelString> modelStringList;
    ListView listView;
    Button btn_save;
    MarkListBase baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_schedule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_2, AddMarkActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, AddMarkActivity.this, 0.8);

         baseAdapter = new MarkListBase();
        baseAdapter.context= AddMarkActivity.this;
        Intent intent = getIntent();

        data_  = intent.getStringExtra("data");
        idExam_  = intent.getStringExtra("idExam_");
        idSelect  = intent.getStringExtra("idSelect");
        btn_save = findViewById(R.id.btn_save);
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText("Mark");
        GetData();
        btn_save();
        buttonBack();
    }

    private void btn_save() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                baseAdapter.Addata(idExam_,idSelect,data_);
            }
        });

    }

    private void GetData() {
        listView = findViewById(R.id.Sem_listview);



        DataAPI.api.MarkCreate(idExam_,idSelect).enqueue(new Callback<List<ModelString>>() {
            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                modelStringList = response.body();


                baseAdapter.list=modelStringList;

                listView.setAdapter(baseAdapter);
                baseAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {

            }
        });

    }

    private void buttonBack() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddMarkActivity.this, ExamDetailActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idExam", idExam_);
                intent.putExtra("idSelect", idSelect);
              startActivity(intent);
            }
        });
    }
}