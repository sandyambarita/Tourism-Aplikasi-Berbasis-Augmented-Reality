package praktikum.develops.a11414001.digitourapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.adapter.AngkaPilAdapter;
import praktikum.develops.a11414001.digitourapplication.adapter.AutoFitGridLayoutManager;
import praktikum.develops.a11414001.digitourapplication.model.ModelChallengeType;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;
import praktikum.develops.a11414001.digitourapplication.model.ModelQuestion;

public class GridViewPilihanActivity extends AppCompatActivity {

    Realm realm;

    Context myContext;

    public int LocationId;

    RecyclerView recyclerView = null;
    private AngkaPilAdapter angkaPilAdapter;

    //Buat jadi gridView

    AutoFitGridLayoutManager gridLayoutManager;
    private final int DEFAULT_WIDTH = 125;

    private int ChallengeTypeId=0;
    ModelCheckpoint tempModel;
    ModelChallengeType type;
    private String CheckpointName;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_pilihan);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        pref = getSharedPreferences("mypref", MODE_PRIVATE);

        Intent intent = getIntent();
        CheckpointName = intent.getStringExtra("checkpointName");
        ChallengeTypeId = Integer.valueOf(intent.getStringExtra("challengeTypeId"));


        //realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        tempModel = realm.where(ModelCheckpoint.class).equalTo("checkpoint_name",CheckpointName).findFirst();
        type = realm.where(ModelChallengeType.class).equalTo("challenge_type_id",ChallengeTypeId).findFirst();
        realm.commitTransaction();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_pilihanangka);
        //Toast.makeText(myContext, "Hasil lokasi"+LocationId, Toast.LENGTH_SHORT).show();
        String nama = type.getType_name();
        TextView txt = (TextView) findViewById(R.id.txtJenisPertanyaan);
        txt.setText(nama);

        setUpRecyclerView();
    }



    private void setUpRecyclerView(){
        RealmResults<ModelQuestion> hasil= realm.where(ModelQuestion.class).equalTo("checkpoint_id", tempModel.getCheckpoint_id()).equalTo("challenge_type_id",ChallengeTypeId).findAll();

        angkaPilAdapter= new AngkaPilAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(
                gridLayoutManager = new AutoFitGridLayoutManager(getApplicationContext(), DEFAULT_WIDTH));
        recyclerView.setAdapter(angkaPilAdapter);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent previousScreen = new Intent(getApplicationContext(), ChallangeTypeActivity.class);
        startActivity(previousScreen);
        GridViewPilihanActivity.this.finish();
        return true;
    }

    public void onBackPressed(){
        Intent previousScreen = new Intent(getApplicationContext(), ChallangeTypeActivity.class);
        startActivity(previousScreen);
        GridViewPilihanActivity.this.finish();
    }
}
