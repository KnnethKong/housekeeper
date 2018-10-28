package com.haiwai.housekeeper.activity.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.https.Contants;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.services.android.geocoder.ui.GeocoderAutoCompleteView;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import com.mapbox.services.geocoding.v5.models.CarmenFeature;

public class MapBoxMapActivity extends AppCompatActivity  {
    MapboxMap map;
    ImageView top_map_bar;
    private MapView mapView = null;
    GeocoderAutoCompleteView gacview;
    FloatingActionButton fab;
    private String lat = "";
    private String lng = "";
    private boolean isMap = false;
    private TextView tvSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapboxAccountManager.start(MapBoxMapActivity.this, Contants.TOKENS);
        setContentView(R.layout.activity_map_box_map);
        top_map_bar = (ImageView) findViewById(R.id.iv_back_arrow);
        gacview = (GeocoderAutoCompleteView) findViewById(R.id.query);
        tvSend = ((TextView) findViewById(R.id.tv_send_location));
        top_map_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(getIntent().getBooleanExtra("fromchart",false)){
            gacview.setVisibility(View.GONE);
            tvSend.setVisibility(View.VISIBLE);
        }
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setStyleUrl(Style.MAPBOX_STREETS); // specify the map style
        mapView.onCreate(savedInstanceState);
        initData();
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {//35.6184395336,112.2816432607
//                MarkerViewOptions markerViewOptions = new MarkerViewOptions()
//                        .position(new LatLng(35.6184395336, 112.2816432607));
//                mapboxMap.setMaxZoom(2.0f);
//                mapboxMap.addMarker(markerViewOptions);
                map = mapboxMap;
                getMyLocationTest();//定位当前位置
            }
        });
        gacview.setAccessToken(MapboxAccountManager.getInstance().getAccessToken());
        gacview.setType(GeocodingCriteria.TYPE_POI);
        gacview.setOnFeatureListener(new GeocoderAutoCompleteView.OnFeatureListener() {
            @Override
            public void OnFeatureClick(CarmenFeature feature) {
                Position position = feature.asPosition();
                updateMap(position.getLatitude(), position.getLongitude());
            }
        });


        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLocation!=null){
                    Intent data = new Intent();
                    data.putExtra("lat",mLocation.getLatitude()+"");
                    data.putExtra("lng",mLocation.getLongitude()+"");
                    setResult(RESULT_OK,data);
                    finish();
                }else{
                    setResult(100,new Intent());
                    finish();
                }
            }
        });

    }

    private void initData() {
        isMap = getIntent().getBooleanExtra("isMap", false);
        if (isMap) {
            lat = getIntent().getStringExtra("lat");
            lng = getIntent().getStringExtra("lng");
        }
    }

    private Location mLocation;
    private void getMyLocationTest() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        mLocation = map.getMyLocation();
        // Add the marker to the map
//        map.addMarker(new MarkerOptions()
//                .position(new LatLng(TextUtils.isEmpty(lat) ? map.getMyLocation().getLatitude() : Double.valueOf(lat), TextUtils.isEmpty(lng) ? map.getMyLocation().getLongitude() : Double.valueOf(lng))));
//
//        CameraPosition cameraPosition = new CameraPosition.Builder()
////                .target(new LatLng(mapboxMap.getMyLocation().getLatitude(), mapboxMap.getMyLocation().getLongitude()))
//                .target(new LatLng(TextUtils.isEmpty(lat) ? map.getMyLocation().getLatitude() : Double.valueOf(lat), TextUtils.isEmpty(lng) ? map.getMyLocation().getLongitude() : Double.valueOf(lng)))
//                .zoom(13f)
//                .build();
//        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        updataLocation(mapboxMap);
        updateMap(TextUtils.isEmpty(lat) ? map.getMyLocation().getLatitude() : Double.valueOf(lat), TextUtils.isEmpty(lng) ? map.getMyLocation().getLongitude() : Double.valueOf(lng));
//        System.out.println(">>>>>>>>>>>>>>>>>lat>>............2" + map.getMyLocation().getLatitude() + ">>>>>>>>>>>>>>>lng>>" + map.getMyLocation().getLongitude());

    }

//    private void updataLocation(MapboxMap mapboxMap) {
//        MarkerViewOptions markerViewOptions = new MarkerViewOptions()
//                .position(new LatLng(TextUtils.isEmpty(lat) ? mapboxMap.getMyLocation().getLatitude() : Double.valueOf(lat), TextUtils.isEmpty(lng) ? mapboxMap.getMyLocation().getLongitude() : Double.valueOf(lng)));
//        mapboxMap.addMarker(markerViewOptions);
//    }


    private void updateMap(double latitude, double longitude) {
        // Build marker
        map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title("Geocoder result"));
        // Animate camera to geocoder result location
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
                .zoom(15)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 5000, null);



    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
