package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.ContactActivity;
import fpt.aptech.hss.Controller.CallNavParent;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailContactActivity extends AppCompatActivity {
    String data,idSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        Intent intent = getIntent();

        data  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("idSelect");

//        Toast.makeText(this, ""+idSelect, Toast.LENGTH_SHORT).show();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_teacher_addexem);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);

        n.setText(data);

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavParent callNav = new CallNavParent();
        callNav.call(bottom_navigation, R.id.page_4, DetailContactActivity.this);
        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, DetailContactActivity.this,0.8);

        buttonBack();
        Getdata();

    }

    private void Getdata() {
        TextView Name,Style,Content;
        Name = findViewById(R.id.Name);
        Style = findViewById(R.id.Style);
        Content = findViewById(R.id.Content);



        DataAPI.api.GetContactDetail(idSelect).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                ModelString modelString = response.body();
                Name.setText(modelString.getData2());
                Style.setText(modelString.getData3());
                Content.setText(modelString.getData4());
            }

            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {
                Toast.makeText(DetailContactActivity.this,"Connect error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void buttonBack(){
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailContactActivity.this, ContactActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
            }
        });
    }
}