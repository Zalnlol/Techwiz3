package fpt.aptech.hss.Config;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigData {


    public static String IP ="172.16.1.34";
    public static final String profilePreferences = "login";
    public static final String Mail = "user";
    public String userId(Context context){
        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences(profilePreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString(Mail,null);
    }
}
