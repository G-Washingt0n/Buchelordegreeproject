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
import com.pgmail.martsulg.bachelordegreeproject.fragments.ExerciseConstructFragment;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import p.martsulg.data.models.DeleteResponse;
import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.RequestParams;
import p.martsulg.data.models.TrainingsFeed;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.DelItemUseCase;
import p.martsulg.domain.exercises.AddExerciseUseCase;
import p.martsulg.domain.exercises.GetExerсisesListUseCase;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class ExercisesViewModel implements MyViewModel {
    public ExercisesAdapter adapter;
    private FragmentActivity activity;
    private GetExerсisesListUseCase listUseCase = new GetExerсisesListUseCase();
    private AddExerciseUseCase addUseCase = new AddExerciseUseCase();
    private DelItemUseCase delItemUseCase = new DelItemUseCase();
    private List<ExercisesFeed> exercises;
    //    private ArrayList<ListTrainings> adapterList;
    private UserInfo user = new UserInfo().getInstance();
    private String currentTrainingId;

    public ExercisesViewModel(FragmentActivity activity, String trainingId) {
        this.activity = activity;
        adapter = new ExercisesAdapter(activity, this, null);
        currentTrainingId = trainingId;
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
        params.setObjectId(currentTrainingId);

        listUseCase.execute(params, new DisposableObserver<TrainingsFeed>() {
            @Override
            public void onNext(TrainingsFeed feed) {
                exercises = feed.getExercises();
                adapter.dataChanged(feed.getExercises());
                adapter.notifyDataSetChanged();
                Log.e("adapter notify", String.valueOf(feed.getExercises().size()));
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
        params.setTimetable(activity.getString(R.string.table_exercises));
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

    public void onPlayClick() {
        //TODO add timer screen
    }

    public void onFabClick() {
        openExtraFragment(new ExerciseConstructFragment().getInstance(activity.getSupportFragmentManager(), null, currentTrainingId));
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
                        openExtraFragment(new ExerciseConstructFragment().getInstance(activity.getSupportFragmentManager(), exercises.get(position), currentTrainingId));
//                        NavigationActivity.putExtraFragment(activity.getSupportFragmentManager(),
//                                new TrainingConstructFragment().getInstance(exercises.get(position)));
                        break;
                    case 1: //delete
                        delRequest(exercises.get(position).getObjectId(), position);
                        break;
                    case 2: //share
                        shareAction(exercises.get(position));
                        break;
                }
                return true;
            }
        });
    }

    private void shareAction(ExercisesFeed exercisesFeed){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/*");
        shareIntent.putExtra(Intent.EXTRA_TEXT, createShareText(exercisesFeed));
        activity.startActivity(Intent.createChooser(shareIntent, "Share"));
    }

    private String createShareText(ExercisesFeed exercisesFeed){
        return exercisesFeed.getExerciseName() +
                "\n" +
                "Sets: " + String.valueOf(exercisesFeed.getSetsNum());
    }

    public void goFurther(int position) {
//        openExtraFragment(no new  );
//        Toast.makeText(activity, "Tap on exercises item #" + position,
//                Toast.LENGTH_LONG).show();
    }

    private void openExtraFragment(Fragment fragment) {
        NavigationActivity.showFragment(activity.getSupportFragmentManager(), fragment);
    }
}
