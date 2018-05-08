package praktikum.develops.a11414001.digitourapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sandy on 5/10/2017.
 */

public class ModelChallengeType extends RealmObject {
    @PrimaryKey
    private int challenge_type_id;

    private String type_name;
    private String description;

    public ModelChallengeType(){}

    public ModelChallengeType(int challenge_type_id, String type_name, String description) {
        this.challenge_type_id = challenge_type_id;
        this.type_name = type_name;
        this.description = description;
    }

    public int getChallenge_type_id() {
        return challenge_type_id;
    }

    public void setChallenge_type_id(int challenge_type_id) {
        this.challenge_type_id = challenge_type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

