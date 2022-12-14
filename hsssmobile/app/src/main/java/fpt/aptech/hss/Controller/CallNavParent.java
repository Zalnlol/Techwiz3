package fpt.aptech.hss.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.AccountParentActivity;
import fpt.aptech.hss.Screen.MainParentActivity;
import fpt.aptech.hss.Screen.MainTecherScreenActivity;
import fpt.aptech.hss.Screen.ParentNotificationActivity;
import fpt.aptech.hss.Screen.SubjectListTecherActivity;
import fpt.aptech.hss.TeacherAccountActivity;

public class CallNavParent {

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
                    Intent intent = new Intent(context, MainParentActivity.class);
                    context.startActivity(intent);
                    return true;
                case R.id.page_3:
                    Intent intent1 = new Intent(context, ParentNotificationActivity.class);
                    context.startActivity(intent1);
                    return true;
                case R.id.page_4:
                    Intent viewProfile = new Intent(context, AccountParentActivity.class);
                    context.startActivity(viewProfile);
                    return true;

            }
            return false;
        }
    };


    public void setDisplay(ScrollView scrollView, Activity activity, double heights){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //chi???u cao
        int height = displayMetrics.heightPixels;
        ViewGroup.LayoutParams params = scrollView.getLayoutParams();
        // Changes the height and width to the specified *pixels*
        double so = height *heights;
        params.height = (int) so;
        scrollView.setLayoutParams(params);
    }
}
