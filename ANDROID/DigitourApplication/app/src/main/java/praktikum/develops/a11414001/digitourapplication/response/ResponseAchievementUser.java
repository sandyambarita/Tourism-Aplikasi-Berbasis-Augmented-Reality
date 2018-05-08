package praktikum.develops.a11414001.digitourapplication.response;

import java.util.List;

import praktikum.develops.a11414001.digitourapplication.model.ModelAchievementUser;

/**
 * Created by Sandy on 6/8/2017.
 */

public class ResponseAchievementUser {
    public String error;
    public List<ModelAchievementUser> allAchievementUser;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ModelAchievementUser> getAllAchievementUser() {
        return allAchievementUser;
    }

    public void setAllAchievementUser(List<ModelAchievementUser> allAchievementUser) {
        this.allAchievementUser = allAchievementUser;
    }
}
