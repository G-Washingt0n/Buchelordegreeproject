package com.pgmail.martsulg.buchelordegreeproject.viewModels;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.pgmail.martsulg.buchelordegreeproject.activities.EntryActivity;
import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.buchelordegreeproject.adapters.TrainingsAdapter;
import com.pgmail.martsulg.buchelordegreeproject.fragments.TrainingConstructFragment;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.RequestParams;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.trainings.AddTrainingUseCase;
import p.martsulg.domain.trainings.DelTrainingUseCase;
import p.martsulg.domain.trainings.GetTrainingsListUseCase;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class TrainingsViewModel implements MyViewModel {
    //remove token - ?
    private String token = EntryActivity.preferences.getString("Token", null);
    public TrainingsAdapter adapter;
    private FragmentActivity activity;
    private GetTrainingsListUseCase listUseCase = new GetTrainingsListUseCase();
    private AddTrainingUseCase addUseCase = new AddTrainingUseCase();
    private DelTrainingUseCase delUseCase = new DelTrainingUseCase();
    //    private ArrayList<ListTrainings> adapterList;
    private UserInfo user = new UserInfo().getInstance();

    public TrainingsViewModel(FragmentActivity activity) {
        this.activity = activity;
        adapter = new TrainingsAdapter(activity);
    }

    @Override
    public void init() {
    }


    @Override
    public void resume() {
        getRequest();

    }

    @Override
    public void pause() {

    }


    @Override
    public void release() {
    }


    @Override
    public void getRequest() {
        RequestParams params = new RequestParams();
        params.setOwnerId(user.getOwnerId());

        listUseCase.execute(params, new DisposableObserver<List<TrainingsFeed>>() {
            @Override
            public void onNext(List<TrainingsFeed> trainingsFeeds) {
                adapter.dataChanged(trainingsFeeds);
                adapter.notifyDataSetChanged();
                Log.e("adapter notify", String.valueOf(trainingsFeeds.size()));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                listUseCase.dispose();
            }
        });
    }

    @Override
    public void delRequest() {
    }

    @Override
    public void addRequest() {
//        RequestParams params = new RequestParams();
//        params.setTitle(title2send.get());
//        params.setMessage(comment2send.get());
//        params.setToken(EntryActivity.preferences.getString("Token", null));
//        addUseCase.execute(params, new DisposableObserver<Object>() {
//            @Override
//            public void onNext(Object o) {
//                getRequest(currPage);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                comment2send.set(null);
//                title2send.set(null);
//                addUseCase.dispose();
//            }
//        });
    }

    public void onFabClick() {
        NavigationActivity.putExtraFragment(activity.getSupportFragmentManager(), new TrainingConstructFragment().getInstance(null));
    }

}
