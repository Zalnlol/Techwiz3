package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    ModelString modelString;
    String idSelect;
    TextView tv1,tv2,tv3;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        idSelect  = intent.getStringExtra("idSelect");
        showTest();
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
                        .load("http://" + ConfigData.IP + ":7777/"+modelString.getData1())
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