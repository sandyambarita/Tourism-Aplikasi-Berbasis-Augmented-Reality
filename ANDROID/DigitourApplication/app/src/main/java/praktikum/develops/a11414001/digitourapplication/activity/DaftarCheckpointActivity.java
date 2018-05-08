package praktikum.develops.a11414001.digitourapplication.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.adapter.CheckpointAdapter;
import praktikum.develops.a11414001.digitourapplication.adapter.LokasiAdapter;
import praktikum.develops.a11414001.digitourapplication.controller.ControllerCheckpoint;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;
import praktikum.develops.a11414001.digitourapplication.model.ModelLocation;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilCheckpoint;
import praktikum.develops.a11414001.digitourapplication.service.DigitourAPI;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarCheckpointActivity extends AppCompatActivity {
    Realm realm;

    Context myContext;

    public int LocationId;

    RecyclerView recyclerView = null;
    private CheckpointAdapter checkpointAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_checkpoint);

        Intent intent = getIntent();
        LocationId = Integer.valueOf(intent.getStringExtra("locationId"));
        //Toast.makeText(getApplicationContext(), "Hasil lokasi"+LocationId, Toast.LENGTH_SHORT).show();
        myContext = getApplicationContext();

        realm = Realm.getDefaultInstance();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_checkpoint);
        //Toast.makeText(myContext, "Hasil lokasi"+LocationId, Toast.LENGTH_SHORT).show();

        setUpRecyclerView();
    }



    private void setUpRecyclerView(){
        RealmResults<ModelCheckpoint> hasil= realm.where(ModelCheckpoint.class).equalTo("location_id",LocationId).findAll();

        checkpointAdapter= new CheckpointAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(checkpointAdapter);
        recyclerView.setHasFixedSize(true);

    }
}
