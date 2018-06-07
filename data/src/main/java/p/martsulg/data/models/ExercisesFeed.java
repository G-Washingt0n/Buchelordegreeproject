package p.martsulg.data.models;

import java.util.List;

/**
 * Created by g_washingt0n on 12.02.2018.
 */

public class ExercisesFeed {
    private String exerciseName;
    private int setsNum;
    private boolean repeatable;
    private int queuePos;
    private String ownerId;
    private String objectId;
    private List<SetsFeed> sets;

    public List<SetsFeed> getSets() {
        return sets;
    }

    public void setSets(List<SetsFeed> sets) {
        this.sets = sets;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getSetsNum() {
        return setsNum;
    }

    public void setSetsNum(int setsNum) {
        this.setsNum = setsNum;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public int getQueuePos() {
        return queuePos;
    }

    public void setQueuePos(int queuePos) {
        this.queuePos = queuePos;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
