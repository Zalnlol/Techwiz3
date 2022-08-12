//package fpt.aptech.hss.API;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import fpt.aptech.hss.Config.ConfigData;
//import fpt.aptech.hss.Model.ModelString;
//import retrofit2.Call;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.http.GET;
//import retrofit2.http.POST;
//import retrofit2.http.Query;
//
//public interface TokenAPI {
//    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//    DataAPI api = new Retrofit.Builder()
//            .baseUrl("http://"+ ConfigData.IP +":7777/api/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(DataAPI.class);
//    @GET("token/get")
//    Call<AccountToken> GetToken(@Query("token") String token);
//    @POST("token/add")
//    Call<AccountToken> AddToken(@Body AccountToken token);
//}
