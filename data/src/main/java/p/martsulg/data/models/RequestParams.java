package p.martsulg.data.models;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class RequestParams {
    private String ownerId;
    private String objectId;
    private String timetable;

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
