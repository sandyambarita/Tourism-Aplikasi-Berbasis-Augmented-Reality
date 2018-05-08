package praktikum.develops.a11414001.digitourapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.adapter.AboutAdapter;
import praktikum.develops.a11414001.digitourapplication.model.ModelAbout;

public class AboutActivity extends AppCompatActivity {

    Realm realm;
    RecyclerView recyclerView = null;
    private AboutAdapter aboutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_about);
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        RealmResults<ModelAbout> hasil= realm.where(ModelAbout.class).findAll();

        aboutAdapter = new AboutAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(aboutAdapter);
        recyclerView.setHasFixedSize(true);

    }
    public void onBackPressed(){
        Intent previousScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(previousScreen);
        AboutActivity.this.finish();
    }
}
