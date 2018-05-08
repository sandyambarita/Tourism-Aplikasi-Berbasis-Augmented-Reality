package praktikum.develops.a11414001.digitourapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
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
import praktikum.develops.a11414001.digitourapplication.model.ModelLeaderboard;
import praktikum.develops.a11414001.digitourapplication.model.ModelQuestion;
import praktikum.develops.a11414001.digitourapplication.response.ResponseLeaderboard;
import praktikum.develops.a11414001.digitourapplication.service.DigitourAPI;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PilihanGandaActivity extends AppCompatActivity {
    Realm realm;

    //share
    public CallbackManager callbackManager;
    public LoginManager loginManager;
    ShareDialog shareDialog;


    private Button option1, option2, option3, option4, next, finish, prev,btn5050;
    private TextView  question;
    private RealmResults<ModelQuestion> soals;
    private RealmList<ModelQuestion> listSoal;
    RealmResults<ModelLeaderboard> modelLeaderboards;
    private int ChallengeTypeId=0;
    public static int CheckpointID=0;
    private static int QuestionId = 0;
    private static int tempNomor = 0;
    int tempCheck;
    private static int nomorSoal;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private float score = 0;
    private int scoreMax;

    //membuat bantuan 0 atau satu
    public static int bantuanfound= 0;
    public static int checkbantuan  =0;

    //ask bantuan
    public static int bantuanAsk= 0;
    public static int checkbantuanAsk=0;

    //mengambil email dan buat leaderboard
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    ProgressDialog progress;
    String user;
    String challenge_type_name;
    String checkpoint_name;
    DigitourAPI digitourAPI;

    //untuk previosscreen
    private final String KEY_NAME = "name";
    String savedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_ganda);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        pref = getSharedPreferences("mypref", MODE_PRIVATE);

        //mengambil email dari firebase
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        final Intent intent = getIntent();
        CheckpointID = Integer.valueOf(intent.getStringExtra("checkpointId"));
        ChallengeTypeId = Integer.valueOf(intent.getStringExtra("challengeTypeId"));
        QuestionId = Integer.valueOf(intent.getStringExtra("questionID"));
        tempNomor = Integer.valueOf(intent.getStringExtra("noSoal"));

        pref = getSharedPreferences("mypref", MODE_PRIVATE);

        realm = Realm.getDefaultInstance();

        initializeView();
        try {
            soals = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("question_id", QuestionId).equalTo("no_soal",tempNomor).findAll();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        setRealmList();


        realm.beginTransaction();
        final ModelQuestion nmtmp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("question_id", QuestionId).equalTo("no_soal",tempNomor).findFirst();

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
//        if(iterator.hasNext()) nextSoal();
//        else gameOver();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = pref.edit();
                editor.putString(String.valueOf(nomorSoal), "1");
                editor.commit();
                option1.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                option2.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                option3.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                option4.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);


//                if (option1.getText().toString().equalsIgnoreCase(mAnswer)) correctAnswer();
//                else wrongAnswer(option1);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = pref.edit();
                editor.putString(String.valueOf(nomorSoal), "2");
                editor.commit();
                option2.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                option1.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                option3.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                option4.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor = pref.edit();
                editor.putString(String.valueOf(nomorSoal), "3");
                editor.commit();
