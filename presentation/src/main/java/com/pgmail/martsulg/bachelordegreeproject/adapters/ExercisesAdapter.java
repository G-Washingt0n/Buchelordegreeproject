package com.pgmail.martsulg.bachelordegreeproject.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgmail.martsulg.bachelordegreeproject.R;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.ExercisesViewModel;

import java.util.List;

import p.martsulg.data.models.ExercisesFeed;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.Holder> {
    private FragmentActivity activity;
    private ExercisesViewModel viewModel;
    private List<ExercisesFeed> exercises;
    private int itemCount = 0;

    public ExercisesAdapter(FragmentActivity activity, ExercisesViewModel exercisesViewModel, List<ExercisesFeed> exercises) {
        this.activity = activity;
        this.viewModel = exercisesViewModel;
        this.exercises = exercises;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView exerciseName;
        TextView exerciseSets;
        ImageView exerciseStart;
        ImageButton moreBtn;


        public Holder(View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.exercise_name);
            exerciseSets = itemView.findViewById(R.id.exercise_sets);
            exerciseStart = itemView.findViewById(R.id.exercise_start);
            moreBtn = itemView.findViewById(R.id.exercise_moreBtn);
        }
    }

    @Override
    public Holder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(final ExercisesAdapter.Holder holder, final int position) {
        holder.exerciseName.setText(exercises.get(position).getExerciseName());
        holder.exerciseSets.setText(activity.getString(R.string.sets, exercises.get(position).getSetsNum()));
        holder.moreBtn.setOnClickListener(v -> viewModel.menuAction(holder.moreBtn, position));
        holder.exerciseStart.setOnClickListener(v -> viewModel.onPlayClick());
        holder.itemView.setOnClickListener(v -> viewModel.goFurther(position));
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void dataChanged(List<ExercisesFeed> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
        itemCount = exercises.size();
    }


}
