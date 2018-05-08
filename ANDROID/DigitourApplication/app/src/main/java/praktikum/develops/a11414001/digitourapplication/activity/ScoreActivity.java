package praktikum.develops.a11414001.digitourapplication.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import io.realm.Realm;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.model.ModelAchievement;
import praktikum.develops.a11414001.digitourapplication.model.ModelChallengeType;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;
import praktikum.develops.a11414001.digitourapplication.response.ResponseAchievementUser;
import praktikum.develops.a11414001.digitourapplication.service.DigitourAPI;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreActivity extends AppCompatActivity {
    Realm realm;
    DigitourAPI digitourAPI;

    //share
    public CallbackManager callbackManager;
    public LoginManager loginManager;
    ShareDialog shareDialog;

    private int scoreMax;
    private TextView nilai;
    private int CheckpointID;
    private int type;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    ProgressDialog progress;
    String user;
    String tempCName;
    String tempHadiah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        realm = Realm.getDefaultInstance();

        final Intent intent = getIntent();
        type = Integer.valueOf(intent.getStringExtra("type"));
        CheckpointID = Integer.valueOf(intent.getStringExtra("checkpointID"));
        scoreMax = Integer.valueOf(intent.getStringExtra("score"));
        Log.e("Test = ",""+scoreMax);
        //Toast.makeText(this, "nilai score"+scoreMax, Toast.LENGTH_SHORT).show();
        nilai = (TextView) findViewById(R.id.nilaiscore);
        nilai.setText(""+scoreMax);


        Button btnScore = (Button) findViewById(R.id.btnNextScore);
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentScore = new Intent(getApplicationContext(), ChallangeTypeActivity.class);
                startActivity(intentScore);
                finish();
            }
        });

        Button btnHome= (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intentHome);
                finish();
            }
        });

        if(scoreMax >= 80){
            setAchievementUser();
            dialogAchievement();
        }else{
            Log.e("Yoooo", String.valueOf(scoreMax));
        }

        facebookSDKInitialize();

        shareDialog = new ShareDialog(this);
        Button btnShare = (Button) findViewById(R.id.btnShareScore);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent content = new ShareLinkContent.Builder().setContentTitle("Share Score")
                            .setContentDescription("Score yang di dapat : "+scoreMax)
                            .setContentUrl(Uri.parse("https://developers.facebook.com"))
                            .build();
                    shareDialog.show(content);

                }
            }
        });
    }

    protected void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    private void setAchievementUser(){
        progress = ProgressDialog.show(ScoreActivity.this, "Initialisasi Data", "Sedang Diproses", true);

        final Callback<ResponseAchievementUser> userCallback = new Callback<ResponseAchievementUser>() {

            @Override
            public void onResponse(Call<ResponseAchievementUser> call, Response<ResponseAchievementUser> response) {
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseAchievementUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL"+t.getMessage(), Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        };
        try {

            ModelCheckpoint modelCheckpoint = realm.where(ModelCheckpoint.class).equalTo("checkpoint_id",CheckpointID ).findFirst();
            ModelChallengeType modelChallengeType = realm.where(ModelChallengeType.class).equalTo("challenge_type_id", type).findFirst();
            ModelAchievement modelAchievement = realm.where(ModelAchievement.class).equalTo("checkpoint_name", modelCheckpoint.getCheckpoint_name()).equalTo("type_name", modelChallengeType.getType_name()).findFirst();
            user = mFirebaseUser.getDisplayName();
            tempCName = modelCheckpoint.getCheckpoint_name();
            tempHadiah = modelAchievement.getHadiah();

            digitourAPI= RESTClient.get();
            Call<ResponseAchievementUser> leaderboardCall= digitourAPI.tambahAchievementUser(user, tempCName, modelChallengeType.getType_name(),tempHadiah);
            leaderboardCall.enqueue(userCallback);

        }catch (Exception e){
            Log.e("Error : ", e.getMessage());
        }

    }

    public void dialogAchievement(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.dialog_achievement);
        alertDialogBuilder.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void onBackPressed(){
        Intent previousScreen = new Intent(getApplicationContext(), ChallangeTypeActivity.class);
        startActivity(previousScreen);
        ScoreActivity.this.finish();
    }
}
