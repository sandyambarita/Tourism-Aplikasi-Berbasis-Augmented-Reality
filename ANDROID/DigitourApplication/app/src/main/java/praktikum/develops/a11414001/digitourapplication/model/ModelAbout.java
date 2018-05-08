package praktikum.develops.a11414001.digitourapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sandy on 5/22/2017.
 */

public class ModelAbout extends RealmObject {
    @PrimaryKey
    private int about_id;

    private String name;
    private int nim;
    private String prodi;
    private String path_gambar;

    public int getAbout_id() {
        return about_id;
    }

    public void setAbout_id(int about_id) {
        this.about_id = about_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getPath_gambar() {
        return path_gambar;
    }

    public void setPath_gambar(String path_gambar) {
        this.path_gambar = path_gambar;
    }
}
