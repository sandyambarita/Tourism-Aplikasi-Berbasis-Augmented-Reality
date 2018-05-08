package praktikum.develops.a11414001.digitourapplication.response;

import java.util.List;

import praktikum.develops.a11414001.digitourapplication.model.ModelLocation;

/**
 * Created by Sandy on 4/20/2017.
 */

public class ResponseHasilLocation {
    public String error;
    public List <ModelLocation> allLocation;
    public String isError() {
        return error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ModelLocation> getAllLocation() {
        return allLocation;
    }

    public void setAllLocation(List<ModelLocation> allLocation) {
        this.allLocation = allLocation;
    }
}
