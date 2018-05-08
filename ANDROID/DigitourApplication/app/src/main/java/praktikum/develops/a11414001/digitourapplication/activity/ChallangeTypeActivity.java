package praktikum.develops.a11414001.digitourapplication.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import eu.kudan.kudan.ARAPIKey;
import io.realm.Realm;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;

public class ChallangeTypeActivity extends AppCompatActivity {

    Realm realm;

    private MediaPlayer mediaPlayer;
    private SharedPreferences pref;
    private String checkpoint_name;
    private final String KEY_NAME = "name";
    String savedName;

    int id_checkpoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challange_type);

        ARAPIKey key = ARAPIKey.getInstance();
        key.setAPIKey("pYPISK2yqEzGqbTnA5cJUlj3klU4CW8JVgqApI2nHsS2fEe6xTN4oPtFz/8SAUorSy00QMCURCzJp/EdoKcBQWhC+PNq8C+ClcDQ5g8q9KtxXKEed3BuiZ1AEAHiKsKXl5VxBuFHz8fiQDSI2yO+mFpQBe0PWn5vfOoXOmRg0xBQdSjdXxxWZPVupswFRHrmLMIcicrnT3jTnq1//oXH/rPVNeZAyscXiuxsba0MYu9eEruApOPwdlTbHmP49+csvwnKis5/SDBQaXgcIuXrdS7LGQARHeHcmdifQXE0d2spE+U1hlj2dcp5A+/XuY4NK2BggfWx9f+xN9HlAsSeWS0uvjwyGTJC6BJJCEEiCNivDTTS/ZIYqUgL5Nzw2bMIKPhoL2iE7Ck2HLa9oKJfmspFxBTlV9X3neK+VL2AUohy//EBEmaxcQ8ZvLqIYqz3ETiWZ0HhyVlVPI1fHq7yWq6tkujx96oqnQ2xNBcJYp7/LkL5Us2mHKiJ2Aw/PCuHvvrKCqcPyFQEI8g0hHwTv0BtLY3aaBaqsBmse0jMLa1RIQj2juEUicq7H6nlbGkZDLQF+lQ1GnM5DzvCuTqDpmav1LgZ5o63OlOKuxX4Iw/m9c8K56GmIRD9TDnmzMPp/Z7kr9i0YTSm9Sp2OcryuukAq5nJtzXDpECNLz3/Pbo=");
        permissionsRequest();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        Button btnInfo = (Button) findViewById(R.id.btninfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo(v);
            }
        });

        Intent intent = getIntent();
        pref = getSharedPreferences("mypreferences", MODE_PRIVATE);
        savedName = pref.getString(KEY_NAME, "-");
        TextView textView = (TextView) findViewById(R.id.txtchallange);
        textView.setText("Challenge Type Di "+ savedName);

        realm.beginTransaction();
        ModelCheckpoint modelCheckpoint = realm.where(ModelCheckpoint.class).equalTo("checkpoint_name",savedName ).findFirst();
        realm.commitTransaction();

        id_checkpoint = modelCheckpoint.getCheckpoint_id();

        Button btn1 = (Button) findViewById(R.id.btnPG);
        Button btn2 = (Button) findViewById(R.id.btnTF);
        Button btn3 = (Button) findViewById(R.id.btnAR);
        Button btn4 = (Button) findViewById(R.id.btnSound);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), GridViewPilihanActivity.class);
                intent1.putExtra("checkpointName",savedName);
                intent1.putExtra("challengeTypeId", String.valueOf(1));
                startActivity(intent1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), GridViewPilihanActivity.class);
                intent2.putExtra("checkpointName",savedName);
                intent2.putExtra("challengeTypeId", String.valueOf(2));
                startActivity(intent2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), ARCameraaActivity.class);
                intent3.putExtra("checkpointName",savedName);
                intent3.putExtra("challengeTypeId", String.valueOf(3));
                startActivity(intent3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(id_checkpoint);
            }
        });
    }

    // Requests app permissions
    public void permissionsRequest() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 111);

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 111: {
                if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED ) {

                } else {
                    permissionsNotSelected();
                }
            }
        }
    }

    private void permissionsNotSelected() {
        AlertDialog.Builder builder = new AlertDialog.Builder (this);
        builder.setTitle("Permissions Required");
        builder.setMessage("Please enable the requested permissions in the app settings in order to use this demo app");
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                System.exit(1);
            }
        });
        AlertDialog noInternet = builder.create();
        noInternet.show();
    }

    public void playSound(int idCheckpoint){

        if(idCheckpoint == 1){
            if(mediaPlayer==null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.itdel);
                mediaPlayer.start();
            }else if(mediaPlayer.isPlaying()){
                mediaPlayer.release();
                mediaPlayer=null;
            }
        }
        else if(idCheckpoint == 2){
            if(mediaPlayer==null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.tbsilalahi);
                mediaPlayer.start();
            }else if(mediaPlayer.isPlaying()){
                mediaPlayer.release();
                mediaPlayer=null;
            }
        }else if(idCheckpoint == 3){
            if(mediaPlayer==null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.makamsidabutar);
                mediaPlayer.start();
            }else if(mediaPlayer.isPlaying()){
                mediaPlayer.release();
                mediaPlayer=null;
            }
        }else if(idCheckpoint == 4){
            if(mediaPlayer==null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.batukursi);
                mediaPlayer.start();
            }else if(mediaPlayer.isPlaying()){
                mediaPlayer.release();
                mediaPlayer=null;
            }
        }else{
            Toast.makeText(this, "Audio Masih Belum Ada", Toast.LENGTH_SHORT).show();
        }
    }


    public void dialogInfo(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.dialog_info);
        alertDialogBuilder.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent previousScreen = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(previousScreen);
        finish();
        return true;
    }
    public void onBackPressed(){
        Intent previousScreen = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(previousScreen);
        ChallangeTypeActivity.this.finish();
    }
}
