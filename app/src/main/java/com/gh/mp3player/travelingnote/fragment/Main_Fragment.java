package com.gh.mp3player.travelingnote.fragment;

import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.FragmentTransaction;

import com.gh.mp3player.travelingnote.App;
import com.gh.mp3player.travelingnote.OnMainCallBack;
import com.gh.mp3player.travelingnote.R;
import com.gh.mp3player.travelingnote.act.MapMgr;
import com.gh.mp3player.travelingnote.databinding.MainFragmentBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main_Fragment extends BaseFragment<MainFragmentBinding> implements OnMainCallBack {
    public static final String TAG = Main_Fragment.class.getName();
    private static final int TYPE_LIST = 1;
    private static final int TYPE_MAP = 2;
    private static final int TYPE_OPTION = 3;
    private ArrayList<PlaceItem> listMap;


    @Override
    protected void initView() {

        mbinding.menu.icList.setOnClickListener(view -> {
            readStoryFile();
            showMenu(TYPE_LIST);
        });
        mbinding.menu.icMap.setOnClickListener(view -> {
            readStoryFile();
            showMenu(TYPE_MAP);
        });
        mbinding.menu.icUtility.setOnClickListener(this);
        mbinding.ivPlace.setOnClickListener(view -> showMyLocation());
        showMenu(TYPE_LIST);
    }

    private void showMyLocation() {
        MapMgr mgr=new MapMgr();
        mgr.getInstance().forseShowMyLocation();
    }


    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.ic_list) {
            showMenu(TYPE_LIST);
        } else if (v.getId() == R.id.ic_map) {
            showMenu(TYPE_MAP);
        } else if (v.getId() == R.id.ic_utility) {
            showMenu(TYPE_OPTION);
        }
    }

    public void readStoryFile() {
        listMap = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("danhlam.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String name = null;
            String link = null;
            double lat = 0;
            double log = 0;
            StringBuilder desc = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                if (line.isEmpty()) continue;
                if (name == null) {
                    name = line;
                } else if (lat == 0) {
                    lat = Double.parseDouble(line);
                } else if (log == 0) {
                    log = Double.parseDouble(line);
                } else if (link == null) {
                    link = line;
                } else if (!line.contains("@@@")) {
                    desc.append(line).append("\n");

                } else {
                    PlaceItem model = new PlaceItem(name, desc.toString(), link, lat, log);
                    listMap.add(model);
                    name = null;
                    lat = 0;
                    log = 0;
                    link = null;
                    desc = new StringBuilder();
                }
                line = reader.readLine();
            }
            inputStream.close();
            inputStreamReader.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context, listMap.size() + "", Toast.LENGTH_SHORT).show();
    }

    private void showMenu(int typeList) {
        ImageViewCompat.setImageTintList(mbinding.menu.icList, ColorStateList.valueOf(context.getColor(typeList == 1 ? R.color.white : R.color.lower_blue)));
        ImageViewCompat.setImageTintList(mbinding.menu.icMap, ColorStateList.valueOf(context.getColor(typeList == 2 ? R.color.white : R.color.lower_blue)));
        ImageViewCompat.setImageTintList(mbinding.menu.icUtility, ColorStateList.valueOf(context.getColor(typeList == 3 ? R.color.white : R.color.lower_blue)));

        if (typeList == 1) {
            goToPlaceFragment();
        } else if (typeList == 2) {
            goToLocationFragment();
        }
    }

    private void goToLocationFragment() {
        mbinding.ivPlace.setVisibility(View.VISIBLE);
        SupportMapFragment fragment = new SupportMapFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_main, fragment, "").commit();
        fragment.getMapAsync(googleMap -> {
            MapMgr mgr = new MapMgr();
            mgr.getInstance().initMap(context, googleMap);
        });
    }

    private void goToPlaceFragment() {
        mbinding.ivPlace.setVisibility(View.GONE);
        readStoryFile();
        showFragment(ListFragment.TAG, listMap, false);
    }

    public void showFragment(String tag, Object data, boolean isBack) {
        try {
            Class<?> clazz = Class.forName(tag);//Trỏ vào 1 Fragment class
            Constructor<?> constructor = clazz.getConstructor();
            BaseFragment fragment = (BaseFragment) constructor.newInstance();
            fragment.setmData(data);
            fragment.setCallBack(this);

            FragmentTransaction trans = getChildFragmentManager().beginTransaction();
            if (isBack) {
                trans.addToBackStack(null);
            }
            trans.replace(R.id.ln_main, fragment, tag).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected MainFragmentBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return MainFragmentBinding.inflate(inflater, container, false);
    }


}
