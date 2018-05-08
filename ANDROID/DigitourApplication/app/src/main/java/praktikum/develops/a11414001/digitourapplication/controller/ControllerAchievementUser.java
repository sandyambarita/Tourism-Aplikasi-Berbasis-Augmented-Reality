package praktikum.develops.a11414001.digitourapplication.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.model.ModelAchievementUser;

/**
 * Created by Sandy on 6/8/2017.
 */

public class ControllerAchievementUser {
    Realm realm;

    public ControllerAchievementUser(){
        try {
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void insertData(final int achievement_user_id,final String user, final String checkpoint_name, final String challenge_type_name, final String hadiah)
    {
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ModelAchievementUser modelAchievement= realm.createObject(ModelAchievementUser.class, achievement_user_id);
                    modelAchievement.setUser(user);
                    modelAchievement.setCheckpoint_name(checkpoint_name);
                    modelAchievement.setChallenge_type_name(challenge_type_name);
                    modelAchievement.setHadiah(hadiah);
                }
            });
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<ModelAchievementUser> achievement= realm.where(ModelAchievementUser.class).findAll();
            achievement.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
}
