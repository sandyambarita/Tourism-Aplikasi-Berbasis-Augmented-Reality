package praktikum.develops.a11414001.digitourapplication.response;

import java.util.List;

import praktikum.develops.a11414001.digitourapplication.model.ModelChallengeType;

/**
 * Created by Sandy on 5/10/2017.
 */

public class ResponseHasilChallengeType {
    public String error;
    public List<ModelChallengeType> allChallengeType;
    public String isError() {
        return error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ModelChallengeType> getAllChallengeType() {
        return allChallengeType;
    }

    public void setAllChallengeType(List<ModelChallengeType> allChallengeType) {
        this.allChallengeType = allChallengeType;
    }
}


