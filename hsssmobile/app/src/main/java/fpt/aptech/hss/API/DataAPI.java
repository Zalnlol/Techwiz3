package fpt.aptech.hss.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Model.ModelString;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataAPI {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    DataAPI api = new Retrofit.Builder()
            .baseUrl("http://"+ ConfigData.IP +":7777/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DataAPI.class);
    @POST("account/create")
    Call<ModelString> APICreateAccount (@Query("mail") String mail,
                                        @Query("password") String password,
                                        @Query("code") String code,
                                        @Query("role") String role,
                                        @Query("name") String name,
                                        @Query("dob") String dob


    );
    @POST("account/login")
    Call<ModelString> APILogin (@Query("mail") String mail,
                                @Query("password") String password
                                );


    @GET("getMyClasses")
    Call<ModelString> GetMyClasses (@Query("mail") String mail
    );


}
