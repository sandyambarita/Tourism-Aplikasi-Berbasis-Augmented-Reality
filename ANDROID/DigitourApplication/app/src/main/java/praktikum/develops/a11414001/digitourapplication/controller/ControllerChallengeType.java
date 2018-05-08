package praktikum.develops.a11414001.digitourapplication.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.model.ModelChallengeType;

/**
 * Created by Sandy on 5/10/2017.
 */

public class ControllerChallengeType {
    Realm realm;

    public ControllerChallengeType(){
        try {
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void insertData(final int challenge_type_id,final String type_name, final String description)
    {
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ModelChallengeType modelCheckpoint = realm.createObject(ModelChallengeType.class, challenge_type_id);
                    modelCheckpoint.setType_name(type_name);
                    modelCheckpoint.setDescription(description);
                }
            });
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<ModelChallengeType> checkpoin = realm.where(ModelChallengeType.class).findAll();
            checkpoin.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
}

