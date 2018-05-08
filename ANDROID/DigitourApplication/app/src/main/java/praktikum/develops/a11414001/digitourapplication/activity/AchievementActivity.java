package praktikum.develops.a11414001.digitourapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.adapter.AchievementAdapter;
import praktikum.develops.a11414001.digitourapplication.model.ModelAchievement;

public class AchievementActivity extends AppCompatActivity {

    Realm realm;

    RecyclerView recyclerView = null;
    private AchievementAdapter achievementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_achievement);
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        RealmResults<ModelAchievement> hasil= realm.where(ModelAchievement.class).findAll();

        achievementAdapter = new AchievementAdapter(hasil,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(achievementAdapter);
        recyclerView.setHasFixedSize(true);

    }
    public void onBackPressed(){
        Intent previousScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(previousScreen);
        AchievementActivity.this.finish();
    }
}