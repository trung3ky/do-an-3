package hqtrung.hqt.doan3real.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hqtrung.hqt.doan3real.Activity.ChiTietBaiDoXeActivity;
import hqtrung.hqt.doan3real.Adapter.BaiDoXeAdapter;
import hqtrung.hqt.doan3real.Model.BaiDoXe;
import hqtrung.hqt.doan3real.Model.ChiTiet;
import hqtrung.hqt.doan3real.Model.LatLgn;
import hqtrung.hqt.doan3real.Model.Maker;
import hqtrung.hqt.doan3real.Model.SoLuong;
import hqtrung.hqt.doan3real.R;

import static android.content.ContentValues.TAG;

public class Fragment_Home extends Fragment implements OnMapReadyCallback {
    View view;
    GoogleMap map;
    MapView mymap;
    EditText editTextSearch;
    TextView txtTT;

    FirebaseDatabase database;
    DatabaseReference myRef, mChitiet;

    public String TrangThai;


    public static TextView txtVTND;
    FusedLocationProviderClient fusedLocationProviderClient;

    private ArrayList<ChiTiet> mData = new ArrayList<>(); // cc ni chưa chi mà nhiefu kinh rua m mới sửa cái j  rk
    private ArrayList<ChiTiet> mang = new ArrayList<>();// cái ni m copy à tí xóa
    ArrayList<LatLng> arrayList = new ArrayList<>();
    ArrayList<Maker> arrayListMaker = new ArrayList<>();
    ArrayList<BaiDoXe> danhArrayList = new ArrayList<>();
    private ArrayList<ChiTiet> chiTietArrayList = new ArrayList<>();

    private ArrayList<SoLuong> arrayListSoLuong = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        AnhXa();

        arrayList.clear();
        arrayListMaker.clear();
        getMaker();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mymap != null){
            mymap.onCreate(null);
            mymap.onResume();
            mymap.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        map = googleMap;
        map.setMyLocationEnabled(false);
        map.getUiSettings().setMyLocationButtonEnabled(false);


        map.getUiSettings().setZoomControlsEnabled(true);
        try {

            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.mapstyle));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        SetUpLocation();
    }

    private void getMaker(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("DanhSach");
        mChitiet = database.getReference().child("ChiTiet");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Double KinhDO1 = Double.parseDouble(snapshot.child("kinhDo").getValue().toString());
                Double VyDo1 = Double.parseDouble(snapshot.child("viDo").getValue().toString());
                final String ten = snapshot.child("tenBaiDoXe").getValue(String.class);
                String hinh_anh = snapshot.child("hinhAnh").getValue(String.class);
                String key = snapshot.getKey().toString();
//                final String soluong = snapshot.child("soLuong").getValue().toString();

                arrayList.add(new LatLng(VyDo1, KinhDO1));
                danhArrayList.add(new BaiDoXe(hinh_anh,ten,key,key));
                arrayListMaker.add(new Maker(ten, key));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void AnhXa(){
        mymap = (MapView) view.findViewById(R.id.map);
        editTextSearch = (EditText) view.findViewById(R.id.editTextTimKiem);
        txtVTND = (TextView) view.findViewById(R.id.Vitringuoidung);
        txtTT = (TextView) view.findViewById(R.id.TrangThai);
    }

    private void SetUpLocation(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            getLocation();
        }else{
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    private void getLocation(){
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
            Location location = task.getResult();
            if (location != null){
                Geocoder geocoder = new Geocoder(getActivity(),
                        Locale.getDefault());

                try {

                    for (int i = 0 ; i < arrayList.size(); i++){

                        if (BaiDoXeAdapter.arrayList.get(i).getConlai().toString().equals("0") ){
                            map.addMarker(new MarkerOptions().position(arrayList.get(i))
                                    .title(arrayListMaker.get(i).getNameMaker().toString()+" (Hết chỗ)")
                                    .snippet(arrayListMaker.get(i).getKey())
                                    .icon(bitmapDescriptorFromVector(getActivity().getApplicationContext(), R.drawable.parking_red)));

                            map.animateCamera(CameraUpdateFactory.zoomTo(13));
                            map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                        }else {
                            map.addMarker(new MarkerOptions().position(arrayList.get(i))
                                    .title(arrayListMaker.get(i).getNameMaker().toString())
                                    .snippet(arrayListMaker.get(i).getKey())
                                    .icon(bitmapDescriptorFromVector(getActivity().getApplicationContext(), R.drawable.car_maker)));

                            map.animateCamera(CameraUpdateFactory.zoomTo(13));
                            map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                        }

                        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                            @Override
                            public View getInfoWindow(Marker marker) {
                                View view = getLayoutInflater().inflate(R.layout.info_maker, null);

                                TextView txt = (TextView) view.findViewById(R.id.trung1);
//                                TextView txt1 = (TextView) view.findViewById(R.id.trung);

                                txt.setText(marker.getTitle());
//                                txt1.setText(marker.getSnippet());


                                return view;
                            }

                            @Override
                            public View getInfoContents(Marker marker) {
                                return null;
                            }
                        });


                        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                String makerkey = marker.getSnippet();
                                Intent intent = new Intent(getActivity(), ChiTietBaiDoXeActivity.class);
                                intent.putExtra("key", makerkey);
                                startActivity(intent);
                            }
                        });
                    }



                    List<Address> addresses = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 1
                    );
                    txtVTND.setText(addresses.get(0).getLatitude()+" "+addresses.get(0).getLongitude());
                    LatLng sydney = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
                    map.addMarker(new MarkerOptions()
                            .position(sydney)
                    );

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }
        });
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawble = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawble.setBounds(0, 0,vectorDrawble.getIntrinsicWidth(),
                vectorDrawble.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawble.getIntrinsicWidth(),
                vectorDrawble.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawble.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


}
