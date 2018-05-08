package praktikum.develops.a11414001.digitourapplication.response;

import java.util.List;

import praktikum.develops.a11414001.digitourapplication.model.ModelQuestion;

/**
 * Created by Sandy on 5/7/2017.
 */

public class ResponseHasilQuestion {
    public String error;
    public List<ModelQuestion> allQuestion;
    public String isError() {
        return error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ModelQuestion> getAllQuestion() {
        return allQuestion;
    }

    public void setAllQuestion(List<ModelQuestion> allQuestion) {
        this.allQuestion = allQuestion;
    }
}

