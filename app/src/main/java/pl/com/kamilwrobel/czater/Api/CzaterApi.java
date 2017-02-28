package pl.com.kamilwrobel.czater.Api;

import pl.com.kamilwrobel.czater.Api.dto.Token;
import pl.com.kamilwrobel.czater.Api.dto.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CzaterApi {

    @GET("/users/me")
    Call<User> getMe();

    @POST("/users/login")
    @FormUrlEncoded
    Call<Token> login(@Field("login") String login, @Field("password") String password);

    @POST("/users/register")
    @FormUrlEncoded
    Call<Token> register(@Field("login") String login, @Field("password") String password);
}
