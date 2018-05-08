package praktikum.develops.a11414001.digitourapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import io.realm.Realm;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;

public class DeskripsiWisataActivity extends AppCompatActivity {

    Realm realm;
    private int checkpointID;
    private ModelCheckpoint obj;
    private TextView namacheckpoint, deskripsi;
    private ImageView imgCheckpoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_wisata);

        Intent intent = getIntent();
        checkpointID = Integer.valueOf(intent.getStringExtra("checkpointId"));

        realm = Realm.getDefaultInstance();

        initializeView();
        try {
            obj = realm.where(ModelCheckpoint.class).equalTo("checkpoint_id",checkpointID).findFirst();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        setImgJenisFilm(obj);
    }
    private  void initializeView(){

        namacheckpoint =(TextView) findViewById(R.id.txtcheckpoint);
        deskripsi = (TextView) findViewById(R.id.txtDeskripsi);
        imgCheckpoint = (ImageView) findViewById(R.id.imageCheckpoint);

    }


    private void setImgJenisFilm(ModelCheckpoint checkpoint0){
        namacheckpoint.setText(""+checkpoint0.getCheckpoint_name());
        deskripsi.setText("Deskripsi : "+checkpoint0.getDescription());
        Glide.with(getApplicationContext()).load(RESTClient.URL+"/pa3ws/digitour/image/"+obj.getPath_gambarpoint()).into(imgCheckpoint);

    }
}
