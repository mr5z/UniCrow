package com.uapp.useekr.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.uapp.useekr.R;

import butterknife.BindView;

/**
 * Created by root on 12/2/17.
 */

public class SeekWorkFragment extends BaseFragment implements OnMapReadyCallback {

    @BindView(R.id.mapView)
    MapView mapView;

    @Override
    int contentLayout() {
        return R.layout.fragment_seek_work;
    }

    @Override
    int contentTitle() {
        return R.string.find_work;
    }

    @Override
    void initialize() {
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
    }
}