//                if (option3.getText().toString().equalsIgnoreCase(mAnswer)) correctAnswer();
//                else wrongAnswer(option3);
                option3.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                option2.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                option1.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                option4.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = pref.edit();
                editor.putString(String.valueOf(nomorSoal), "4");
                editor.commit();
                option4.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                option2.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                option3.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                option1.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
            }
        });



        btn5050 = (Button) findViewById(R.id.btn5050);
        if(checkbantuan == CheckpointID){
            btn5050.setEnabled(false);
        }else{
            btn5050.setEnabled(true);
        }
        btn5050.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("BantuanFOund",""+bantuanfound+"checkbantuan"+checkbantuan+" chpoint id "+CheckpointID );
                if(bantuanfound ==0 || checkbantuan != CheckpointID) {
                    checkbantuan = CheckpointID;
                    btn5050.setEnabled(false);
                    bantuan(nmtmp);
                    bantuanfound=1;
                }else{
                    btn5050.setEnabled(false);
                }
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
                   // Toast.makeText(PilihanGandaActivity.this, "jwbn dipilih= "+jwbn+"hasil ="+hasil.size()+"jwbtmp benar= "+jwbtmp, Toast.LENGTH_SHORT).show();
                }
                rumus=((score/hasil.size())*100);
                Log.e("SCore = ",""+rumus+"hasil.size"+hasil.size());
                scoreMax = (int)rumus;
                Intent intentScore = new Intent(getApplicationContext(), ScoreActivity.class);
                try{
                    setLeaderboard();
                }catch (Exception e){
                    Toast.makeText(PilihanGandaActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                intentScore.putExtra("score",String.valueOf(scoreMax));
                intentScore.putExtra("checkpointID", String.valueOf(CheckpointID));
                intentScore.putExtra("type", String.valueOf(ChallengeTypeId));
                startActivity(intentScore);
                finish();
            }
        });

        facebookSDKInitialize();


        shareDialog = new ShareDialog(this);
        final Button btnShareAsk = (Button) findViewById(R.id.btnAsk);
        if(checkbantuanAsk == CheckpointID){
            btnShareAsk.setEnabled(false);
        }else{
            btnShareAsk.setEnabled(true);
        }
        btnShareAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbantuanAsk != CheckpointID) {
                    checkbantuanAsk = CheckpointID;
                    btnShareAsk.setEnabled(false);

                    if (ShareDialog.canShow(ShareLinkContent.class)) {
                        ShareLinkContent content = new ShareLinkContent.Builder().setContentTitle("Share Ask Friend")
                                .setContentDescription("Digitour Challange")
                                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                                .build();
                        shareDialog.show(content);

                    }

                    bantuanAsk=1;
                }else{
                    btnShareAsk.setEnabled(false);
                }

            }
        });

    }

    public void bantuan(ModelQuestion question){
        int answer1 = 1, answer2 = 2, answer3 = 3, answer4 = 4;

        setBtnViewToDefault();
        nomorSoal = question.getNo_soal();

        realm.beginTransaction();
        ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
        realm.commitTransaction();

        if(mqtemp.getAnswer().equalsIgnoreCase(String.valueOf(answer1))) {
            option2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            option2.setEnabled(false);
            option3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            option3.setEnabled(false);
            option1.setEnabled(true);
            option4.setEnabled(true);
        }else if(mqtemp.getAnswer().equalsIgnoreCase(String.valueOf(answer2))) {
            option4.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            option4.setEnabled(false);
            option3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            option3.setEnabled(false);
            option2.setEnabled(true);
            option1.setEnabled(true);
        }else if(mqtemp.getAnswer().equalsIgnoreCase(String.valueOf(answer3))) {
            option2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            option2.setEnabled(false);
            option1.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            option1.setEnabled(false);
            option3.setEnabled(true);
            option4.setEnabled(true);
        }else if(mqtemp.getAnswer().equalsIgnoreCase(String.valueOf(String.valueOf(answer4)))) {
            option2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            option2.setEnabled(false);
            option3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            option3.setEnabled(false);
            option1.setEnabled(true);
            option4.setEnabled(true);
        }
    }

    protected void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    private void setLeaderboard(){
        progress = ProgressDialog.show(PilihanGandaActivity.this, "Initialisasi Data", "Sedang Diproses", true);

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
        option1 =(Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);
        question = (TextView) findViewById(R.id.question);
        next = (Button) findViewById(R.id.btnNext);
        finish = (Button) findViewById(R.id.btnFinish);
        prev = (Button) findViewById(R.id.btnPrev);
    }

    private void setRealmList(){
        listSoal = new RealmList<ModelQuestion>();
        listSoal.addAll(soals.subList(0, soals.size()));
    }

    private void nextSoal(){
        tempNomor = tempNomor+1;
        if(tempNomor>0 && tempNomor<tempCheck){
            prev.setEnabled(true);
            realm.beginTransaction();
            ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
            realm.commitTransaction();
            updateSoal(mqtemp);
        }

        else if(tempNomor>0&&tempNomor==tempCheck){
            prev.setEnabled(true);
            finish.setEnabled(true);
            next.setEnabled(false);
            realm.beginTransaction();
            ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
            realm.commitTransaction();
            updateSoal(mqtemp);

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
            ModelQuestion mqtemp = realm.where(ModelQuestion.class).equalTo("checkpoint_id",CheckpointID ).equalTo("challenge_type_id",ChallengeTypeId).equalTo("no_soal",tempNomor).findFirst();
            realm.commitTransaction();
            updateSoal(mqtemp);
            next.setEnabled(true);
        }
    }

    private void updateSoal(ModelQuestion soal0){
        setBtnViewToDefault();
        nomorSoal = soal0.getNo_soal();

        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);

        String jwbn= pref.getString(String.valueOf(nomorSoal), "-");
        if(jwbn == "1" ){
            option1.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        }else if(jwbn == "2"){
            option2.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        }else if(jwbn == "3"){
            option3.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        }else if(jwbn == "4"){
            option4.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        }
        question.setText(soal0.getQuestion());
        option1.setText(soal0.getOption1());
        option2.setText(soal0.getOption2());
        option3.setText(soal0.getOption3());
        option4.setText(soal0.getOption4());


    }
    private void setBtnViewToDefault(){
        option1.getBackground().clearColorFilter();
        option2.getBackground().clearColorFilter();
        option3.getBackground().clearColorFilter();
        option4.getBackground().clearColorFilter();
    }


    @Override
    public boolean onSupportNavigateUp() {
        Intent previousScreen = new Intent(getApplicationContext(), GridViewPilihanActivity.class);
        realm.beginTransaction();
        ModelCheckpoint mqtemp = realm.where(ModelCheckpoint.class).equalTo("checkpoint_id",CheckpointID ).findFirst();
        realm.commitTransaction();
        previousScreen.putExtra("checkpointName", mqtemp.getCheckpoint_name());
        previousScreen.putExtra("challengeTypeId", String.valueOf(1));
        if(bantuanfound==1 && checkbantuan==CheckpointID){
            //btnBantuan tidak bisa digunakan lagi
            bantuanfound=1;
        }else{
            //btnBantuan bisa digunakan lagi
            bantuanfound=0;
        }

        if(bantuanAsk==1 && checkbantuanAsk==CheckpointID){
            //btnBantuan tidak bisa digunakan lagi
            bantuanAsk=1;
        }else{
            //btnBantuan bisa digunakan lagi
            bantuanAsk=0;
        }
        startActivity(previousScreen);
        return true;
    }


}
