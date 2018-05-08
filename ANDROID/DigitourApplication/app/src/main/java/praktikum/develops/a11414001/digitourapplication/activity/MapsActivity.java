package praktikum.develops.a11414001.digitourapplication.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Realm realm;

    Circle mCircle;
    private GoogleMap mMap;
    Marker myMarker;
    String strLokasi;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final String KEY_CHECKPOINTNAME="name";

    private Circle circle;
    LocationManager locationManager;
    LatLng latLng;
    float latitudeUser, longitudeUser;
    int found=0;

//    double latitude;
//    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btnBack = (Button) findViewById(R.id.bBack);
        Button btnInfo = (Button) findViewById(R.id.bInfo);

        try {
            realm = Realm.getDefaultInstance();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=  new Intent(getApplicationContext(), PlayActivity.class);
                found=0;
                startActivity(i);
                finish();
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo(v);
                finish();
            }
        });

        pref = getSharedPreferences("mypreferences", MODE_PRIVATE);
        final String savedName = pref.getString(KEY_CHECKPOINTNAME, "-");

        //Map
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //check networkny enable
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //get the latitude
                    float latitude = (float) location.getLatitude();
                    //get the Longitudenya cuy
                    float longitude = (float)location.getLongitude();

                    //Instansiasi the class, latitude dan longitude ny CUI
                    latLng = new LatLng(latitude, longitude);

                    latitudeUser=(float)latLng.latitude;
                    longitudeUser=(float)latLng.latitude;
                    found = 1;
                    //Toast.makeText(MapsActivity.this, "Lokasi ANDA="+latitudeUser+"longlang"+longitudeUser, Toast.LENGTH_SHORT).show();
                    //longitudeUser=String.valueOf(latLng.longitude);

                    //Log.e("latitudeCuy", String.valueOf(latLng.latitude));

                    //Instansiasi class, Geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getCountryName();
                        strLokasi=str;
                        myMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Lokasi Anda: "+strLokasi));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.2f));

//                        circle = mMap.addCircle(new CircleOptions()
//                                .center(new LatLng(latitude, longitude))
//                                .radius(5000)
//                                .strokeColor(Color.GREEN)
//                                .fillColor(Color.argb(0,191,255,255))
//                                .clickable(true));

                        if(myMarker != null){

                            myMarker.showInfoWindow();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {
                    Toast.makeText(MapsActivity.this, "Perhatikan Koneksi dan GPS Anda", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //get the latitude
                    float latitude = (float)location.getLatitude();
                    //get the Longitudenya cuy
                    float longitude = (float)location.getLongitude();

                    //Instansiasi the class, latitude dan longitude ny CUI
                    latLng = new LatLng(latitude, longitude);

                    latitudeUser=(float)latLng.latitude;
                    longitudeUser=(float)latLng.latitude;
                    found = 1;

                   // Toast.makeText(MapsActivity.this, "Lokasi ANDA="+latitudeUser+"longlang"+longitudeUser, Toast.LENGTH_SHORT).show();

                    //Instansiasi class, Geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getCountryName();
                        strLokasi=str;
                        myMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Lokasi Anda: "+strLokasi));
                        //myMarker.showInfoWindow();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.2f));
//                        circle = mMap.addCircle(new CircleOptions()
//                                .center(new LatLng(latitude, longitude))
//                                .radius(1000)
//                                .strokeColor(Color.GREEN)
//                                .fillColor(Color.argb(0,191,255,255))
//                                .clickable(true));


                        if(myMarker != null){
                            myMarker.showInfoWindow();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {
                    Toast.makeText(MapsActivity.this, "Perhatikan koneksi dan GPS Anda", Toast.LENGTH_SHORT).show();
                }
            });
        }
        try {
            realm = Realm.getDefaultInstance();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void dialogInfo(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.dialog_info_map);
        alertDialogBuilder.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;
        Marker markercui;

            List<ModelCheckpoint> list = new ArrayList<>(realm.where(ModelCheckpoint.class).findAll());
            for (final ModelCheckpoint object : list) {

                float la = 0;
                float lo = 0;
                la = object.getLatitude();
                lo = object.getLongitude();
                LatLng latlngcheckpoint = new LatLng(la, lo);
                markercui = mMap.addMarker(new MarkerOptions().position(latlngcheckpoint).title(object.getCheckpoint_name()));
                markercui.setTitle(object.getCheckpoint_name());
                markercui.setVisible(true);
                markercui.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlngcheckpoint, 15.2f));
            }

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    double tempDistance;

                    //hitung distance, kalau distance masi dalam radius, proses.
                   // Log.e("jarak ", "" + distance(marker.getPosition().latitude, marker.getPosition().longitude, myMarker.getPosition().latitude, myMarker.getPosition().longitude));
                    if (marker.getTitle().compareTo("Lokasi Anda: " + strLokasi) == 0) {
                        myMarker.showInfoWindow();
                        found=1;

                    } else {
                        if(found==1) {
                            found=0;
                            tempDistance = distance(marker.getPosition().latitude, marker.getPosition().longitude, myMarker.getPosition().latitude, myMarker.getPosition().longitude);
                            if (tempDistance <= 2) {
                                try {
                                    //Log.e("test", marker.getPosition().latitude+" "+marker.getPosition().longitude);
                                    // variabel posisi User dimana
                                    // variabel yg diklik
                                    editor = pref.edit();
                                    editor.putString(KEY_CHECKPOINTNAME, String.valueOf(marker.getTitle()));
                                    editor.commit();

                                    String savedName = pref.getString(KEY_CHECKPOINTNAME, "-");
                                    Intent intent = new Intent(getApplicationContext(), ChallangeTypeActivity.class);
                                    found=0;
                                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("checkpointName", savedName);
                                    startActivity(intent);
                                    //return true;

                                } catch (Exception e) {
                                    Log.d("hasil error", e.getMessage());
                                }
                            } else {
                                Toast.makeText(MapsActivity.this, "Not in range.Min 2 KM from your position.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MapsActivity.this, "Wait to get your position. You must turn on your GPS", Toast.LENGTH_SHORT).show();
                        }
                    }

                    return true;
                }
            });
    }
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
