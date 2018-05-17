package p.martsulg.data.models;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class ListExercises {
    private int sets_num;
    private String ownerId;
    private String ex_name;
    private String objectId;

    public int getSets_num() {
        return sets_num;
    }

    public void setSets_num(int sets_num) {
        this.sets_num = sets_num;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getEx_name() {
        return ex_name;
    }

    public void setEx_name(String ex_name) {
        this.ex_name = ex_name;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
