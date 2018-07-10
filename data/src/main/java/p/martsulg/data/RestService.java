package p.martsulg.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import p.martsulg.data.models.DeleteResponse;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.LogInUser;
import p.martsulg.data.models.RegisterUser;
import p.martsulg.data.models.RequestParams;
import p.martsulg.data.models.RequestRelation;
import p.martsulg.data.models.SetsFeed;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestService {

    private static final RestService instance = new RestService();

    private RestApi restApi;

    private RestService() {
        init();
    }

    public static RestService getInstance() {
        return instance;
    }

    private void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/C5528678-BFAB-F70F-FF82-D2CB10670100/" +
                        "0F2D67BF-E621-6650-FFB0-F02FF5CFC100/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();

        restApi = retrofit.create(RestApi.class);
    }

    public Observable<UserInfo> logUser(LogInUser user) {
        return restApi.logUser(user);
    }

    public Observable<UserInfo> regUser(RegisterUser profile) {
        return restApi.regUser(profile);
    }

    public Observable<Boolean> checkToken(String param) {
        return restApi.checkToken(param);
    }

    public Observable<List<TrainingsFeed>> getTrainings(RequestParams request) {
        StringBuilder url = new StringBuilder("data/Timetable?where=ownerId%20%3D%20'");
        url.append(request.getOwnerId());
        url.append("'&sortBy=weekday%20asc");
        return restApi.getTrainings(url.toString());
    }

    public Observable<TrainingsFeed> getExercises(RequestParams request) {
        StringBuilder url = new StringBuilder("data/Timetable/");
        url.append(request.getObjectId());
        url.append("?loadRelations=exercise");
        return restApi.getExercises(url.toString());
    }

    public Observable<UserInfo> getUserInfo(String string) {
        return restApi.getUserInfo(string);
    }

    public Observable<TrainingsFeed> newTraining(TrainingsFeed feed) {
        return restApi.newTraining(feed);
    }

    public Observable<ExercisesFeed> newExercise(ExercisesFeed feed) {
        return restApi.newExercise(feed);
    }

    public Observable<DeleteResponse> delItem(RequestParams request) {
        StringBuilder url = new StringBuilder("data/");
        url.append(request.getTimetable() + "/")
                .append(request.getObjectId());
        return restApi.delItem(url.toString());
    }

    public Observable<TrainingsFeed> updateTraining(TrainingsFeed feed) {
        StringBuilder url = new StringBuilder("data/timetable/");
        url.append(feed.getObjectId());
        return restApi.updateTraining(url.toString(), feed);
    }

    public Observable<ExercisesFeed> updateExercise(ExercisesFeed feed) {
        StringBuilder url = new StringBuilder("data/exercises/");
        url.append(feed.getObjectId());
        return restApi.updateExercise(url.toString(), feed);
    }


    public Observable<UserInfo> updateUser(UserInfo userInfo) {
        StringBuilder url = new StringBuilder("users/");
        url.append(userInfo.getObjectId());
        return restApi.updateUser(url.toString(), userInfo);
    }

    public Observable<Void> logOut(String token) {
        return restApi.logOut(token);
    }

    public Observable<Integer> addRelation (RequestRelation mRelation) {
        return restApi.addRelation(mRelation.getObjectId(), mRelation.getmRelation());
    }

    public Observable<Integer> addSetRelation (RequestRelation mRelation) {
        return restApi.addSetRelation(mRelation.getObjectId(), mRelation.getmRelation());
    }
    public Observable<SetsFeed> newSet(SetsFeed feed) {
        return restApi.newSet(feed);
    }

    public Observable<ExercisesFeed> getSets(RequestParams request) {
            StringBuilder url = new StringBuilder("data/exercises/");
            url.append(request.getObjectId());
            url.append("?loadRelations=sets");
            return restApi.getSets(url.toString());
    }
}
