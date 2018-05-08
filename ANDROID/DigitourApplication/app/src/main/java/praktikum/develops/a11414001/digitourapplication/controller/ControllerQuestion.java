package praktikum.develops.a11414001.digitourapplication.controller;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.model.ModelQuestion;

/**
 * Created by Sandy on 5/7/2017.
 */

public class ControllerQuestion {
    Realm realm;

    public ControllerQuestion() {
        try{
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
    public void insertData(final int checkpoint_id, final int challange_type_id,
                           final int question_id,final int no_soal, final String question, final String option1, final String option2, final String option3, final String option4, final String answer){
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ModelQuestion question1 = realm.createObject(ModelQuestion.class, question_id);
                    question1.setCheckpoint_id(checkpoint_id);
                    question1.setChallenge_type_id(challange_type_id);
                    question1.setNo_soal(no_soal);
                    question1.setQuestion(question);
                    question1.setOption1(option1);
                    question1.setOption2(option2);
                    question1.setOption3(option3);
                    question1.setOption4(option4);
                    question1.setAnswer(answer);

                }
            });
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteData(){
        try{
            realm.beginTransaction();
            RealmResults<ModelQuestion> question= realm.where(ModelQuestion.class).findAll();
            question.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

}


