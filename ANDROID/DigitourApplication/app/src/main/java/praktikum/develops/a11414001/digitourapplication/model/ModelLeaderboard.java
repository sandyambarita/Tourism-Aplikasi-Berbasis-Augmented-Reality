package praktikum.develops.a11414001.digitourapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sandy on 6/6/2017.
 */

public class ModelLeaderboard extends RealmObject {
    @PrimaryKey
    private int leaderboard_id;

    private String challenge_type_name;
    private String checkpoint_name;
    private String user;
    private int score;
    private String date_time;

    public int getLeaderboard_id() {
        return leaderboard_id;
    }

    public void setLeaderboard_id(int leaderboard_id) {
        this.leaderboard_id = leaderboard_id;
    }

    public String getChallenge_type_name() {
        return challenge_type_name;
    }

    public void setChallenge_type_name(String challenge_type_name) {
        this.challenge_type_name = challenge_type_name;
    }

    public String getCheckpoint_name() {
        return checkpoint_name;
    }

    public void setCheckpoint_name(String checkpoint_name) {
        this.checkpoint_name = checkpoint_name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
