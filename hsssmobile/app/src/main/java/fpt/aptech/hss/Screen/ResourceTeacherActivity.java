package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.EditResourceActivity;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourceTeacherActivity extends AppCompatActivity {

    String idSelect="";
    String data_="";
    ModelString data;
    TextView name;
    Button btn_edit_reource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_teacher);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_teacher_ubject_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_2, ResourceTeacherActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, ResourceTeacherActivity.this, 0.8);

        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);

        Intent intent = getIntent();
         data_  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("idSelect");
        n.setText(data_);
        name = findViewById(R.id.resouce);
        btn_edit_reource = findViewById(R.id.btn_edit_reource);

        getData();
        btn_edit_reource();
        buttonBack();
    }

    private void btn_edit_reource(){
        btn_edit_reource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResourceTeacherActivity.this, EditResourceActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getData() {
        DataAPI.api.GetReouceDetail(idSelect).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                data  = response.body();

                name.setText(data.getData2());


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
                Intent intent = new Intent(ResourceTeacherActivity.this, SubjectDetailActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
            }
        });
    }
}