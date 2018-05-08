package praktikum.develops.a11414001.digitourapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import praktikum.develops.a11414001.digitourapplication.R;

public class InformasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        GridLayout grd = (GridLayout) findViewById(R.id.grdViewinformasi);
        grd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformasiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed(){
        Intent previousScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(previousScreen);
        InformasiActivity.this.finish();
    }
}
