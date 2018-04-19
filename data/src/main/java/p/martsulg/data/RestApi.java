package p.martsulg.data;


import io.reactivex.Observable;
import p.martsulg.data.models.LogInUser;
import p.martsulg.data.models.RegisterUser;
import p.martsulg.data.models.UserInfo;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RestApi {

//    @Headers("Accept: application/json")
//    @POST("api/v1/auth/login")
//    Observable<UserInfo> logUser(@Query("email") String email, @Query("password") String password);

//    @Multipart
//    @Headers("Accept: application/json")
//    @POST("api/v1/auth/register")
//    Observable<UserInfo> regUser(@Part("email") RequestBody email, @Part("password") RequestBody password,
//                                  @Part("name") RequestBody name, @Part("avatar") RequestBody avatar);

    @POST("users/login")
    Observable<UserInfo> logUser(@Body LogInUser profile);

    @POST("users/register")
    Observable<UserInfo> regUser(@Body RegisterUser profile);
}