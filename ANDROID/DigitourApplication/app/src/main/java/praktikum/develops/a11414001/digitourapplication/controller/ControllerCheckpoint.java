package praktikum.develops.a11414001.digitourapplication.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;

/**
 * Created by Sandy on 4/28/2017.
 */

public class ControllerCheckpoint {

    Realm realm;

    public ControllerCheckpoint(){
        try {
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void insertData(final int checkpoint_id, final int location_id, final String checkpoint_name, final float latitude, final float longitude, final String path_gambarpoint, final String description)
    {
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ModelCheckpoint modelCheckpoint = realm.createObject(ModelCheckpoint.class, checkpoint_id);
                    modelCheckpoint.setLocation_id(location_id);
                    modelCheckpoint.setCheckpoint_name(checkpoint_name);
                    modelCheckpoint.setLatitude(latitude);
                    modelCheckpoint.setLongitude(longitude);
                    modelCheckpoint.setPath_gambarpoint(path_gambarpoint);
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
            RealmResults<ModelCheckpoint> checkpoin = realm.where(ModelCheckpoint.class).findAll();
            checkpoin.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
}
