package p.martsulg.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.LogInUser;
import p.martsulg.data.models.RegisterUser;
import p.martsulg.data.models.RequestParams;
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
//        url.append("'&sortBy=queuePos%20asc");
        return restApi.getExercises(url.toString());
    }

    public Observable<UserInfo> getUserInfo(String string) {
        return restApi.getUserInfo(string);
    }

    public Observable<TrainingsFeed> newTraining(TrainingsFeed feed) {
        return restApi.newTraining(feed);
    }

    public Observable<Void> newExercise(ExercisesFeed feed) {
        return restApi.newExercise(feed);
    }

}
