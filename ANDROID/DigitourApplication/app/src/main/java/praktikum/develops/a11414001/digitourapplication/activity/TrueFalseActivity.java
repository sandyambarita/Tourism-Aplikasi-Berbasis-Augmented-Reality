package praktikum.develops.a11414001.digitourapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.model.ModelChallengeType;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;
import praktikum.develops.a11414001.digitourapplication.model.ModelQuestion;
import praktikum.develops.a11414001.digitourapplication.response.ResponseLeaderboard;
import praktikum.develops.a11414001.digitourapplication.service.DigitourAPI;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrueFalseActivity extends AppCompatActivity {

    Realm realm;
    private Button optionFalse, optionTrue, next, finish, prev;
    private TextView question;
    private RealmResults<ModelQuestion> soals ;
    private RealmList<ModelQuestion> listSoal;
    private static int nomorSoal;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private int ChallengeTypeId=0;
    private int CheckpointID;

    private float score = 0;

    private static int QuestionId = 0;
    private static int tempNomor = 0;
    int tempCheck;
    private int scoreMax;

    //mengambil email dan buat leaderboard
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    ProgressDialog progress;
    String user;
    String challenge_type_name;
    String checkpoint_name;
    DigitourAPI digitourAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);

        //mengambil email dari firebase
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        Intent intent = getIntent();
        CheckpointID = Integer.valueOf(intent.getStringExtra("checkpointId"));
        ChallengeTypeId = Integer.valueOf(intent.getStringExtra("challengeTypeId"));
        QuestionId = Integer.valueOf(intent.getStringExtra("questionID"));
        tempNomor = Integer.valueOf(intent.getStringExtra("noSoal"));

        pref = getSharedPreferences("mypref", MODE_PRIVATE);

        realm = Realm.getDefaultInstance();

