package praktikum.develops.a11414001.digitourapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerCheckpoint;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilCheckpoint;
import praktikum.develops.a11414001.digitourapplication.service.DigitourAPI;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayActivity extends AppCompatActivity {

    Realm realm;

    ProgressDialog progress;
    DigitourAPI digitourapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btn = (Button) findViewById(R.id.buttonLokasi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), DaftarWisataActivity.class);
                startActivity(intent);
                finish();

            }
        });


        Button btnGo = (Button) findViewById(R.id.buttonGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    realm = Realm.getDefaultInstance();
//                   // progress = ProgressDialog.show(PlayActivity.this, "Inisialisasi Data", "Sedang Mengunduh Data Untuk Aplikasi", true);
//                    getDataCheckpoint();
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }

                Intent intentGo = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intentGo);
                finish();
            }
        });
    }
    private void getDataCheckpoint(){
        final ProgressDialog progress = new ProgressDialog(PlayActivity.this);
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

    @Override
    public boolean onSupportNavigateUp() {
        Intent previousScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(previousScreen);
        PlayActivity.this.finish();
        return true;
    }

    public void onBackPressed(){
        Intent previousScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(previousScreen);
        PlayActivity.this.finish();
    }
}
