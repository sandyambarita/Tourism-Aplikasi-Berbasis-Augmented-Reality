package praktikum.develops.a11414001.digitourapplication.response;

import java.util.List;

import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;


/**
 * Created by Sandy on 4/28/2017.
 */

public class ResponseHasilCheckpoint {
    public String error;
    public List<ModelCheckpoint> allCheckpoint;
    public String isError() {
        return error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ModelCheckpoint> getAllCheckpoint() {
        return allCheckpoint;
    }

    public void setAllCheckpoint(List<ModelCheckpoint> allCheckpoint) {
        this.allCheckpoint = allCheckpoint;
    }
}

