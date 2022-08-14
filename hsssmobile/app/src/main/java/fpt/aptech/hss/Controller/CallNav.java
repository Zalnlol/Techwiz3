package fpt.aptech.hss.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ScrollView;

import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.ClassroomListActivity;
import fpt.aptech.hss.Screen.MainActivity;
import fpt.aptech.hss.Screen.StudetntAccountActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CallNav {

    Context context;


    public void call(BottomNavigationView bottom_navigation, int pageactive, Context context){
        this.context = context;
        bottom_navigation.setSelectedItemId(pageactive);
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected( MenuItem item) {

            switch (item.getItemId()) {
                case R.id.page_1:
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    return true;
                case R.id.page_2:
                    Intent intent2 = new Intent(context, ClassroomListActivity.class);
                    context.startActivity(intent2);
                    return true;
                case R.id.page_3:
//                    Intent viewProfile = new Intent(context, MainAccountActivity.class);
//                    context.startActivity(viewProfile);
                    return true;
                case R.id.page_4:
                    Intent viewProfile = new Intent(context, StudetntAccountActivity.class);
                    context.startActivity(viewProfile);
                    return true;

            }
            return false;
        }
    };


    public void setDisplay(ScrollView scrollView, Activity activity, double heights){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //chiều cao
        int height = displayMetrics.heightPixels;
        ViewGroup.LayoutParams params = scrollView.getLayoutParams();
        // Changes the height and width to the specified *pixels*
        double so = height *heights;
        params.height = (int) so;
        scrollView.setLayoutParams(params);
    }
}
