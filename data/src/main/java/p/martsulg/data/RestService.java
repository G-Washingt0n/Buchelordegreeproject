package p.martsulg.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import p.martsulg.data.models.LogInUser;
import p.martsulg.data.models.RegisterUser;
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
                .baseUrl("https://api.backendless.com/C5528678-BFAB-F70F-FF82-D2CB10670100/0F2D67BF-E621-6650-FFB0-F02FF5CFC100/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();

        restApi = retrofit.create(RestApi.class);
    }

    /*
    public Observable<ListComments> getComments(CommentParams params) {
        return restApi.getComments(params.getToken());
    }

    public Observable<Object> addComment(CommentParams params) {
        return restApi.addComment(params.getTitle(), params.getMessage(), params.getToken());
    }

    public Observable<Object> delComment(CommentParams params) {
        return restApi.delComment(params.getCommentId(), params.getToken());
    }*/

    public Observable<UserInfo> logUser(LogInUser user) {
        return restApi.logUser(user);
    }

    public Observable<UserInfo> regUser(RegisterUser profile) {

//        RequestBody email =
//                RequestBody.create(MediaType.parse("multipart/form-data"), profile.getEmail());
//        RequestBody password =
//                RequestBody.create(MediaType.parse("multipart/form-data"), profile.getPassword());
//        RequestBody name =
//                RequestBody.create(MediaType.parse("multipart/form-data"), profile.getName());
//        RequestBody avatar =
//                RequestBody.create(MediaType.parse("multipart/form-data"), profile.getAvatar());

        return restApi.regUser(profile);
    }

    /*public Observable<ListAnswers> getAnswers(AnswersParams params) {
        return restApi.getAnswers(params.getCommentId(), params.getToken());
    }

    public Observable<Object> delAnswer(AnswersParams params) {
        return restApi.delAnswer(params.getCommentId(), params.getAnswerId(), params.getToken());
    }

    public Observable<Object> addAnswer(AnswersParams params) {
        return restApi.addAnswer(params.getCommentId(), params.getMessage(), params.getToken());
    }*/
}
