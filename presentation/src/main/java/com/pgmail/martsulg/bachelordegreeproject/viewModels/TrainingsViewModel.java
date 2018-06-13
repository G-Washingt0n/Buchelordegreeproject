package com.pgmail.martsulg.bachelordegreeproject.viewModels;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.bachelordegreeproject.adapters.TrainingsAdapter;
import com.pgmail.martsulg.bachelordegreeproject.fragments.ExercisesFragment;
import com.pgmail.martsulg.bachelordegreeproject.fragments.TrainingConstructFragment;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.DeleteResponse;
import p.martsulg.data.models.RequestParams;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.DelItemUseCase;
import p.martsulg.domain.trainings.GetTrainingsListUseCase;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class TrainingsViewModel implements MyViewModel {

    public TrainingsAdapter adapter;
    private FragmentActivity activity;
    private GetTrainingsListUseCase listUseCase = new GetTrainingsListUseCase();
    DelItemUseCase delItemUseCase = new DelItemUseCase();
    private List<TrainingsFeed> trainings;
    private UserInfo user = new UserInfo().getInstance();

    public TrainingsViewModel(FragmentActivity activity) {
        this.activity = activity;
        adapter = new TrainingsAdapter(activity, this);
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
                trainings = trainingsFeeds;
                adapter.dataChanged(trainingsFeeds);
                adapter.notifyDataSetChanged();
                Log.e("adapter notify", String.valueOf(trainingsFeeds.size()));
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                listUseCase.dispose();
            }
        });
    }

    @Override
    public void delRequest(String objectId, int position) {
        RequestParams params = new RequestParams();
        params.setObjectId(objectId);
        params.setTimetable(activity.getString(R.string.table_timetable));
        delItemUseCase.execute(params, new DisposableObserver<DeleteResponse>() {
            @Override
            public void onNext(DeleteResponse response) {
                Toast.makeText(activity, "Item removed!", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();     //не забыть обновить данные
                adapter.notifyItemRemoved(position);
                getRequest();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("deleteError", e.getMessage());
                Toast.makeText(activity, "Item removed!", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();     //не забыть обновить данные
                adapter.notifyItemRemoved(position);
                getRequest();
            }

            @Override
            public void onComplete() {
                adapter.notifyDataSetChanged();     //не забыть обновить данные
                delItemUseCase.dispose();
            }
        });
    }

    @Override
    public void addRequest() {

    }

    public void onFabClick() {
        NavigationActivity.putExtraFragment(activity.getSupportFragmentManager(), new TrainingConstructFragment().getInstance(activity.getSupportFragmentManager(), null));
    }

    public void menuAction(final ImageButton moreBtn, final int position) {
        PopupMenu popup = new PopupMenu(activity, moreBtn);
        popup.getMenu().add(Menu.NONE, 0, Menu.NONE, activity.getResources().getString(R.string.edit));
        popup.getMenu().add(Menu.NONE, 1, Menu.NONE, activity.getResources().getString(R.string.delete));
        popup.getMenu().add(Menu.NONE, 2, Menu.NONE, activity.getResources().getString(R.string.share));
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case 0: //edit
                        openExtraFragment(new TrainingConstructFragment().getInstance(activity.getSupportFragmentManager(), trainings.get(position)));
//                        NavigationActivity.putExtraFragment(activity.getSupportFragmentManager(),
//                                new TrainingConstructFragment().getInstance(trainings.get(position)));
                        break;
                    case 1: //delete
                        delRequest(trainings.get(position).getObjectId(), position);
                        break;
                    case 2: //share
                        //TODO
                        break;
                }
                return true;
            }
        });
    }

    public void goFurther(int position) {
        openExtraFragment(ExercisesFragment.getInstance(activity.getSupportFragmentManager(), trainings.get(position)));
    }

    private void openExtraFragment(Fragment fragment) {
        NavigationActivity.putExtraFragment(activity.getSupportFragmentManager(), fragment);
    }


}
