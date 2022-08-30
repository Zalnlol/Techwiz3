package fpt.aptech.hss.Config;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigData {



    public static String IP ="172.16.2.236";
    public static final String profilePreferences = "profilepref";
    public static final String Mail = "mailKey";
    public static final String ID = "idKey";
    public String GetMail(Context context){
        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences(profilePreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString(Mail,null).toString();
    }

    public String userId(Context context){
        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences(profilePreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getString(ID,null).toString();
    }
}
