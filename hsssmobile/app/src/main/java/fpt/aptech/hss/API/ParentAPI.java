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

public interface ParentAPI {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ParentAPI api = new Retrofit.Builder()
            .baseUrl("http://"+ ConfigData.IP +":8080/KSS/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ParentAPI.class);
    @POST("parent/register")
    Call<ModelString> APICreateParentAccount (@Query("mail") String mail,
                                        @Query("password") String password,
                                        @Query("code") String code,
                                        @Query("phone") String role,
                                        @Query("name") String name,
                                        @Query("dob") String dob


    );
    @GET("parent/get/mark")
    Call<List<ModelString>> APIParentGetMark (@Query("mail") String mail);
    @GET("parent/get/class")
    Call<List<ModelString>> ParentGetClass (@Query("mail") String mail);
    @GET("parent/get/test")
    Call<List<ModelString>> ParentGetExam (@Query("mail") String mail);
    @GET("parent/mark/exam")
    Call<ModelString> MarkByExam (@Query("exam") String exam, @Query("email") String email);
}
