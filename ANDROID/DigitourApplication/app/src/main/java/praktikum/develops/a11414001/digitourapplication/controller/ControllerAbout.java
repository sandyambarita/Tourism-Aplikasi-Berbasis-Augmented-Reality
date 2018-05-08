package praktikum.develops.a11414001.digitourapplication.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.model.ModelAbout;
import praktikum.develops.a11414001.digitourapplication.model.ModelChallengeType;

/**
 * Created by Sandy on 5/22/2017.
 */

public class ControllerAbout {
    Realm realm;

    public ControllerAbout(){
        try {
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void insertData(final int about_id,final String name, final int nim, final String prodi, final String path_gambar)
    {
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ModelAbout modelAbout= realm.createObject(ModelAbout.class, about_id);
                    modelAbout.setName(name);
                    modelAbout.setNim(nim);
                    modelAbout.setProdi(prodi);
                    modelAbout.setPath_gambar(path_gambar);
                }
            });
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<ModelAbout> about = realm.where(ModelAbout.class).findAll();
            about.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
}
