package com.pgmail.martsulg.bachelordegreeproject.viewModels;

import android.content.Intent;
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
import com.pgmail.martsulg.bachelordegreeproject.adapters.ExercisesAdapter;
import com.pgmail.martsulg.bachelordegreeproject.adapters.SetsAdapter;
import com.pgmail.martsulg.bachelordegreeproject.fragments.ExerciseConstructFragment;
import com.pgmail.martsulg.bachelordegreeproject.fragments.SetConstructFragment;
import com.pgmail.martsulg.bachelordegreeproject.fragments.SetsFragment;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.DeleteResponse;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.RequestParams;
import p.martsulg.data.models.SetsFeed;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.DelItemUseCase;
import p.martsulg.domain.sets.GetSetsListUseCase;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class SetsViewModel implements MyViewModel {
    public SetsAdapter adapter;
    private FragmentActivity activity;
    private GetSetsListUseCase listUseCase = new GetSetsListUseCase();
    private DelItemUseCase delItemUseCase = new DelItemUseCase();
    private List<SetsFeed> sets;
    private UserInfo user = new UserInfo().getInstance();
    private String currentExerciseId;

    public SetsViewModel(FragmentActivity activity, String exerciseId) {
        this.activity = activity;
        adapter = new SetsAdapter(activity, this, null);
        currentExerciseId = exerciseId;
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
        params.setObjectId(currentExerciseId);

        listUseCase.execute(params, new DisposableObserver<ExercisesFeed>() {
            @Override
            public void onNext(ExercisesFeed feed) {
                sets = feed.getSets();
                adapter.dataChanged(feed.getSets());
                adapter.notifyDataSetChanged();
                Log.e("adapter notify", String.valueOf(feed.getSets().size()));
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
        params.setTimetable(activity.getString(R.string.table_sets));
        delItemUseCase.execute(params, new DisposableObserver<DeleteResponse>() {

            @Override
            public void onNext(DeleteResponse response) {
                Toast.makeText(activity, "Item removed!", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();     //убрать эту дичь потом
                adapter.notifyItemRemoved(position);    //убрать эту дичь потом
                getRequest();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                delItemUseCase.dispose();
            }
        });
    }

    @Override
    public void addRequest() {
    }

    public void onFabClick() {
        openExtraFragment(new SetConstructFragment().getInstance(activity.getSupportFragmentManager(), null, currentExerciseId));
    }

    public void menuAction(final ImageButton moreBtn, final int position) {
        PopupMenu popup = new PopupMenu(activity, moreBtn);
        popup.getMenu().add(Menu.NONE, 0, Menu.NONE, activity.getResources().getString(R.string.edit));
        popup.getMenu().add(Menu.NONE, 1, Menu.NONE, activity.getResources().getString(R.string.delete));
        popup.getMenu().add(Menu.NONE, 2, Menu.NONE, activity.getResources().getString(R.string.share));
        popup.show();
        popup.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case 0: //edit
//                        openExtraFragment(new SetConstructFragment().getInstance(activity.getSupportFragmentManager(), sets.get(position), currentExerciseId));
                    break;
                case 1: //delete
                    delRequest(sets.get(position).getObjectId(), position);
                    break;
                case 2: //share
//                        shareAction(sets.get(position));
                    break;
            }
            return true;
        });
    }

//    private void shareAction(SetsFeed setsFeed){
//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.setType("text/*");
//        shareIntent.putExtra(Intent.EXTRA_TEXT, createShareText(setsFeed));
//        activity.startActivity(Intent.createChooser(shareIntent, "Share"));
//    }
//
//    private String createShareText(SetsFeed setsFeed){
//        return setsFeed.getExerciseName() +
//                "\n" +
//                "Sets: " + String.valueOf(setsFeed.getSetsNum());
//    }


    private void openExtraFragment(Fragment fragment) {
        NavigationActivity.showFragment(activity.getSupportFragmentManager(), fragment);
    }
}
