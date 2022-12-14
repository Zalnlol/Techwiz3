package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.API.ParentAPI;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Controller.CallNavParent;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestDetailsParentActivity extends AppCompatActivity {
    SharedPreferences sharedPreferencesProfile;
    ModelString Mark;
    String idSelect;
    //RecyclerView recyclerTest;
    TextView tv1,tv2,tv3,tv4;
    int s;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_details_parent);

        Intent intent = getIntent();
        idSelect  = intent.getStringExtra("idSelect");
       String data_  = intent.getStringExtra("data");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_teacher_addexem);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavParent callNav = new CallNavParent();
        callNav.call(bottom_navigation, R.id.page_2, TestDetailsParentActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, TestDetailsParentActivity.this, 0.8);


        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText(data_);
        buttonBack();


        //s=50;
        showTest();
    }

    private void buttonBack() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestDetailsParentActivity.this, MainParentActivity.class);
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
        ParentAPI.api.MarkByExam(idSelect,email).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                Mark = response.body();
                tv1=findViewById(R.id.tvtest_name);
                tv2=findViewById(R.id.tvtest_startdate);
                tv3=findViewById(R.id.tvtest_mark);
                tv4=findViewById(R.id.tvtest_remark);
                tv1.setText(Mark.getData1());
                tv2.setText(Mark.getData2());
                tv3.setText(Mark.getData3()+"/10");
                tv4.setText(Mark.getData4());
                s=Integer.valueOf(Mark.getData3());
                s=s*10;
                drawChart();
                //Toast.makeText(TestDetailsActivity.this, Mark.toString(), Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {
                Toast.makeText(TestDetailsParentActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(TestDetailsParentActivity.this, email, Toast.LENGTH_SHORT).show();
                Toast.makeText(TestDetailsParentActivity.this, idSelect, Toast.LENGTH_SHORT).show();
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