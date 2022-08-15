package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import fpt.aptech.hss.API.NotificationAPI;
import fpt.aptech.hss.BaseAdapter.NotificationAdapter;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherNotificataionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ModelString> accountNotifications;
    SharedPreferences sharedPreferencesProfile;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notificataion);
        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_3, TeacherNotificataionActivity.this);
        recyclerView = findViewById(R.id.rcvnotifiactionteacher);
        GetTokenData();

    }
    private void GetTokenData(){
        sharedPreferencesProfile = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferencesProfile.getString("user",null);
        NotificationAPI.api.GetNotification(email).enqueue(new Callback<List<ModelString>>() {
            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                if (response.isSuccessful()){
                    accountNotifications  = response.body();
                    SetRecycleView();
                    System.out.println(accountNotifications);
                }else {
                    Toast.makeText(TeacherNotificataionActivity.this,"Faill",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
    private  void SetRecycleView() {
        try {
            NotificationAdapter adapter = new NotificationAdapter(accountNotifications, TeacherNotificataionActivity.this);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            System.out.println(adapter.getItemCount());
        } catch (Exception e) {
            System.out.println("faill");
        }
    }
}