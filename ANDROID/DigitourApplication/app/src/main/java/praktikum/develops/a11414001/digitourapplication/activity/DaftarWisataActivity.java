package praktikum.develops.a11414001.digitourapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.adapter.LokasiAdapter;
import praktikum.develops.a11414001.digitourapplication.model.ModelLocation;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;

public class DaftarWisataActivity extends AppCompatActivity {
    Realm realm;

    RecyclerView recyclerView = null;
    private LokasiAdapter lokasiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarwisata);

        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_play);
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        RealmResults<ModelLocation> hasil= realm.where(ModelLocation.class).findAll();

        lokasiAdapter = new LokasiAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lokasiAdapter);
        recyclerView.setHasFixedSize(true);

    }
}
