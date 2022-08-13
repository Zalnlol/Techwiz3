package fpt.aptech.hss.Config;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigData {

    public static String IP ="172.16.0.119";
    public static final String profilePreferences = "profilepref";
    public static final String Mail = "mailKey";

    public String userId(Context context){
        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences(profilePreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString(Mail,null);
    }
}
