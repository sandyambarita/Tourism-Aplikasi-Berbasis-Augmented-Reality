package praktikum.develops.a11414001.digitourapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sandy on 4/25/2017.
 */

public class ModelLocation extends RealmObject {
    @PrimaryKey
    private int location_id;

    private String location_name;
    private String city_name;
    private String path_gambar;


    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getPath_gambar() {
        return path_gambar;
    }

    public void setPath_gambar(String path_gambar) {
        this.path_gambar = path_gambar;
    }

}
