package praktikum.develops.a11414001.digitourapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.adapter.AchievementUserAdapter;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerAchievementUser;
import praktikum.develops.a11414001.digitourapplication.model.ModelAchievementUser;
import praktikum.develops.a11414001.digitourapplication.response.ResponseAchievementUser;
import praktikum.develops.a11414001.digitourapplication.service.DigitourAPI;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    DigitourAPI digitourAPI;

    public static final String ANONYMOUS = "anonymous";
    private GoogleApiClient mGoogleApiClient;

    private TextView Nama , Email;
    private ImageView fotoProfile;

    //Achievement buat user
    Realm realm;

    RecyclerView recyclerView = null;
    private AchievementUserAdapter achievementUserAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        // Initialize Firebase Auth

        realm = Realm.getDefaultInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        Nama = (TextView) findViewById(R.id.namaProfil);
        Email = (TextView) findViewById(R.id.emailProfil);
        fotoProfile = (ImageView) findViewById(R.id.gambarProfil);

        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            Nama.setText("Nama : "+mFirebaseUser.getDisplayName());
            Email.setText("E-mail : "+mFirebaseUser.getEmail());
            Glide.with(this).load(mFirebaseUser.getPhotoUrl()).into(fotoProfile);
        }

        Button btnLogout = (Button) findViewById(R.id.btnsign_out);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                Intent sign = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(sign);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_achievementuser);
        setUpRecyclerView();

        consumeAchievementUser();
    }

    private void setUpRecyclerView(){
        RealmResults<ModelAchievementUser> hasil= realm.where(ModelAchievementUser.class).equalTo("user", mFirebaseUser.getDisplayName()).findAll();

        achievementUserAdapter = new AchievementUserAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(achievementUserAdapter);
        recyclerView.setHasFixedSize(true);

    }

    public void consumeAchievementUser(){
        final ProgressDialog progress = new ProgressDialog(ProfilActivity.this);
        progress.setTitle(getString(R.string.app_name));
        progress.setMessage("Inisialisasi Data...");
        progress.show();
        Callback<ResponseAchievementUser> leaderboardCallback = new Callback<ResponseAchievementUser>() {
            //
            @Override
            public void onResponse(Call<ResponseAchievementUser> call, Response<ResponseAchievementUser> response) {
                if (response.isSuccessful()) {
                    List<ModelAchievementUser> lmodelLeaderboard = response.body().getAllAchievementUser();
                    int jumlahData = response.body().getAllAchievementUser().size();

                    if (jumlahData > 0) {
                        ControllerAchievementUser cl = new ControllerAchievementUser();
                        cl.deleteData();

                        for (int y = 0; y < jumlahData; y++) {
                            ModelAchievementUser tmp = lmodelLeaderboard.get(y);
                            cl.insertData(tmp.getAchievement_user_id(), tmp.getUser(), tmp.getCheckpoint_name(), tmp.getChallenge_type_name(), tmp.getHadiah());
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "ERRORCUIIIII" + response.message().toString(), Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseAchievementUser> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };


        digitourAPI = RESTClient.get();
        Call<ResponseAchievementUser> call= digitourAPI.getHasilAchievementUser();
        call.enqueue(leaderboardCallback);

    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent previousScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(previousScreen);
        ProfilActivity.this.finish();
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
