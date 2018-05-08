package praktikum.develops.a11414001.digitourapplication.response;

import java.util.List;

import praktikum.develops.a11414001.digitourapplication.model.ModelAbout;
import praktikum.develops.a11414001.digitourapplication.model.ModelLeaderboard;

/**
 * Created by Sandy on 6/6/2017.
 */

public class ResponseLeaderboard {
    public String error;
    public List<ModelLeaderboard> allLeaderboard;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ModelLeaderboard> getAllLeaderboard() {
        return allLeaderboard;
    }

    public void setAllLeaderboard(List<ModelLeaderboard> allLeaderboard) {
        this.allLeaderboard = allLeaderboard;
    }
}
