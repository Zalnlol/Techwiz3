package fpt.aptech.hss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Controller.CallNavParent;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Screen.AccountParentActivity;
import fpt.aptech.hss.Screen.LoginActivity;
import fpt.aptech.hss.Screen.StudetntAccountActivity;

public class TeacherAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_account);

        getSupportActionBar().hide();


        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_4, TeacherAccountActivity.this);
        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, TeacherAccountActivity.this, 0.88);


        SharedPreferences sharedPreferencesProfile = getSharedPreferences("profilepref", MODE_PRIVATE);
        String nameKey = sharedPreferencesProfile.getString("nameKey",null);
        String mailKey = sharedPreferencesProfile.getString("mailKey",null);
        String birthKey = sharedPreferencesProfile.getString("birthKey",null);
        String avatarKey = sharedPreferencesProfile.getString("avatarKey",null);

        ImageView imageView = findViewById(R.id.iv_ac_avata);
        TextView tv_ac_name = findViewById(R.id.tv_ac_name);
        TextView tv_ac_dbo = findViewById(R.id.tv_ac_dbo);
        TextView tv_ac_mail = findViewById(R.id.tv_ac_mail);
        tv_ac_name.setText(nameKey);
        tv_ac_dbo.setText("Birthday: "+birthKey);
        tv_ac_mail.setText("Mail: "+mailKey);

        Glide.with(TeacherAccountActivity.this)
                .load("http://" + ConfigData.IP + ":8080/KSS/"+avatarKey)
//                .transform(new RoundedCorners(radius))
//                .transform(new CircleCrop())
                .override(600, 600)
//                .error(R.drawable.icon5)
                .into(imageView);

        btn_Logout();
    }

    private void btn_Logout() {
        Button btn_Logout = findViewById(R.id.btn_Logout);
        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences myPrefs = getSharedPreferences("profilepref",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.apply();
                finish();
                Intent intent = new Intent(TeacherAccountActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}