//        realm.beginTransaction();
//        ModelCheckpoint tempModel = realm.where(ModelCheckpoint.class).equalTo("checkpoint_name",CheckpointName).findFirst();
//        realm.commitTransaction();
        initializeView();
        try {
            soals = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("question_id", QuestionId).equalTo("no_soal",tempNomor).findAll();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        setRealmList();

        realm.beginTransaction();
        ModelQuestion nmtmp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("question_id", QuestionId).equalTo("no_soal",tempNomor).findFirst();

        realm.commitTransaction();
        tempNomor = nmtmp.getNo_soal();
        final RealmResults<ModelQuestion> checksoal = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).findAllSorted("no_soal", Sort.ASCENDING);
        tempCheck =checksoal.last().getNo_soal();
        if(tempNomor!=tempCheck&&tempNomor==1 ){
            finish.setEnabled(false);
            prev.setEnabled(false);
            updateSoal(nmtmp);
        }else if(tempNomor==tempCheck && tempNomor!=1){
            finish.setEnabled(true);
            prev.setEnabled(true);
            next.setEnabled(false);
            updateSoal(nmtmp);
        }else if(tempNomor==tempCheck && tempNomor==1){
            finish.setEnabled(true);
            prev.setEnabled(false);
            next.setEnabled(false);
            updateSoal(nmtmp);
        }
        else {
            finish.setEnabled(true);
            prev.setEnabled(true);
            next.setEnabled(true);
            updateSoal(nmtmp);
        }

        optionTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = pref.edit();
                editor.putString(String.valueOf(nomorSoal), "TRUE");
                editor.commit();
                optionTrue.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                optionFalse.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);

            }
        });

        optionFalse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                editor = pref.edit();
                editor.putString(String.valueOf(nomorSoal), "FALSE");
                editor.commit();
                optionFalse.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                optionTrue.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextSoal();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prevSoal();
            }
        });


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double rumus;
                score=0;
                RealmResults<ModelQuestion> hasil = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).findAll();
                for(int a=0;a<hasil.size();a++){
                    String jwbtmp = hasil.get(a).getAnswer();
                    String jwbn= pref.getString(String.valueOf(a+1), "-");

                    if(jwbtmp.equalsIgnoreCase(jwbn)){
                        score++;
                    }
                    //Toast.makeText(TrueFalseActivity.this, "jwbn dipilih= "+jwbn+"hasil ="+hasil.size()+"jwbtmp benar= "+jwbtmp, Toast.LENGTH_SHORT).show();
                }
                rumus= ((score/hasil.size())*100);
                scoreMax = (int)rumus;
                Intent intentScore = new Intent(getApplicationContext(), ScoreActivity.class);
                try{
                    setLeaderboard();
                }catch (Exception e){
                    Toast.makeText(TrueFalseActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                intentScore.putExtra("score",String.valueOf(scoreMax));
                intentScore.putExtra("checkpointID", String.valueOf(CheckpointID));
                intentScore.putExtra("type", String.valueOf(ChallengeTypeId));
                startActivity(intentScore);
                finish();
            }
        });
    }

    private void setLeaderboard(){
        progress = ProgressDialog.show(TrueFalseActivity.this, "Initialisasi Data", "Sedang Diproses", true);

        final Callback<ResponseLeaderboard> userCallback = new Callback<ResponseLeaderboard>() {

            @Override
            public void onResponse(Call<ResponseLeaderboard> call, Response<ResponseLeaderboard> response) {
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseLeaderboard> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL"+t.getMessage(), Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        };
        try {
            //mengambil date_time sewaktu di finish
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => "+c.getTime());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date_time= df.format(c.getTime());

            ModelCheckpoint modelCheckpoint = realm.where(ModelCheckpoint.class).equalTo("checkpoint_id", CheckpointID).findFirst();
            ModelChallengeType modelChallengeType = realm.where(ModelChallengeType.class).equalTo("challenge_type_id", ChallengeTypeId).findFirst();
            user = mFirebaseUser.getDisplayName();
            checkpoint_name = modelCheckpoint.getCheckpoint_name();
            challenge_type_name = modelChallengeType.getType_name();

            digitourAPI= RESTClient.get();
            Call<ResponseLeaderboard> leaderboardCall= digitourAPI.tambahLeaderboard(challenge_type_name, checkpoint_name, user, scoreMax, date_time);
            leaderboardCall.enqueue(userCallback);

        }catch (Exception e){
            Log.e("Error : ", e.getMessage());
        }

    }
    private  void initializeView(){
        optionTrue =(Button) findViewById(R.id.optiontrue);
        optionFalse = (Button) findViewById(R.id.optionfalse );
        question = (TextView) findViewById(R.id.question);
        next = (Button) findViewById(R.id.btnNext);
        prev = (Button) findViewById(R.id.btnPrev);
        finish = (Button) findViewById(R.id.btnFinish);
    }
    private void setRealmList(){
        listSoal = new RealmList<ModelQuestion>();
        listSoal.addAll(soals.subList(0, soals.size()));
    }

    private void nextSoal(){
        tempNomor = tempNomor+1;
        if(tempNomor>0 && tempNomor<tempCheck){
            realm.beginTransaction();
            ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
            realm.commitTransaction();
            updateSoal(mqtemp);
            prev.setEnabled(true);
        }
        else if(tempNomor>0&&tempNomor==tempCheck){
            realm.beginTransaction();
            ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
            realm.commitTransaction();
            updateSoal(mqtemp);
            prev.setEnabled(true);
            finish.setEnabled(true);
            next.setEnabled(false);
        }else{
            realm.beginTransaction();
            ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
            realm.commitTransaction();
            updateSoal(mqtemp);
        }
    }
    private void prevSoal(){
        tempNomor = tempNomor-1;
        if(tempNomor<=1) {
            //tempNomor=1;
            realm.beginTransaction();
            ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
            realm.commitTransaction();
            updateSoal(mqtemp);
            prev.setEnabled(false);
            next.setEnabled(true);

        }else if(tempNomor>1){
            realm.beginTransaction();
            ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
            realm.commitTransaction();
            updateSoal(mqtemp);
            next.setEnabled(true);
        }else{
            realm.beginTransaction();
            ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
            realm.commitTransaction();
            updateSoal(mqtemp);
        }

    }

    private void updateSoal(ModelQuestion soal0){
        setBtnViewToDefault();
        nomorSoal = soal0.getNo_soal();

        String jwbn= pref.getString(String.valueOf(nomorSoal), "-");
        if(jwbn == "TRUE" ){
            optionTrue.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        }else if(jwbn == "FALSE"){
            optionFalse.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        }
        question.setText(soal0.getQuestion());
        optionTrue.setText(soal0.getOption1());
        optionFalse.setText(soal0.getOption2());


    }
    private void setBtnViewToDefault(){
        optionTrue.getBackground().clearColorFilter();
        optionFalse.getBackground().clearColorFilter();
    }


    @Override
    public boolean onSupportNavigateUp() {
        Intent previousScreen = new Intent(getApplicationContext(), ChallangeTypeActivity.class);
        startActivity(previousScreen);
        TrueFalseActivity.this.finish();
        return true;
    }


}

