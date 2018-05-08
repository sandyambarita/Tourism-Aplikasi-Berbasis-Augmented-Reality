package praktikum.develops.a11414001.digitourapplication.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerAbout;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerAchievement;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerChallengeType;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerCheckpoint;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerLocation;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerQuestion;
import praktikum.develops.a11414001.digitourapplication.model.ModelAbout;
import praktikum.develops.a11414001.digitourapplication.model.ModelAchievement;
import praktikum.develops.a11414001.digitourapplication.model.ModelChallengeType;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;
import praktikum.develops.a11414001.digitourapplication.model.ModelLocation;
import praktikum.develops.a11414001.digitourapplication.model.ModelQuestion;
import praktikum.develops.a11414001.digitourapplication.response.ResponseAchievement;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilAbout;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilChallengeType;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilCheckpoint;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilLocation;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilQuestion;
import praktikum.develops.a11414001.digitourapplication.service.DigitourAPI;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashscreenActivity extends AppCompatActivity {

    Context myContext;
    ProgressDialog progress;
    DigitourAPI digitourapi;


    public int CheckpointId;
    public int ChallangeTypeId;

    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new RequestData().execute();

        myContext = getApplicationContext();
        //progress = ProgressDialog.show(SplashscreenActivity.this, "Inisialisasi Data", "Sedang Mengunduh Data Untuk Aplikasi", true);


        try{
            realm = Realm.getDefaultInstance();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },3000);
        //animation
        ImageView image = (ImageView)findViewById(R.id.imageView2);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);
        image.startAnimation(animation1);
    }

    private class RequestData extends AsyncTask<Void, Void, String>{
        private ProgressDialog progress = null;
        public RequestData(){

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(SplashscreenActivity.this);
            progress.setTitle("DigitourAPP");
            progress.setMessage("Menghubungkan Ke server ...");
            progress.show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = "false";
            boolean cekNetwork = isNetworkAvailable();
            if(cekNetwork){
                result = "true";
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progress.dismiss();

            if(result.compareTo("false")==0){
                Toast.makeText(SplashscreenActivity.this, "Gagal Menghubungkan ke server", Toast.LENGTH_SHORT).show();
            }else{
                consumeQuestion();
                consumeChallengeType();
                consumeChallengeType();
                getDataCheckpoint();
                consumeLokasi();
                consumeAbout();
                consumeAchievement();
            }
        }
    }

    public void consumeAbout(){
        final ProgressDialog progress = new ProgressDialog(SplashscreenActivity.this);
        progress.setTitle(getString(R.string.app_name));
        progress.setMessage("Inisialisasi Data...");
        progress.show();
        Callback<ResponseHasilAbout> aboutCallback = new Callback<ResponseHasilAbout>() {
            //
            @Override
            public void onResponse(Call<ResponseHasilAbout> call, Response<ResponseHasilAbout> response) {
                if (response.isSuccessful()) {
                    List<ModelAbout> lmodelabout = response.body().getAllAbout();
                    int jumlahData = response.body().getAllAbout().size();

                    if (jumlahData > 0) {
                        ControllerAbout cl = new ControllerAbout();
                        cl.deleteData();

                        for (int y = 0; y < jumlahData; y++) {
                            ModelAbout tmpAbout = lmodelabout.get(y);
                            cl.insertData(tmpAbout.getAbout_id(), tmpAbout.getName(), tmpAbout.getNim(), tmpAbout.getProdi(), tmpAbout.getPath_gambar());
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "ERRORCUIIIII" + response.message().toString(), Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseHasilAbout> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };


        digitourapi = RESTClient.get();
        Call<ResponseHasilAbout> callAbout = digitourapi.getHasilAbout();
        callAbout.enqueue(aboutCallback);

    }

    public void consumeAchievement(){
        final ProgressDialog progress = new ProgressDialog(SplashscreenActivity.this);
        progress.setTitle(getString(R.string.app_name));
        progress.setMessage("Inisialisasi Data...");
        progress.show();
        Callback<ResponseAchievement> leaderboardCallback = new Callback<ResponseAchievement>() {
            //
            @Override
            public void onResponse(Call<ResponseAchievement> call, Response<ResponseAchievement> response) {
                if (response.isSuccessful()) {
                    List<ModelAchievement> lmodelLeaderboard = response.body().getAllAchievement();
                    int jumlahData = response.body().getAllAchievement().size();

                    if (jumlahData > 0) {
                        ControllerAchievement cl = new ControllerAchievement();
                        cl.deleteData();

                        for (int y = 0; y < jumlahData; y++) {
                            ModelAchievement tmp = lmodelLeaderboard.get(y);
                            cl.insertData(tmp.getAchievement_id(), tmp.getCheckpoint_name(),tmp.getType_name() ,tmp.getHadiah(), tmp.getPath_gambar());
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "ERRORCUIIIII" + response.message().toString(), Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseAchievement> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };


        digitourapi = RESTClient.get();
        Call<ResponseAchievement> call= digitourapi.getHasilAchievement();
        call.enqueue(leaderboardCallback);

    }

public void consumeLokasi() {
    final ProgressDialog progress = new ProgressDialog(SplashscreenActivity.this);
    progress.setTitle(getString(R.string.app_name));
    progress.setMessage("Inisialisasi Data...");
    progress.show();
    Callback<ResponseHasilLocation> locationCallback = new Callback<ResponseHasilLocation>() {
        //
        @Override
        public void onResponse(Call<ResponseHasilLocation> call, Response<ResponseHasilLocation> response) {
            if (response.isSuccessful()) {
                List<ModelLocation> lmodellocation = response.body().getAllLocation();
                int jumlahData = response.body().getAllLocation().size();

                if (jumlahData > 0) {
                    ControllerLocation cl = new ControllerLocation();
                    cl.deleteData();

                    for (int y = 0; y < jumlahData; y++) {
                        ModelLocation tmpLocation = lmodellocation.get(y);
                        cl.insertData(tmpLocation.getLocation_id(), tmpLocation.getLocation_name(), tmpLocation.getCity_name(), tmpLocation.getPath_gambar());
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "ERRORCUIIIII" + response.message().toString(), Toast.LENGTH_SHORT).show();
            }
            progress.dismiss();
        }

        @Override
        public void onFailure(Call<ResponseHasilLocation> call, Throwable t) {
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };


    digitourapi = RESTClient.get();
    Call<ResponseHasilLocation> callSoal = digitourapi.getHasillocation();
    callSoal.enqueue(locationCallback);

}
    public void consumeQuestion(){
        final ProgressDialog progress = new ProgressDialog(SplashscreenActivity.this);
        progress.setTitle(getString(R.string.app_name));
        progress.setMessage("Inisialisasi Data...");
        progress.show();
        Callback<ResponseHasilQuestion> questionCallback = new Callback<ResponseHasilQuestion>() {
            //
            @Override
            public void onResponse(Call<ResponseHasilQuestion> call, Response<ResponseHasilQuestion> response) {
                if(response.isSuccessful()){
                    List<ModelQuestion> lmodelquestion= response.body().getAllQuestion();
                    int jumlahData = response.body().getAllQuestion().size();

                    if(jumlahData > 0){
                        ControllerQuestion cl = new ControllerQuestion();
                        cl.deleteData();

                        for (int y=0; y<jumlahData; y++){
                            ModelQuestion tmp= lmodelquestion.get(y);
                            cl.insertData(tmp.getCheckpoint_id(), tmp.getChallenge_type_id(), tmp.getQuestion_id(),tmp.getNo_soal(), tmp.getQuestion(), tmp.getOption1(), tmp.getOption2(), tmp.getOption3(), tmp.getOption4(), tmp.getAnswer());
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "ERRORCUIIIII"+response.message().toString(), Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseHasilQuestion> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        digitourapi= RESTClient.get();
        Call<ResponseHasilQuestion> callSoal = digitourapi.getHasilQuestion();
        callSoal.enqueue(questionCallback);
    }

    public void consumeChallengeType(){
        final ProgressDialog progress = new ProgressDialog(SplashscreenActivity.this);
        progress.setTitle(getString(R.string.app_name));
        progress.setMessage("Inisialisasi Data...");
        progress.show();
        Callback<ResponseHasilChallengeType> challengeCallback = new Callback<ResponseHasilChallengeType>() {
            //
            @Override
            public void onResponse(Call<ResponseHasilChallengeType> call, Response<ResponseHasilChallengeType> responseHasilChallengeTypeResponse) {
                if(responseHasilChallengeTypeResponse.isSuccessful()){
                    List<ModelChallengeType> lmodelchallangetype= responseHasilChallengeTypeResponse.body().getAllChallengeType();
                    int jumlahData = responseHasilChallengeTypeResponse.body().getAllChallengeType().size();

                    if(jumlahData > 0){
                        ControllerChallengeType cl = new ControllerChallengeType();
                        cl.deleteData();

                        for (int y=0; y<jumlahData; y++){
                            ModelChallengeType tmp= lmodelchallangetype.get(y);
                            cl.insertData(tmp.getChallenge_type_id(),  tmp.getType_name(), tmp.getDescription());
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "ERRORCUIIIII"+responseHasilChallengeTypeResponse.message().toString(), Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseHasilChallengeType> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        digitourapi= RESTClient.get();
        Call<ResponseHasilChallengeType> callChallenge= digitourapi.getHasilChallenge();
        callChallenge.enqueue(challengeCallback );
    }

    private void getDataCheckpoint(){
        final ProgressDialog progress = new ProgressDialog(SplashscreenActivity.this);
        progress.setTitle(getString(R.string.app_name));
        progress.setMessage("Inisialisasi Data...");
        progress.show();
        //RESTClient.get().getHasilCheckpoint(LocationId).enqueue(new Callback<ResponseHasilCheckpoint>() {
        Callback<ResponseHasilCheckpoint> checkpointCallback = new Callback<ResponseHasilCheckpoint>(){
            @Override
            public void onResponse(Call<ResponseHasilCheckpoint> call, Response<ResponseHasilCheckpoint> response) {
                //Toast.makeText(getApplicationContext(), "Hasil"+response.body().getAllCheckpoint().size(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    List<ModelCheckpoint> lmodelcheckpoint = response.body().getAllCheckpoint();
                    int jumlahData = response.body().getAllCheckpoint().size();

                    if (jumlahData > 0) {
                        ControllerCheckpoint cl = new ControllerCheckpoint();
                        cl.deleteData();

                        for (int y = 0; y < jumlahData; y++) {
                            ModelCheckpoint tmpCheckpoint = lmodelcheckpoint.get(y);
                            cl.insertData(tmpCheckpoint.getCheckpoint_id(), tmpCheckpoint.getLocation_id(),
                                    tmpCheckpoint.getCheckpoint_name(), tmpCheckpoint.getLatitude(), tmpCheckpoint.getLongitude(), tmpCheckpoint.getPath_gambarpoint(), tmpCheckpoint.getDescription());
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "ERRORCUIIIII" + response.message().toString(), Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseHasilCheckpoint> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        digitourapi = RESTClient.get();
        Call<ResponseHasilCheckpoint> callCheckpoint = digitourapi.getHasilCheckpoint();
        callCheckpoint.enqueue(checkpointCallback);
    }
    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
