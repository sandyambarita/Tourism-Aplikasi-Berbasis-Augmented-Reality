package praktikum.develops.a11414001.digitourapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import eu.kudan.kudan.ARActivity;
import eu.kudan.kudan.ARImageNode;
import eu.kudan.kudan.ARImageTrackable;
import eu.kudan.kudan.ARImageTracker;
import eu.kudan.kudan.ARNode;
import eu.kudan.kudan.ARTextureMaterial;
import io.realm.Realm;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;
import praktikum.develops.a11414001.digitourapplication.model.ModelQuestion;

public class ARCameraaActivity extends ARActivity {
    Realm realm;
    TextView instruksi;
    private String CheckpointName;
    private static int ChallengeTypeId = 0;
    ModelQuestion obj;

    private ARImageTrackable trackable, trackable1, trackable2, trackable3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcameraa);

        final Intent intent = getIntent();
        CheckpointName = intent.getStringExtra("checkpointName");
        ChallengeTypeId = Integer.valueOf(intent.getStringExtra("challengeTypeId"));

        realm = Realm.getDefaultInstance();

        try {
            ModelCheckpoint modelCheckpoint = realm.where(ModelCheckpoint.class).equalTo("checkpoint_name", CheckpointName).findFirst();
            obj = realm.where(ModelQuestion.class).equalTo("checkpoint_id",modelCheckpoint.getCheckpoint_id()).equalTo("challenge_type_id",ChallengeTypeId).findFirst();
            instruksi =(TextView) findViewById(R.id.txtInstruksi);
            Log.e("Question = ",""+obj.getQuestion());
            instruksi.setText(""+obj.getQuestion());
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Button button = (Button) findViewById(R.id.btnBackAR);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ChallangeTypeActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void setup() {
        addImageTrackable();
        addImageTrackable1();
        addImageTrackable2();
        addImageTrackable3();
        addImageNode();
        addImageNode1();
        addImageNode2();
        addImageNode3();
//        addModelNode();
//        addModelNode1();
    }

    private void addImageTrackable() {

        // Initialise image trackable
        trackable = new ARImageTrackable("Del");
        trackable.loadFromAsset("del.jpg");

//        trackable = new ARImageTrackable("Del");
//        trackable.loadFromAsset("del.jpg");
//
//        trackable = new ARImageTrackable("Indonesia");
//        trackable.loadFromAsset("indo.jpg");

        // Get instance of image tracker manager
        ARImageTracker trackableManager = ARImageTracker.getInstance();

        // Add image trackable to image tracker manager
        trackableManager.addTrackable(trackable);
    }

    private void addImageTrackable1() {

        // Initialise image trackable
        trackable1 = new ARImageTrackable("TB Silalahi");
        trackable1.loadFromAsset("patungtbsilalahi.jpg");

//        trackable = new ARImageTrackable("Del");
//        trackable.loadFromAsset("del.jpg");
//
//        trackable = new ARImageTrackable("Indonesia");
//        trackable.loadFromAsset("indo.jpg");

        // Get instance of image tracker manager
        ARImageTracker trackableManager = ARImageTracker.getInstance();

        // Add image trackable to image tracker manager
        trackableManager.addTrackable(trackable1);
    }

    private void addImageTrackable2() {

        // Initialise image trackable
        trackable2 = new ARImageTrackable("Stone Chair");
        trackable2.loadFromAsset("pasungsiallagan.jpg");

//        trackable = new ARImageTrackable("Del");
//        trackable.loadFromAsset("del.jpg");
//
//        trackable = new ARImageTrackable("Indonesia");
//        trackable.loadFromAsset("indo.jpg");

        // Get instance of image tracker manager
        ARImageTracker trackableManager = ARImageTracker.getInstance();

        // Add image trackable to image tracker manager
        trackableManager.addTrackable(trackable2);
    }

    private void addImageTrackable3() {

        // Initialise image trackable
        trackable3 = new ARImageTrackable("Kuburan Sidabutar");
        trackable3.loadFromAsset("kuburansidabutar.jpg");

//        trackable = new ARImageTrackable("Del");
//        trackable.loadFromAsset("del.jpg");
//
//        trackable = new ARImageTrackable("Indonesia");
//        trackable.loadFromAsset("indo.jpg");

        // Get instance of image tracker manager
        ARImageTracker trackableManager = ARImageTracker.getInstance();

        // Add image trackable to image tracker manager
        trackableManager.addTrackable(trackable3);
    }

    private void addImageNode() {

        // Initialise image node
        ARImageNode imageNode = new ARImageNode("itdel.png");

        // Add image node to image trackable
        trackable.getWorld().addChild(imageNode);

        // Image scale
        ARTextureMaterial textureMaterial = (ARTextureMaterial)imageNode.getMaterial();
        float scale = trackable.getWidth() / textureMaterial.getTexture().getWidth();
        imageNode.scaleByUniform(scale);

        // Hide image node
        imageNode.setVisible(false);

    }

    private void addImageNode1() {

        // Initialise image node
        ARImageNode imageNode = new ARImageNode("tbsilalahi.jpg");

        // Add image node to image trackable
        trackable1.getWorld().addChild(imageNode);

        // Image scale
        ARTextureMaterial textureMaterial = (ARTextureMaterial)imageNode.getMaterial();
        float scale = trackable1.getWidth() / textureMaterial.getTexture().getWidth();
        imageNode.scaleByUniform(scale);

        // Hide image node
        imageNode.setVisible(false);

    }

    private void addImageNode2() {

        // Initialise image node
        ARImageNode imageNode = new ARImageNode("stonechair.png");

        // Add image node to image trackable
        trackable2.getWorld().addChild(imageNode);

        // Image scale
        ARTextureMaterial textureMaterial = (ARTextureMaterial)imageNode.getMaterial();
        float scale = trackable2.getWidth() / textureMaterial.getTexture().getWidth();
        imageNode.scaleByUniform(scale);

        // Hide image node
        imageNode.setVisible(false);

    }

    private void addImageNode3() {

        // Initialise image node
        ARImageNode imageNode = new ARImageNode("sidabutar.png");

        // Add image node to image trackable
        trackable3.getWorld().addChild(imageNode);

        // Image scale
        ARTextureMaterial textureMaterial = (ARTextureMaterial)imageNode.getMaterial();
        float scale = trackable3.getWidth() / textureMaterial.getTexture().getWidth();
        imageNode.scaleByUniform(scale);

        // Hide image node
        imageNode.setVisible(false);

    }

//    private void addModelNode() {
//        // Import model
//        ARModelImporter modelImporter = new ARModelImporter();
//        modelImporter.loadFromAsset("ben.jet");
//        ARModelNode modelNode = (ARModelNode)modelImporter.getNode();
//
//        // Load model texture
//        ARTexture2D texture2D = new ARTexture2D();
//        texture2D.loadFromAsset("bigBenTexture.png");
//
//        // Apply model texture file to model texture material and add ambient lighting
//        ARLightMaterial material = new ARLightMaterial();
//        material.setTexture(texture2D);
//        material.setAmbient(0.8f, 0.8f, 0.8f);
//        modelNode.scaleByUniform(0.25f);
//        modelNode.rotateByDegrees(90,1,0,0);
//
//        // Apply texture material to models mesh nodes
//        for (ARMeshNode meshNode : modelImporter.getMeshNodes()){
//            meshNode.setMaterial(material);
//        }
//
//        // Add model node to image trackable
//        trackable.getWorld().addChild(modelNode);
//    }
//
//    private void addModelNode1() {
//        // Import model
//        ARModelImporter modelImporter = new ARModelImporter();
//        modelImporter.loadFromAsset("ataturk.jet");
//        ARModelNode modelNode = (ARModelNode)modelImporter.getNode();
//
//        // Load model texture
//        ARTexture2D texture2D = new ARTexture2D();
//        texture2D.loadFromAsset("mermer.jpg");
//
//        // Apply model texture file to model texture material and add ambient lighting
//        ARLightMaterial material = new ARLightMaterial();
//        material.setTexture(texture2D);
//        material.setAmbient(0.8f, 0.8f, 0.8f);
//        modelNode.scaleByUniform(10f);
//        modelNode.rotateByDegrees(0,0,0,0);
//
//        // Apply texture material to models mesh nodes
//        for (ARMeshNode meshNode : modelImporter.getMeshNodes()){
//            meshNode.setMaterial(material);
//        }
//
//        // Add model node to image trackable
//        trackable1.getWorld().addChild(modelNode);
//    }

//    public void addModelButtonPressed(View view) {
//
//        hideAll();
//        trackable.getWorld().getChildren().get(1).setVisible(true);
//        trackable1.getWorld().getChildren().get(1).setVisible(true);
//    }

    public void addImageButtonPressed(View view) {
        hideAll();
        trackable.getWorld().getChildren().get(0).setVisible(true);
        trackable1.getWorld().getChildren().get(0).setVisible(true);
        trackable2.getWorld().getChildren().get(0).setVisible(true);
        trackable3.getWorld().getChildren().get(0).setVisible(true);
    }

    private void hideAll(){
        List<ARNode> nodes =  trackable.getWorld().getChildren();
        List<ARNode> nodes1 =  trackable1.getWorld().getChildren();
        List<ARNode> nodes2 =  trackable2.getWorld().getChildren();
        List<ARNode> nodes3 =  trackable3.getWorld().getChildren();
        for(ARNode node : nodes){
            node.setVisible(false);
        }
        for(ARNode node : nodes1){
            node.setVisible(false);
        }
        for(ARNode node : nodes2){
            node.setVisible(false);
        }
        for(ARNode node : nodes3){
            node.setVisible(false);
        }
    }
}
