package fpt.aptech.hss.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Model.ModelString;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
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
    Call<List<ModelString>> GetMyClasses (@Query("mail") String mail);

    @GET("getTeacer/myresource")
    Call<List<ModelString>> GetTeacherMyResource (@Query("id") String id);
    
    @GET("getMyClassesDetails")
    Call<ModelString> GetMyClassesDetails (@Query("id") String id);
    @GET("student/get/test")
    Call<List<ModelString>> StudentGetExam (@Query("email") String email);
    @GET("student/get/class")
    Call<List<ModelString>> StudentGetClass (@Query("email") String email);
    @GET("student/class/details")
    Call<List<ModelString>> ListStudenByClass (@Query("name") String name);
    @GET("student/semeter/course")
    Call<List<ModelString>> ListSemeterCourse (@Query("name") String name);
    @GET("student/semeter/listcouser")
    Call<List<ModelString>> ListSemeterListCourse (@Query("name") String name, @Query("sname") String sname);
    @GET("student/mark/exam")
    Call<ModelString> MarkByExam (@Query("exam") String exam, @Query("email") String email);
    @GET("document/get")
    Call<ModelString> DoccumentById ( @Query("id") String id);
    @GET("student/get/test/class")
    Call<List<ModelString>> StudentExamClass (@Query("email") String email,@Query("sname") String sname);
    @GET("student/get/account")
    Call<ModelString> StudentGetAccount (@Query("email") String email);
    @GET("document/get/mail")
    Call<List<ModelString>> DoccumentAll (@Query("mail") String mail);


    @GET("getTeacer/mytest")
    Call<List<ModelString>> GetTeacherMyTest (@Query("id") String id);

    @GET("getTeacer/getsubject")
    Call<List<ModelString>> GetSubject (@Query("id") String id);

    @GET("getTeacer/getsubject/detail")
    Call<ModelString> GetSubjectDetail (@Query("id") String id);

    @GET("getTeacer/getsubject/reoucedetail")
    Call<ModelString> GetReouceDetail (@Query("id") String id);

    @POST("getTeacer/getsubject/postreouceedit")
    Call<ModelString> PostReouceEdit (@Query("id") String id,
                                      @Query("content") String content );
    @GET("getTeacer/getexamlist")
    Call<List<ModelString>> GetExem (@Query("id") String id);

    @GET("getTeacer/getexamclassroom")
    Call<List<ModelString>> Getclassroom (@Query("id") String id);


    @POST("getTeacer/postcreateexam")
    Call<ModelString> CreateExem (@Query("id") String id,
                                  @Query("name") String name,
                                  @Query("classroom") String classroom,
                                  @Query("startday") String startday);

    @GET("getTeacer/examdetail")
    Call<ModelString> ExemDetail (@Query("id") String id);

    @GET("getTeacer/marklist")
    Call<List<ModelString>> MarkList (@Query("id") String id,
                                        @Query("idcourse") String idcourse);

    @GET("getTeacer/markcreate")
    Call<List<ModelString>> MarkCreate (@Query("id") String id,
                                        @Query("idcourse") String idcourse);

    @POST("getTeacer/markcreatepost")
    Call<List<ModelString>> MarkCreatePost (@Body List<ModelString> data);
    @GET("notification/add/")
    Call<ModelString> TokenAdd (@Query("token") String token);
    @GET("notification/add/mail")
    Call<ModelString> TokenLogin (@Query("token") String token,@Query("mail") String mail);
}
