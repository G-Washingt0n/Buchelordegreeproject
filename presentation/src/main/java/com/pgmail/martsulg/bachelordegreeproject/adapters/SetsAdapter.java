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
import com.pgmail.martsulg.bachelordegreeproject.extras.CustomDateUtils;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.ExercisesViewModel;
import com.pgmail.martsulg.bachelordegreeproject.viewModels.SetsViewModel;

import java.util.List;

import p.martsulg.data.models.ExercisesFeed;
import p.martsulg.data.models.SetsFeed;

public class SetsAdapter extends RecyclerView.Adapter<SetsAdapter.Holder> {
    private FragmentActivity activity;
    private SetsViewModel viewModel;
    private List<SetsFeed> sets;
    private int itemCount = 0;

    public SetsAdapter(FragmentActivity activity, SetsViewModel setsViewModel, List<SetsFeed> sets) {
        this.activity = activity;
        this.viewModel = setsViewModel;
        this.sets = sets;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView setNum;
        TextView setRepeats;
        TextView setWeight;
        TextView reqTime;
        TextView restTime;
        ImageButton moreBtn;


        public Holder(View itemView) {
            super(itemView);
            setNum = itemView.findViewById(R.id.set_number);
            setRepeats= itemView.findViewById(R.id.set_repeats);
            setWeight= itemView.findViewById(R.id.setWeight);
            reqTime= itemView.findViewById(R.id.setReqTime);
            restTime= itemView.findViewById(R.id.setRestTime);
            moreBtn = itemView.findViewById(R.id.set_moreBtn);
        }
    }

    @Override
    public Holder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_set, parent, false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.setNum.setText(String.valueOf(sets.get(position).getSetNumber()));
        holder.setRepeats.setText("Repeats: " + String.valueOf(sets.get(position).getRepsNum()));
        holder.setWeight.setText("Weight: " + String.valueOf(sets.get(position).getRepWeight()));
        holder.reqTime.setText("Req. time: " + CustomDateUtils.millisToTime(sets.get(position).getReqTime()));
        holder.restTime.setText("Rest time: " + CustomDateUtils.millisToTime(sets.get(position).getRestTime()));
        holder.moreBtn.setOnClickListener(v -> viewModel.menuAction(holder.moreBtn, position));
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void dataChanged(List<SetsFeed> sets) {
        this.sets = sets;
        notifyDataSetChanged();
        itemCount = sets.size();
    }


}
