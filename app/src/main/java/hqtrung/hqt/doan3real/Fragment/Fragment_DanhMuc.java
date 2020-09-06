package hqtrung.hqt.doan3real.Fragment;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hqtrung.hqt.doan3real.Adapter.BaiDoXeAdapter;
import hqtrung.hqt.doan3real.Model.BaiDoXe;
import hqtrung.hqt.doan3real.Model.ChiTiet;
import hqtrung.hqt.doan3real.R;

public class Fragment_DanhMuc extends Fragment {

    View view;
    EditText edtTk;
    public static LinearLayout layoutTb;

    RecyclerView recyclerView;
    ArrayList<BaiDoXe> arrayList;
    BaiDoXeAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference myRef, mChitiet;
    private String soLuong, key, diaChi, gio, hinhAnh, soDienThoai, tenBaiDauXe, kinhDo, vyDo;

    private ArrayList<ChiTiet> chiTietArrayList = new ArrayList<>();


    ProgressDialog dialog;
    public String trangThai;

    private ArrayList<ChiTiet> mData = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danhmuc, container, false);
        AnhXa();


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewBaiDoXe);
        arrayList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        GetBaiDoXe();




        return view;
    }

    private void AnhXa() {
        edtTk = (EditText) view.findViewById(R.id.EditTextSearch);
        layoutTb = (LinearLayout) view.findViewById(R.id.TextViewThongBao);

        dialog = new ProgressDialog(getActivity());

        edtTk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });



    }

    private void filter(String Text){
        ArrayList<BaiDoXe> filterlist = new ArrayList<>();

        for (BaiDoXe item : arrayList){
            if (item.getTen().toLowerCase().contains(Text.toLowerCase())){
                filterlist.add(item);

            }
        }
        adapter.filterList(filterlist);
    }

    private void GetBaiDoXe(){

        dialog.setTitle("thông báo");
        dialog.setMessage("Đang tải dữ liệu");
        dialog.show();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("DanhSach");
        mChitiet = database.getReference().child("ChiTiet");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    key = snapshot.getKey().toString();
                    hinhAnh = snapshot.child("hinhAnh").getValue(String.class);
                    tenBaiDauXe = snapshot.child("tenBaiDoXe").getValue(String.class);
                    diaChi = snapshot.child("diaChi").getValue(String.class);
                    soLuong = snapshot.child("soLuong").getValue(String.class);
                    kinhDo = snapshot.child("kinhDo").getValue(String.class);
                    vyDo = String.valueOf(snapshot.child("viDo").getValue(String.class));
                    soDienThoai = snapshot.child("soDienThoai").getValue(String.class);
                    gio = snapshot.child("gio").getValue(String.class);


                    // contructor m tạo đóe có số lượng nge
                    arrayList.add(new BaiDoXe(hinhAnh, tenBaiDauXe, key, diaChi, kinhDo, vyDo, soDienThoai, soLuong, gio));
                }

                mChitiet.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String keyBaiDauXe = snapshot.child("keyBaiDauXe").getValue(String.class);
                            String trangThai = snapshot.child("trangThai").getValue(String.class);
                            chiTietArrayList.add(new ChiTiet(keyBaiDauXe, trangThai));
                        }

                        // duyệt cả 2 cái mảng

                        for (int i = 0; i < arrayList.size(); i++) {
                            String key = arrayList.get(i).getKey();
                            int dem = 0;
                            boolean isCheck = false;
                            for (int j = 0; j < chiTietArrayList.size(); j++) {
                                if (chiTietArrayList.get(j).getKeyBaiDauXe().equals(key)) {
                                    if (chiTietArrayList.get(j).getTrangThai().equals("Còn")) {
                                        dem++;
                                        arrayList.get(i).setConlai(String.valueOf(dem));
                                    }
                                }
                            }
                            if (dem == 0) {
                                arrayList.get(i).setConlai(String.valueOf(dem));
                            }
                        }


                        adapter = new BaiDoXeAdapter(getActivity(), arrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
