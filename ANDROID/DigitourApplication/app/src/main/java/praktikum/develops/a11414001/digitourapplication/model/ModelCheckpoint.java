package praktikum.develops.a11414001.digitourapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sandy on 4/28/2017.
 */

public class ModelCheckpoint extends RealmObject
{
    @PrimaryKey
    int checkpoint_id;

    int location_id;
    String checkpoint_name;
    float latitude;
    float longitude;
    String path_gambarpoint;
    String description;
    public ModelCheckpoint(){

    }
    public ModelCheckpoint(int checkpoint_id, int location_id, String checkpoint_name, float latitude, float longitude, String path_gambarpoint, String description) {
        this.checkpoint_id = checkpoint_id;
        this.location_id = location_id;
        this.checkpoint_name = checkpoint_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.path_gambarpoint = path_gambarpoint;
        this.description = description;
    }

    public int getCheckpoint_id() {
        return checkpoint_id;
    }

    public void setCheckpoint_id(int checkpoint_id) {
        this.checkpoint_id = checkpoint_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getCheckpoint_name() {
        return checkpoint_name;
    }

    public void setCheckpoint_name(String checkpoint_name) {
        this.checkpoint_name = checkpoint_name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getPath_gambarpoint() {
        return path_gambarpoint;
    }

    public void setPath_gambarpoint(String path_gambarpoint) {
        this.path_gambarpoint = path_gambarpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ModelCheckpoint{" +
                "checkpoint_id=" + checkpoint_id +
                ", location_id=" + location_id +
                ", checkpoint_name='" + checkpoint_name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", path_gambarpoint='" + path_gambarpoint + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
