package fpt.aptech.hss.Screen;

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

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    ModelString modelString;
    String idSelect, idSelect1,data_;
    TextView tv1,tv2,tv3;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_schedule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNav callNav = new CallNav();
        callNav.call(bottom_navigation, R.id.page_2, AccountActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, AccountActivity.this, 0.8);

        Intent intent = getIntent();

        data_  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("id");
        idSelect1  = intent.getStringExtra("idSelect1");

        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        n.setText("Account");
        buttonBack();
        showTest();
    }

    private void buttonBack() {

        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, StudentClassActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("id", idSelect1);
                startActivity(intent);
            }
        });


    }

    private void showTest() {
//        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
//        String email = sharedPreferencesProfile.getString("user", null);
        //ModelString Mark = new ModelString();
        DataAPI.api.StudentGetAccount(idSelect).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                modelString = response.body();
                tv1=findViewById(R.id.tv_ac_name);
                tv2=findViewById(R.id.tv_ac_dbo);
                tv3=findViewById(R.id.tv_ac_mail);
                imageView=findViewById(R.id.iv_ac_avata);
                tv1.setText(modelString.getData2());
                tv2.setText(modelString.getData3());
                Glide.with(AccountActivity.this)
                        .load("http://" + ConfigData.IP + ":8080/KSS/"+modelString.getData1())
//                .transform(new RoundedCorners(radius))
//                .transform(new CircleCrop())
                        .override(600, 600)
//                .error(R.drawable.icon5)
                        .into(imageView);
                //Toast.makeText(TestDetailsActivity.this, Mark.toString(), Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {
                Toast.makeText(AccountActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}