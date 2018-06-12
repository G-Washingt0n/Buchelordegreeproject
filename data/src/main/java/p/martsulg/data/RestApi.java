package p.martsulg.data;


import java.util.List;

import io.reactivex.Observable;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.LogInUser;
import p.martsulg.data.models.RegisterUser;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;


public interface RestApi {

    @POST("users/login")
    Observable<UserInfo> logUser(@Body LogInUser profile);

    @POST("users/register")
    Observable<UserInfo> regUser(@Body RegisterUser profile);

    @GET("users/isvalidusertoken/{token}")
    Observable<Boolean> checkToken(@Path("token") String param);

    @GET
    Observable<List<TrainingsFeed>> getTrainings(@Url String url);

    @GET
    Observable<TrainingsFeed> getExercises(@Url String string);

    @GET("users/{userId}?props")
    Observable<UserInfo> getUserInfo(@Path("userId") String id);

    @POST("data/timetable")
    Observable<TrainingsFeed> newTraining(@Body TrainingsFeed feed);

    @POST("data/timetable/{objectId}")
    Observable<Void> updateTraining(@Body TrainingsFeed feed);

    @POST("data/exercises")
    Observable<Void> newExercise(@Body ExercisesFeed feed);

    @DELETE
    Observable<Long> delItem(@Url String url);
}