package praktikum.develops.a11414001.digitourapplication.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.model.ModelLeaderboard;

/**
 * Created by Sandy on 6/6/2017.
 */

public class ControllerLeaderboard {
    Realm realm;

    public ControllerLeaderboard(){
        try {
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void insertData(final int leaderboard_id,final String challenge_type_name, final String checkpoint_name, final String user, final int score, final String date_time)
    {
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ModelLeaderboard modelLeaderboard= realm.createObject(ModelLeaderboard.class, leaderboard_id);
                    modelLeaderboard.setChallenge_type_name(challenge_type_name);
                    modelLeaderboard.setCheckpoint_name(checkpoint_name);
                    modelLeaderboard.setUser(user);
                    modelLeaderboard.setScore(score);
                    modelLeaderboard.setDate_time(date_time);
                }
            });
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<ModelLeaderboard> about = realm.where(ModelLeaderboard.class).findAll();
            about.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
}
