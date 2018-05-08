package praktikum.develops.a11414001.digitourapplication.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.model.ModelLocation;

/**
 * Created by Sandy on 4/24/2017.
 */

public class ControllerLocation {

    Realm realm;

    public ControllerLocation() {
        try{
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
    public void insertData(final int location_id, final String location_name, final String city_name, final String path_gambar){
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ModelLocation location1 = realm.createObject(ModelLocation.class, location_id);
                    location1.setLocation_name(location_name);
                    location1.setCity_name(city_name);
                    location1.setPath_gambar(path_gambar);
                }
            });
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<ModelLocation> lokasi = realm.where(ModelLocation.class).findAll();
            lokasi.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

}

