package praktikum.develops.a11414001.digitourapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.adapter.LeaderboardAdapter;
import praktikum.develops.a11414001.digitourapplication.model.ModelLeaderboard;

public class LeaderboardActivity extends AppCompatActivity {

    Realm realm;

    RecyclerView recyclerView = null;
    private LeaderboardAdapter leaderboardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_leaderboard);
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        RealmResults<ModelLeaderboard> hasil= realm.where(ModelLeaderboard.class).findAll();

        leaderboardAdapter = new LeaderboardAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(leaderboardAdapter);
        recyclerView.setHasFixedSize(true);

    }
    public void onBackPressed(){
        Intent previousScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(previousScreen);
        LeaderboardActivity.this.finish();
    }
}