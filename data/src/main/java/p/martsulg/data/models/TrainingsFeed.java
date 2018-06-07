package p.martsulg.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by g_washingt0n on 12.02.2018.
 */

public class TrainingsFeed {
    private float complexity;
    private String trainingName;
    private int weekday;
    private long time;

    private long updated;
    private long created;
    private String ownerId;
    private String objectId;
    @SerializedName("exercise")
    private List<ExercisesFeed> exercises;

    public List<ExercisesFeed> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExercisesFeed> exercises) {
        this.exercises = exercises;
    }

    public float getComplexity() {
        return complexity;
    }

    public void setComplexity(float complexity) {
        this.complexity = complexity;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

//    public List<ListExercises> getExercises() {
//        return exercises;
//    }
//
//    public void setExercises(List<ListExercises> exercises) {
//        this.exercises = exercises;
//    }
}
