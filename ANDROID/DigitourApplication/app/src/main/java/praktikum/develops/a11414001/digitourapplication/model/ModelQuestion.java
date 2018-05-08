package praktikum.develops.a11414001.digitourapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sandy on 5/7/2017.
 */

public class ModelQuestion extends RealmObject {
    @PrimaryKey
    private int question_id;

    private int challenge_type_id;
    private int checkpoint_id;
    private int no_soal;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    public ModelQuestion(){}

    public ModelQuestion(int question_id, int challenge_type_id, int checkpoint_id, int no_soal, String question, String option1, String option2, String option3, String option4, String answer) {
        this.question_id = question_id;
        this.challenge_type_id = challenge_type_id;
        this.checkpoint_id = checkpoint_id;
        this.no_soal = no_soal;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getChallenge_type_id() {
        return challenge_type_id;
    }

    public void setChallenge_type_id(int challenge_type_id) {
        this.challenge_type_id = challenge_type_id;
    }

    public int getCheckpoint_id() {
        return checkpoint_id;
    }

    public void setCheckpoint_id(int checkpoint_id) {
        this.checkpoint_id = checkpoint_id;
    }

    public int getNo_soal() {
        return no_soal;
    }

    public void setNo_soal(int no_soal) {
        this.no_soal = no_soal;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}