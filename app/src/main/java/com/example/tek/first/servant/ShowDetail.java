package com.example.tek.first.servant;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;


public class ShowDetail extends FragmentActivity {
    GoogleMap googleMap;
    LocationManager locationManager;
    TextView detailName, detailReviews, detailDistance;
    TextView detailDescription;
    RatingBar detailRating;
    String jsonInfoCollection;
    TextView detaiAdress, detailPhone, detailWebsite;
    TextView detailComments;
    Button btnselect;
    String web_url;
    String phone;
    String mapaddress;
    LocationListener ls;
    double latitude_detail;
    double longgitude_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        detailName = (TextView) findViewById(R.id.detail_name);
        detailRating = (RatingBar) findViewById(R.id.ratingBar);
        detailReviews = (TextView) findViewById(R.id.detail_comment);
        detailDistance = (TextView) findViewById(R.id.detail_distance);
        detailDescription = (TextView) findViewById(R.id.detail_description);
        detaiAdress = (TextView) findViewById(R.id.textViewAddress);
        detailPhone = (TextView) findViewById(R.id.textViewPhone);
        detailWebsite = (TextView) findViewById(R.id.textViewWebsite);
        detailComments = (TextView) findViewById(R.id.textViewCommentDetail);
        btnselect = (Button) findViewById(R.id.btnselect);
        SupportMapFragment supportMapFragment =
                (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.detail_map);

        googleMap = supportMapFragment.getMap();
        //googleMap.setMyLocationEnabled(true);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            jsonInfoCollection = extras.getString("resultObject");
        }
        InfoCollection infoCollectiondetail = new Gson().fromJson(jsonInfoCollection, InfoCollection.class);
        detailName.setText(infoCollectiondetail.getName());
        detailRating.setRating(infoCollectiondetail.getRating());
        detailReviews.setText(infoCollectiondetail.getNumber_comment() + "reviews");
        detailDistance.setText(infoCollectiondetail.getDistance() + "miles");
        detailDescription.setText(infoCollectiondetail.getCategory());
        detaiAdress.setText(infoCollectiondetail.getAddress());
        detailPhone.setText(infoCollectiondetail.getPhone_number());
        detailWebsite.setText(infoCollectiondetail.getMobile_url());
        detailComments.setText(infoCollectiondetail.getSnippet_text());
        mapaddress = infoCollectiondetail.getAddress();
        web_url = infoCollectiondetail.getMobile_url();
        phone = infoCollectiondetail.getPhone_number();
        latitude_detail = infoCollectiondetail.getLatitude();
        longgitude_detail = infoCollectiondetail.getLongtitude();
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        ls = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ls);
            LatLng latLng = new LatLng(latitude_detail, longgitude_detail);
            googleMap.addMarker(new MarkerOptions().position(latLng));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        } catch (Exception e) {

        }
        detaiAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri geoLocation = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q", mapaddress).build();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(geoLocation);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        detailWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(web_url);
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        detailPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:" + phone);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
