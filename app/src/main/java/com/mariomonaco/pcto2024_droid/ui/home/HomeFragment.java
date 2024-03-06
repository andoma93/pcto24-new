package com.mariomonaco.pcto2024_droid.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnMapsSdkInitializedCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mariomonaco.pcto2024_droid.R;
import com.mariomonaco.pcto2024_droid.databinding.FragmentHomeBinding;
import com.mariomonaco.pcto2024_droid.utils.PermissionUtils;

class Animale{
    private String nome;
    private int zampe;
    private double peso;

    private boolean emetteSuono;

    public String getTipo(){
        return "Animale";
    }
}

interface EmetteVerso{
    public String suono();
}

class Cane extends Animale implements EmetteVerso{
    int volumeAbbaio;

    @Override
    public String getTipo(){
        return "Cane";
    }

    @Override
    public String suono() {
        return "Abbaia";
    }
}

class Lucertola extends Animale{
    private int numeroSquame;
}

public class HomeFragment extends Fragment implements OnMapReadyCallback, OnMapsSdkInitializedCallback, OnSuccessListener {

    private FragmentHomeBinding binding;
    private FusedLocationProviderClient fusedLocationClient;
    private Context mContext;
    private SupportMapFragment supportMapFragment;
    private GoogleMap map;
    private Location lastLocation;

    private SupportMapFragment mMapFragment;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
        PermissionUtils.requestLocationPermissions((AppCompatActivity) this.getActivity(), 0, false);
        supportMapFragment.getMapAsync(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getContext());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
        fusedLocationClient.getLastLocation().addOnSuccessListener(this);
    }

    @Override
    public void onMapsSdkInitialized(@NonNull MapsInitializer.Renderer renderer) {
        System.out.println("onMapsSdkInitialized");
    }

    @Override
    public void onSuccess(Object locationObj) {
        if (locationObj != null && locationObj instanceof Location) {
            Location location = (Location) locationObj;
            this.lastLocation = location;
            updateMapMarkers(location);
        }
    }

    public void updateMapMarkers(Location location){
        map.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("Eccomi!"));
        LatLng coordinate = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate locationTarget = CameraUpdateFactory.newLatLngZoom(
                coordinate, 15);
        map.animateCamera(locationTarget);
    }
}