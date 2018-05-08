package praktikum.develops.a11414001.digitourapplication.response;

import java.util.List;

import praktikum.develops.a11414001.digitourapplication.model.ModelAbout;
import praktikum.develops.a11414001.digitourapplication.model.ModelChallengeType;

/**
 * Created by Sandy on 5/22/2017.
 */

public class ResponseHasilAbout {

    public String error;
    public List<ModelAbout> allAbout;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ModelAbout> getAllAbout() {
        return allAbout;
    }

    public void setAllAbout(List<ModelAbout> allAbout) {
        this.allAbout = allAbout;
    }
}
