package com.pgmail.martsulg.bachelordegreeproject.adapters;

import android.support.v7.util.DiffUtil;

import java.util.List;
import java.util.Objects;

import p.martsulg.data.models.TrainingsFeed;

public class TrainingsFeedDiffUtillCallback extends DiffUtil.Callback {

    private final List<TrainingsFeed> oldList;
    private final List<TrainingsFeed> newList;

    public TrainingsFeedDiffUtillCallback(List<TrainingsFeed> oldList, List<TrainingsFeed> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        TrainingsFeed oldTraining = oldList.get(oldItemPosition);
        TrainingsFeed newTraining = oldList.get(oldItemPosition);
        return oldTraining.getCreated() == newTraining.getCreated();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        TrainingsFeed oldTraining = oldList.get(oldItemPosition);
        TrainingsFeed newTraining = oldList.get(oldItemPosition);
        return Objects.equals(oldTraining, newTraining);
    }
}
