package praktikum.develops.a11414001.digitourapplication.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.realm.Realm;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerLeaderboard;
import praktikum.develops.a11414001.digitourapplication.model.ModelLeaderboard;
import praktikum.develops.a11414001.digitourapplication.response.ResponseLeaderboard;
import praktikum.develops.a11414001.digitourapplication.service.DigitourAPI;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener  {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    ImageView btnImageShareFacebook;
    ImageView btnPlay, btnAbout, btnLeaderboard, btnAchievement;
    //facebook login
    public CallbackManager callbackManager;
    public LoginManager loginManager;
    ShareDialog shareDialog;

    DigitourAPI digitourAPI;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private AdView mAdView;
    Realm realm;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            realm = Realm.getDefaultInstance();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        facebookSDKInitialize();

        setContentView(R.layout.activity_main);
        shareDialog = new ShareDialog(this);
        btnImageShareFacebook = (ImageView) findViewById(R.id.btnImageShareFacebook);
        btnImageShareFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent content = new ShareLinkContent.Builder().setContentTitle("Share Application")
                            .setContentDescription("Ini Adalah Aplikasi Digitour Challenge.")
                            .setContentUrl(Uri.parse("https://developers.facebook.com"))
                            .build();
                    shareDialog.show(content);

                }
            }
        });
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        btnAchievement = (ImageView) findViewById(R.id.imageViewAchievement);
        btnAchievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AchievementActivity.class);
                startActivity(i);
            }
        });

        btnLeaderboard = (ImageView) findViewById(R.id.imageViewLeaderboard);
        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                consumeLeaderboard();
                startActivity(intent);
            }
        });

        btnPlay = (ImageView) findViewById(R.id.imageViewPlay);
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(MainActivity.this, "Ini Email = "+mFirebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                    if (mFirebaseUser == null) {
                        Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                        startActivity(intent);
                    } else if(mFirebaseUser != null){
                        Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                        startActivity(intent);
                    }
                }
            });

        btnAbout =(ImageView) findViewById(R.id.imageViewAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent1);
            }
        });

        this.registerReceiver(this.myBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    //BroadcastUntuk baterai
    public BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            if (level == 100)
                Toast.makeText(context, "Battery Full", Toast.LENGTH_LONG).show();
            else if (level < 10)
                Toast.makeText(context, "Battery Low", Toast.LENGTH_LONG).show();
        }
    };

    protected void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1: {
                Intent intentProfil = new Intent(this, ProfilActivity.class);
                startActivity(intentProfil);
                Toast.makeText(getApplicationContext(), "Anda memilih menu profil",Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void showInfo(View view){
        Intent iInfo = new Intent(getApplicationContext(), InformasiActivity.class);
        startActivity(iInfo);
        finish();
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void consumeLeaderboard(){
        final ProgressDialog progress = new ProgressDialog(MainActivity.this);
        progress.setTitle(getString(R.string.app_name));
        progress.setMessage("Inisialisasi Data...");
        progress.show();
        Callback<ResponseLeaderboard> leaderboardCallback = new Callback<ResponseLeaderboard>() {
            //
            @Override
            public void onResponse(Call<ResponseLeaderboard> call, Response<ResponseLeaderboard> response) {
                if (response.isSuccessful()) {
                    List<ModelLeaderboard> lmodelLeaderboard = response.body().getAllLeaderboard();
                    int jumlahData = response.body().getAllLeaderboard().size();

                    if (jumlahData > 0) {
                        ControllerLeaderboard cl = new ControllerLeaderboard();
                        cl.deleteData();

                        for (int y = 0; y < jumlahData; y++) {
                            ModelLeaderboard tmp = lmodelLeaderboard.get(y);
                            cl.insertData(tmp.getLeaderboard_id(), tmp.getChallenge_type_name(), tmp.getCheckpoint_name(), tmp.getUser(),tmp.getScore(), tmp.getDate_time());
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "ERRORCUIIIII" + response.message().toString(), Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseLeaderboard> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };


        digitourAPI = RESTClient.get();
        Call<ResponseLeaderboard> call= digitourAPI.getHasilLeaderboard();
        call.enqueue(leaderboardCallback);

    }

    //Pertanyaan dialog keluar game
    public void onBackPressed(){
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        MainActivity.this.finish();
    }

}

