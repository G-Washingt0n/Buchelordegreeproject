package p.martsulg.data;


import java.util.List;

import io.reactivex.Observable;
import p.martsulg.data.models.DeleteResponse;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.LogInUser;
import p.martsulg.data.models.RegisterUser;
import p.martsulg.data.models.Relation;
import p.martsulg.data.models.SetsFeed;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET
    Observable<ExercisesFeed> getSets(@Url String url);

    @GET("users/{userId}?props")
    Observable<UserInfo> getUserInfo(@Path("userId") String id);

    @POST("data/timetable")
    Observable<TrainingsFeed> newTraining(@Body TrainingsFeed feed);

    @POST("data/exercises")
    Observable<ExercisesFeed> newExercise(@Body ExercisesFeed feed);

    @PUT
    Observable<TrainingsFeed> updateTraining(@Url String string, @Body TrainingsFeed feed);

    @POST
    Observable<ExercisesFeed> updateExercise(@Url String string, @Body ExercisesFeed feed);

    @PUT
    Observable<UserInfo> updateUser(@Url String string, @Body UserInfo info);

    @DELETE
    Observable<DeleteResponse> delItem(@Url String url);

    @GET("users/logout")
    Observable<Void> logOut(@Header("user-token") String token);

    @PUT("data/Timetable/{objectId}/exercise")
    Observable<Integer> addRelation(@Path("objectId") String id, @Body Relation relation);


    @PUT("data/Exercises/{objectId}/sets")
    Observable<Integer> addSetRelation(@Path("objectId") String id, @Body Relation relation);

    @POST("data/sets")
    Observable<SetsFeed> newSet(@Body SetsFeed feed);

}