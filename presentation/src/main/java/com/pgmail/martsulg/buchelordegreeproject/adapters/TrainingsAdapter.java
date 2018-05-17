package com.pgmail.martsulg.buchelordegreeproject.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pgmail.martsulg.buchelordegreeproject.R;
import com.pgmail.martsulg.buchelordegreeproject.activities.NavigationActivity;
import com.pgmail.martsulg.buchelordegreeproject.extras.WeekdaysEnum;
import com.pgmail.martsulg.buchelordegreeproject.fragments.TrainingConstructFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import p.martsulg.data.models.TrainingsFeed;
//import p.martsulg.domain.DelCommentUseCase;

/**
 * Created by g_washingt0n on 08.02.2018.
 */

public class TrainingsAdapter extends RecyclerView.Adapter<TrainingsAdapter.Holder> {
    private FragmentActivity activity;
    private List<TrainingsFeed> trainings;
    private int itemCount = 0;

    public TrainingsAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView trainingDay;
        TextView trainingCreated;
        TextView trainingName;
        //        TextView showTraining;
        TextView trainingTime;
        RatingBar trainingComplexity;
        ImageButton moreBtn;


        public Holder(View itemView) {
            super(itemView);

            trainingDay = itemView.findViewById(R.id.training_day);
            trainingCreated = itemView.findViewById(R.id.training_created);
            trainingName = itemView.findViewById(R.id.training_name);
//            showTraining = itemView.findViewById(R.id.show_training);
            trainingTime = itemView.findViewById(R.id.training_time);
            trainingComplexity = itemView.findViewById(R.id.training_complexity);
            moreBtn = itemView.findViewById(R.id.training_moreBtn);
        }
    }

    @Override
    public Holder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_training, parent, false);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "Short Tap on trainings item!",
                        Toast.LENGTH_LONG).show();
            }
        });
        root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(parent.getContext(), "Long Tap on trainings item!",
                        Toast.LENGTH_LONG).show();
                NavigationActivity.putExtraFragment(activity.getSupportFragmentManager(),
                        new TrainingConstructFragment().getInstance(trainings.get(root.getVerticalScrollbarPosition())));
                return true;
            }
        });

        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(TrainingsAdapter.Holder holder, final int position) {
        holder.trainingDay.setText(WeekdaysEnum.convertIntToShortDay(trainings.get(position).getWeekday()));
        //TODO remove with date convertor
        holder.trainingCreated.setText(fieldSelector(position));//String.valueOf(trainings.get(position).getCreated()));
        DateFormat format = new SimpleDateFormat("d.M.yy", Locale.ENGLISH);
        holder.trainingTime.setText(millisToTime(trainings.get(position).getTime()));//String.valueOf(trainings.get(position).getTime()));
        holder.trainingName.setText(trainings.get(position).getTrainingName());
        holder.trainingComplexity.setRating(trainings.get(position).getComplexity());
        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






        /*holder.showTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                showFragment(manager, new AnswersFragment().newInstance(manager, trainings.get(position)));

            }
        });
        */

//        holder.commDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DelCommentUseCase delUseCase = new DelCommentUseCase();
//                RequestParams params = new RequestParams();
//                params.setCommentId(trainings.get(position).getComment_id());
//                params.setToken(EntryActivity.preferences.getString("Token", null));
//                delUseCase.execute(params, new DisposableObserver<Object>() {
//                    @Override
//                    public void onNext(Object o) {
//                        trainings.remove(position);
//                        dataChanged(trainings);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("Error removing comment", e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        delUseCase.dispose();
//                    }
//                });
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void dataChanged(List<TrainingsFeed> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
        itemCount = trainings.size();
        new TrainingsAdapter(activity);
    }

    private String fieldSelector(int position) {
        if (trainings.get(position).getUpdated() != 0) {
            return "updated: " + millisToDate(trainings.get(position).getUpdated());
        } else {
            return "created: " + millisToDate(trainings.get(position).getCreated());
        }
    }

    private String millisToDate(long millis) {
//        Date date = new Date(millis);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        String str = cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);
//        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
//        try {
//            Date date = format.parse(cal.toString());
//            return date.toString();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return str;
    }

    private String millisToTime(long millis) {
        Calendar cal = new GregorianCalendar();//Calendar.getInstance();
        cal.setTimeInMillis(millis);
        String str = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);

//        DateFormat format = new SimpleDateFormat("h:mm", Locale.ENGLISH);
//        try {
//            Date date = format.parse(str);
//            return date.toString();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return str;//cal.getTime().toString();
    }

}
