package praktikum.develops.a11414001.digitourapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sandy on 6/8/2017.
 */

public class ModelAchievementUser extends RealmObject {
    @PrimaryKey
    private int achievement_user_id;

    private String user;
    private String checkpoint_name;
    private String challenge_type_name;
    private String hadiah;

    public int getAchievement_user_id() {
        return achievement_user_id;
    }

    public void setAchievement_user_id(int achievement_user_id) {
        this.achievement_user_id = achievement_user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCheckpoint_name() {
        return checkpoint_name;
    }

    public void setCheckpoint_name(String checkpoint_name) {
        this.checkpoint_name = checkpoint_name;
    }

    public String getHadiah() {
        return hadiah;
    }

    public void setHadiah(String hadiah) {
        this.hadiah = hadiah;
    }

    public String getChallenge_type_name() {
        return challenge_type_name;
    }

    public void setChallenge_type_name(String challenge_type_name) {
        this.challenge_type_name = challenge_type_name;
    }
}
