package fpt.aptech.hss;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.Screen.ResourceTeacherActivity;
import fpt.aptech.hss.Screen.SubjectDetailActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditResourceActivity extends AppCompatActivity {
    String idSelect="";
    String data_="";
    ModelString data;
    EditText textResource;
    Button btn_save_reource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resource);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_teacher_ubject_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_2, EditResourceActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, EditResourceActivity.this, 0.8);

        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);

        Intent intent = getIntent();
        data_  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("idSelect");
        n.setText(data_);
        textResource = findViewById(R.id.textResource);
        btn_save_reource = findViewById(R.id.btn_save_reource);
        btn_save_reource();
        getData();
        buttonBack();
    }

    private void btn_save_reource(){
        btn_save_reource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String data11 = textResource.getText().toString();

                DataAPI.api.PostReouceEdit(idSelect,data11).enqueue(new Callback<ModelString>() {
                    @Override
                    public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                        Intent intent = new Intent(EditResourceActivity.this, ResourceTeacherActivity.class);
                        intent.putExtra("data", data_);
                        intent.putExtra("idSelect", idSelect);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ModelString> call, Throwable t) {

                    }
                });

            }
        });

    }

    private void getData() {
        DataAPI.api.GetReouceDetail(idSelect).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                data  = response.body();
                textResource.setText(data.getData2());
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
                Intent intent = new Intent(EditResourceActivity.this, ResourceTeacherActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
            }
        });
    }
}