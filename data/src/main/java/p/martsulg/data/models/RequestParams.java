package p.martsulg.data.models;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class RequestParams {
    private String ownerId;
    private String objectId;

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
