package p.martsulg.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by g_washingt0n on 07.06.2018.
 */

public class SetsFeed {
    @SerializedName("restTime")
    private int restTime;
    @SerializedName("repWeight")
    private int repWeight;
    @SerializedName("created")
    private long created;
    @SerializedName("reqTime")
    private int reqTime;
    @SerializedName("ownerId")
    private String ownerId;
    @SerializedName("updated")
    private long updated;
    @SerializedName("setNumber")
    private int setNumber;
    @SerializedName("objectId")
    private String objectId;
    @SerializedName("repsNum")
    private int repsNum;


    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public int getRepWeight() {
        return repWeight;
    }

    public void setRepWeight(int repWeight) {
        this.repWeight = repWeight;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public int getReqTime() {
        return reqTime;
    }

    public void setReqTime(int reqTime) {
        this.reqTime = reqTime;
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

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getRepsNum() {
        return repsNum;
    }

    public void setRepsNum(int repsNum) {
        this.repsNum = repsNum;
    }
}
