package praktikum.develops.a11414001.digitourapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sandy on 6/7/2017.
 */

public class ModelAchievement extends RealmObject {
    @PrimaryKey
    private int achievement_id;

    private String checkpoint_name;
    private String type_name;
    private String hadiah;
    private String path_gambar;

    public int getAchievement_id() {
        return achievement_id;
    }

    public void setAchievement_id(int achievement_id) {
        this.achievement_id = achievement_id;
    }

    public String getCheckpoint_name() {
        return checkpoint_name;
    }

    public void setCheckpoint_name(String checkpoint_name) {
        this.checkpoint_name = checkpoint_name;
    }

    public String getHadiah() {
        return hadiah;
    }

    public void setHadiah(String hadiah) {
        this.hadiah = hadiah;
    }

    public String getPath_gambar() {
        return path_gambar;
    }

    public void setPath_gambar(String path_gambar) {
        this.path_gambar = path_gambar;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
