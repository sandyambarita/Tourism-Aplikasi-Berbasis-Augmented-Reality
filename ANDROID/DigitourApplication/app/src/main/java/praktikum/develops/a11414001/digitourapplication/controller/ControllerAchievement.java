package praktikum.develops.a11414001.digitourapplication.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.model.ModelAchievement;

/**
 * Created by Sandy on 6/7/2017.
 */

public class ControllerAchievement {

    Realm realm;

    public ControllerAchievement(){
        try {
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void insertData(final int achievement_id,final String checkpoint_name,final String type_name ,final String hadiah, final String path_gambar)
    {
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ModelAchievement modelAchievement= realm.createObject(ModelAchievement.class, achievement_id);
                    modelAchievement.setCheckpoint_name(checkpoint_name);
                    modelAchievement.setType_name(type_name);
                    modelAchievement.setHadiah(hadiah);
                    modelAchievement.setPath_gambar(path_gambar);
                }
            });
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<ModelAchievement> achievement= realm.where(ModelAchievement.class).findAll();
            achievement.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
}

