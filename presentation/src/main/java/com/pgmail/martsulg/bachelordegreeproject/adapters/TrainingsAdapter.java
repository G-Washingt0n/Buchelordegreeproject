package com.pgmail.martsulg.bachelordegreeproject.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.extras.CustomDateUtils;
import com.pgmail.martsulg.bachelordegreeproject.extras.WeekdaysEnum;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.TrainingsViewModel;

import java.util.List;

import p.martsulg.data.models.TrainingsFeed;
//import p.martsulg.domain.DelCommentUseCase;

/**
 * Created by g_washingt0n on 08.02.2018.
 */

public class TrainingsAdapter extends RecyclerView.Adapter<TrainingsAdapter.Holder> {
    private FragmentActivity activity;
    private TrainingsViewModel viewModel;
    private List<TrainingsFeed> trainings;
    private int itemCount = 0;

    public TrainingsAdapter(FragmentActivity activity, TrainingsViewModel trainingsViewModel) {
        this.activity = activity;
        this.viewModel = trainingsViewModel;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView trainingDay;
        TextView trainingCreated;
        TextView trainingName;
        TextView trainingTime;
        RatingBar trainingComplexity;
        ImageButton moreBtn;


        public Holder(View itemView) {
            super(itemView);

            trainingDay = itemView.findViewById(R.id.training_day);
            trainingCreated = itemView.findViewById(R.id.training_created);
            trainingName = itemView.findViewById(R.id.training_name);
            trainingTime = itemView.findViewById(R.id.training_time);
            trainingComplexity = itemView.findViewById(R.id.training_complexity);
            moreBtn = itemView.findViewById(R.id.training_moreBtn);
        }
    }

    @Override
    public Holder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_training, parent, false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(final TrainingsAdapter.Holder holder, final int position) {
        holder.trainingDay.setText(WeekdaysEnum.convertIntToShortDay(trainings.get(position).getWeekday()));
        //TODO remove with date convertor
        holder.trainingCreated.setText(fieldSelector(position));
        holder.trainingTime.setText(CustomDateUtils.millisToTime(trainings.get(position).getTime()));
        holder.trainingName.setText(trainings.get(position).getTrainingName());
        holder.trainingComplexity.setRating(trainings.get(position).getComplexity());
        holder.moreBtn.setOnClickListener(v ->
                viewModel.menuAction(holder.moreBtn, position));

        holder.itemView.setOnClickListener((v) ->
                viewModel.goFurther(position));

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void dataChanged(List<TrainingsFeed> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
        itemCount = trainings.size();
    }

    private String fieldSelector(int position) {
        if (trainings.get(position).getUpdated() != 0) {
            return "updated: " + CustomDateUtils.millisToDate(trainings.get(position).getUpdated());
        } else {
            return "created: " + CustomDateUtils.millisToDate(trainings.get(position).getCreated());
        }
    }




}
