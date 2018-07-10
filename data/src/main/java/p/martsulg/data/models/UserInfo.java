package p.martsulg.data.models;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    private static volatile UserInfo instance = new UserInfo();

    public UserInfo getInstance() {
        if (instance == null) {
            synchronized (UserInfo.class){
                if(instance==null)
                    instance = new UserInfo();
            }
        }
        return instance;
    }

    public void renewUserInfo(UserInfo info) {
        instance = info;
    }
    private String name;
    private String surname;
    private int age;
    private String email;
    private String ownerId;
    private String objectId;
    @SerializedName("user-token")
    private String token;



    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getAvatarUrl() {
//        return avatarUrl;
//    }
//
//    public void setAvatarUrl(String avatarUrl) {
//        this.avatarUrl = avatarUrl;
//    }

//    public int getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(int admin) {
//        this.admin = admin;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
