package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.StudentClassAdapter;
import fpt.aptech.hss.BaseAdapter.StudentTestAdapter;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentTestActivity extends AppCompatActivity {
    SharedPreferences sharedPreferencesProfile;
    List<ModelString> TestList;
    RecyclerView recyclerTest;
    String data,idSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_test);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_my_clasroome);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNav callNav = new CallNav();
        callNav.call(bottom_navigation, R.id.page_2, StudentTestActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, StudentTestActivity.this, 0.8);
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText("My Test");
        buttonBack();

        recyclerTest=findViewById(R.id.StudentTest_listview);
        Intent intent = getIntent();
        data  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("id");

        showTest();
    }

    private void buttonBack() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentTestActivity.this, MyclasroomDetail.class);
                intent.putExtra("data", data);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
            }
        });
    }

    private void showTest() {
        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferencesProfile.getString("user", null);
        DataAPI.api.StudentExamClass(email,data).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                if (response.isSuccessful()) {
                    TestList = response.body();

                    StudentTestAdapter adapter = new StudentTestAdapter(TestList, StudentTestActivity.this,data,idSelect );
                    Context context = getApplicationContext();

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerTest.setLayoutManager(mLayoutManager);
                    recyclerTest.setItemAnimator(new DefaultItemAnimator());
                    recyclerTest.setAdapter(adapter);
                    System.out.println(TestList);
                } else {
                    Toast.makeText(StudentTestActivity.this, "Faill", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(StudentTestActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}