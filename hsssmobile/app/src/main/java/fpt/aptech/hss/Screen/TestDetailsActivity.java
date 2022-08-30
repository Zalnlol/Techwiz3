package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.StudentTestAdapter;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestDetailsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferencesProfile;
    ModelString Mark;
    String idSelect,idSelect1,data;
    //RecyclerView recyclerTest;
    TextView tv1,tv2,tv3,tv4;
    ImageView ivAvatarProfile;
    int s;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_details);
        Intent intent = getIntent();
        idSelect  = intent.getStringExtra("idSelect");
        idSelect1  = intent.getStringExtra("idSelect1");
        data  = intent.getStringExtra("data");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_my_clasroome);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNav callNav = new CallNav();
        callNav.call(bottom_navigation, R.id.page_2, TestDetailsActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, TestDetailsActivity.this, 0.8);
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText("Information");
        buttonBack();

        ivAvatarProfile = findViewById(R.id.ivAvatarProfile);
        //s=50;
        showTest();
        Toast.makeText(TestDetailsActivity.this, "Loading", Toast.LENGTH_SHORT).show();
        //Toast.makeText(TestDetailsActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    private void buttonBack() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestDetailsActivity.this, StudentTestActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("id", idSelect1);
                startActivity(intent);
            }
        });
    }

    private void action(){
        showTest();
        drawChart();
    }
    private void showTest() {
        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferencesProfile.getString("user", null);
        //ModelString Mark = new ModelString();
        DataAPI.api.MarkByExam(idSelect,email).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                Mark = response.body();
//                Toast.makeText(TestDetailsActivity.this, ""+response.body().getData1(), Toast.LENGTH_SHORT).show();
                tv1=findViewById(R.id.tvtest_name);
                tv2=findViewById(R.id.tvtest_startdate);
                tv3=findViewById(R.id.tvtest_mark);
                tv4=findViewById(R.id.tvtest_remark);
                if(Mark!=null){
                    tv1.setText(Mark.getData1());
                    tv2.setText(Mark.getData2());
                    tv3.setText(Mark.getData3()+"/10");
                    tv4.setText(Mark.getData4());
                    s=Integer.valueOf(Mark.getData3());
                }




//                Glide.with(TestDetailsActivity.this)
//                        .load("http://" + ConfigData.IP + ":7777/"+Mark.getData5())
//                        .override(600, 600)x
//                        .into(ivAvatarProfile);




                s=s*10;
                drawChart();
                //Toast.makeText(TestDetailsActivity.this, Mark.toString(), Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {
                Toast.makeText(TestDetailsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(TestDetailsActivity.this, email, Toast.LENGTH_SHORT).show();
                Toast.makeText(TestDetailsActivity.this, idSelect, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void drawChart() {
        pieChart = findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);

        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        yvalues.add(new PieEntry(s, "Your Score", 0));
        yvalues.add(new PieEntry((100-s), "Remaining", 1));
        PieDataSet dataSet = new PieDataSet(yvalues, getString(R.string.election_results));
        PieData data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        Description description = new Description();
        description.setText(getString(R.string.pie_chart));
        pieChart.setDescription(description);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(58f);
        pieChart.setHoleRadius(58f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);

    }
}