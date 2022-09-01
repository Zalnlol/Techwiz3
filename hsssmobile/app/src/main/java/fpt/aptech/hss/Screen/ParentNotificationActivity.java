package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import fpt.aptech.hss.API.NotificationAPI;
import fpt.aptech.hss.BaseAdapter.NotificationAdapter;
import fpt.aptech.hss.Controller.CallNav;
import fpt.aptech.hss.Controller.CallNavParent;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentNotificationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ModelString> accountNotifications;
    SharedPreferences sharedPreferencesProfile;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_notification);
        getSupportActionBar().hide();
        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavParent callNav = new CallNavParent();
        callNav.call(bottom_navigation, R.id.page_3, ParentNotificationActivity.this);
        recyclerView = findViewById(R.id.rcvnotifiactionparent);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_schedule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));
        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        ScrollView scrollView = findViewById(R.id.scrollView);

        callNav.setDisplay(scrollView, ParentNotificationActivity.this,0.88);
        n.setText("Notification");

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
                    Toast.makeText(ParentNotificationActivity.this,"Faill",Toast.LENGTH_SHORT).show();
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
            NotificationAdapter adapter = new NotificationAdapter(accountNotifications, ParentNotificationActivity.this);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            System.out.println(adapter.getItemCount());
        } catch (Exception e) {
            System.out.println("faill");
        }
    }
}