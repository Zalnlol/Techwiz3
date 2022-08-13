package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btn_Login;
    EditText Login_mail,Login_password;
    public static final String profilePreferences = "login";
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesProfile;
    SharedPreferences.Editor editor;
    final static String USERNAME_KEY = "user";
    final static String PASSWORD_KEY = "password";



    public static final String accountProfilePreferences = "profilepref";
    public static final String sharedclasspreference = "classprofilepref";

    public static final String LoginStatus = "loginStatusKey";
    public static final String Id = "idKey";
    public static final String Mail = "mailKey";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Birthday = "birthKey";
    public static final String Gender = "genderKey";
    public static final String Role = "roleKey";
    public static final String Avatar = "avatarKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        btn_Login = findViewById(R.id.btn_Login);
        Login_mail = findViewById(R.id.Login_mail);
        Login_password = findViewById(R.id.Login_password);

        checkPreferences();
        btn_Login();
    }

    private void checkPreferences() {
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String username =  sharedPreferences.getString(LoginActivity.USERNAME_KEY,null);
        String password =  sharedPreferences.getString(LoginActivity.PASSWORD_KEY,null);
        Login_mail.setText(username);
        Login_password.setText(password);


    }

    private void btn_Login() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean check = true;


                if (Login_mail.getText().toString().trim().equals("")) {

                    Toast.makeText(LoginActivity.this, "Mail cannot blank", Toast.LENGTH_SHORT).show();
                }

                if (Login_password.getText().toString().trim().equals("")) {

                    Toast.makeText(LoginActivity.this, "Password cannot blank", Toast.LENGTH_SHORT).show();
                }

                DataAPI.api.APILogin(Login_mail.getText().toString(), Login_password.getText().toString()).enqueue(new Callback<ModelString>() {
                    @Override
                    public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                        if (response.body().getData1().equals("Done")) {
                            String user = Login_mail.getText().toString();
                            String password = Login_password.getText().toString();


                            sharedPreferences = getSharedPreferences(profilePreferences, Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString(USERNAME_KEY, user);
                            editor.putString(PASSWORD_KEY, password);
                            editor.commit();

                            sharedPreferencesProfile = getSharedPreferences(accountProfilePreferences, Context.MODE_PRIVATE);
                            editor = sharedPreferencesProfile.edit();
                            editor.putString(LoginStatus,response.body().getData1());
                            editor.putString(Id, response.body().getData2());
                            editor.putString(Name, response.body().getData3());
                            editor.putString(Phone,response.body().getData4());
                            editor.putString(Birthday, response.body().getData5());
                            editor.putString(Gender, response.body().getData6());
                            editor.putString(Role, response.body().getData7());
                            editor.putString(Avatar, response.body().getData8());
                            editor.commit();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, response.body().getData1(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelString> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Connect error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}