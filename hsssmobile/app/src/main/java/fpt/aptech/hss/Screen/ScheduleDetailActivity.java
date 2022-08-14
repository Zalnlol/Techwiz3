package fpt.aptech.hss.Screen;
import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScheduleDetailActivity extends AppCompatActivity {

    TextView tv1,tv2;
    ImageView imageView;
    ModelString modelString;
    String idSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_my_clasroome);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNav callNav = new CallNav();
        callNav.call(bottom_navigation, R.id.page_2, ScheduleDetailActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, ScheduleDetailActivity.this, 0.8);

        Intent intent = getIntent();
        String data_  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("idSelect");
        Toast.makeText(ScheduleDetailActivity.this,idSelect,Toast.LENGTH_SHORT).show();
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText(data_);
        showTest();

        buttonBack();
    }
    private void showTest() {
        DataAPI.api.DoccumentById(idSelect).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                modelString = response.body();
                tv1=findViewById(R.id.tv_tech_name);
                tv2=findViewById(R.id.tv_teach_resource);
                imageView=findViewById(R.id.iv_tech_Avatar);
                tv1.setText(modelString.getData2());
                tv2.setText(modelString.getData3());
                Glide.with(ScheduleDetailActivity.this)
                        .load("http://" + ConfigData.IP + ":7777/"+modelString.getData1())
//                .transform(new RoundedCorners(radius))
//                .transform(new CircleCrop())
                        .override(600, 600)
//                .error(R.drawable.icon5)
                        .into(imageView);

//        tv3.setText(Mark.getData3()+"/10");
//        tv4.setText(Mark.getData4());
            }

            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {
                Toast.makeText(ScheduleDetailActivity.this, "Faill", Toast.LENGTH_SHORT).show();
            }
        });
//        Toast.makeText(TestDetailsActivity.this, idSelect, Toast.LENGTH_SHORT).show();

    }
    private void buttonBack() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleDetailActivity.this, ClassroomListActivity.class);
                startActivity(intent);
            }
        });
    }
}