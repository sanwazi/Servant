package com.example.tek.first.servant;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    EditText editTextSearch;
    Button btnSearch;
    ListView listViewFood, listViewEntertainment, listViewShopping;
    public ArrayList<InfoCollection> food_list = new ArrayList<>();
    public ArrayList<InfoCollection> entertainment_list = new ArrayList<>();
    public ArrayList<InfoCollection> shopping_list = new ArrayList<>();
    double[] currentaddress = new double[2];
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    boolean refersh;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_fragment, menu);
        MenuItem item1 = menu.findItem(R.id.action_refresh);
        item1.setIcon(R.drawable.refresh);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            initInfo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        editTextSearch = (EditText) view.findViewById(R.id.et_search);
        btnSearch = (Button) view.findViewById(R.id.btn_search);
        btnSearch.setEnabled(true);
        listViewFood = (ListView) view.findViewById(R.id.listView_food_result);
        listViewEntertainment = (ListView) view.findViewById(R.id.listView_entertainment_result);
        listViewShopping = (ListView) view.findViewById(R.id.listView_shopping_result);
        final TabHost th = (TabHost) view.findViewById(R.id.tabHost);
        th.setup();
        TabWidget tabWidget = th.getTabWidget();
        th.addTab(th.newTabSpec("tab1").setContent(R.id.tab1).setIndicator("Food", null));
        th.addTab(th.newTabSpec("tab2").setContent(R.id.tab2).setIndicator("Entertainment", null));
        th.addTab(th.newTabSpec("tab3").setContent(R.id.tab3).setIndicator("Shopping", null));
        TextView tv1 = (TextView) tabWidget.getChildAt(0).findViewById(android.R.id.title);
        tv1.setTextSize(10);
        TextView tv2 = (TextView) tabWidget.getChildAt(1).findViewById(android.R.id.title);
        tv2.setTextSize(10);
        TextView tv3 = (TextView) tabWidget.getChildAt(2).findViewById(android.R.id.title);
        tv3.setTextSize(10);
        th.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {

            @Override
            public void onViewDetachedFromWindow(View v) {
            }

            @Override
            public void onViewAttachedToWindow(View v) {
                th.getViewTreeObserver().removeOnTouchModeChangeListener(th);
            }
        });
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currentaddress[0] = location.getLatitude();
                currentaddress[1] = location.getLongitude();
                // initData();
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


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchResult.class);
                Bundle bundle = new Bundle();
                bundle.putString("search", editTextSearch.getText().toString().trim());
                bundle.putDoubleArray("location", currentaddress);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initGPS() {

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            refersh = true;
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setMessage("Please open GPS to locate your positon");
            dialog.setPositiveButton("OK",
                    new android.content.DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 0);
                        }
                    });
            dialog.setNeutralButton("cancel", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });
            dialog.show();
        } else {
            updateLocation();
            refersh = false;
        }
    }


    private void updateLocation() {
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
            if (locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
                currentaddress[0] = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
                currentaddress[1] = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
            } else if (locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null) {
                currentaddress[0] = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude();
                currentaddress[1] = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude();
            }
        } catch (Exception e) {

        }

    }

    private void initInfo(){
        if (!isOnline()) {
            Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_LONG).show();
        } else {
            initGPS();
            updateLocation();
            initData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initInfo();

    }

    public void initData() {

        food_list.clear();
        entertainment_list.clear();
        shopping_list.clear();
        //****************************************//
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                if (currentaddress[0] == 0) {

                } else {
                    Yelp yelp = Yelp.getYelp(getActivity());
                    try {
                        String food_json = yelp.search("taco", currentaddress[0], currentaddress[1], "5");
                        String entertainment_json = yelp.search("bar", currentaddress[0], currentaddress[1], "5");
                        String shopping_json = yelp.search("Macy's", currentaddress[0], currentaddress[1], "5");
                        ProcessJSON.processJson(food_json, food_list);
                        ProcessJSON.processJson(entertainment_json, entertainment_list);
                        ProcessJSON.processJson(shopping_json, shopping_list);

                    } catch (JSONException e) {
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                listViewFood.setAdapter(new CustomAdapter(MainActivityFragment.this, food_list));
                listViewEntertainment.setAdapter(new CustomAdapter(MainActivityFragment.this, entertainment_list));
                listViewShopping.setAdapter(new CustomAdapter(MainActivityFragment.this, shopping_list));
            }
        }.execute();


    }

    public boolean isOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }
}
