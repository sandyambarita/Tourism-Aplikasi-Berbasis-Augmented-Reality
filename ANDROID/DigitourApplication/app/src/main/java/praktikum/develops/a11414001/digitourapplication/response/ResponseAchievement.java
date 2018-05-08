package praktikum.develops.a11414001.digitourapplication.response;

import java.util.List;

import praktikum.develops.a11414001.digitourapplication.model.ModelAchievement;

/**
 * Created by Sandy on 6/7/2017.
 */

public class ResponseAchievement {
    public String error;
    public List<ModelAchievement> allAchievement;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ModelAchievement> getAllAchievement() {
        return allAchievement;
    }

    public void setAllAchievement(List<ModelAchievement> allAchievement) {
        this.allAchievement = allAchievement;
    }
}
