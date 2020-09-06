package hqtrung.hqt.doan3real.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;

import hqtrung.hqt.doan3real.Adapter.BaiDoXeAdapter;
import hqtrung.hqt.doan3real.Fragment.Fragment_Home;
import hqtrung.hqt.doan3real.Model.ChiTiet;
import hqtrung.hqt.doan3real.R;

public class ChiTietBaiDoXeActivity extends AppCompatActivity {

    ImageView imgCT, imgBack, imgChiDuong, imgLienHe;
    TextView txtTitle, txtDC, txtGio, txtVT, txtSLT, txtT;
    HtmlTextView htmlTextView1, htmlTextView2, htmlTextView3, htmlTextView4;
    private String key;
    private ArrayList<ChiTiet> mData = new ArrayList<>();
    private String soluong, diachi, ten, hinh_anh, gio, sdt, kinhdo, vydo;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String maker = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bai_do_xe);

        // nhân nhớ lưu vào nge
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        AnhXa();
        GetBaiDoXe();
        GetData();

    }

    private void AnhXa() {
        imgCT = (ImageView) findViewById(R.id.ImageViewChiTiet);
        txtTitle = (TextView) findViewById(R.id.TextViewTitle);
        txtDC = (TextView) findViewById(R.id.TextViewDiaChiCT);
        txtGio = (TextView) findViewById(R.id.TextViewGioCT);
        txtVT = (TextView) findViewById(R.id.TextViewViTriCT);
        txtSLT = (TextView) findViewById(R.id.TextViewSoLuongTrong);
        txtT = (TextView) findViewById(R.id.TextViewTrong);
        imgBack = (ImageView) findViewById(R.id.ImageViewBack);
        htmlTextView1 = (HtmlTextView) findViewById(R.id.html_text1);
        htmlTextView2 = (HtmlTextView) findViewById(R.id.html_text2);
        htmlTextView3 = (HtmlTextView) findViewById(R.id.html_text3);
        htmlTextView4 = (HtmlTextView) findViewById(R.id.html_text4);
        imgChiDuong = (ImageView) findViewById(R.id.ChiDuong);
        imgLienHe = (ImageView) findViewById(R.id.LienHe);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        
        htmlTextView1.setHtml("<ul><li>Mái che</li></ul>");
        htmlTextView2.setHtml("<ul><li>Người trông giữ</li></ul>");
        htmlTextView3.setHtml("<ul><li>Đèn chiếu sáng</li></ul>");
        htmlTextView4.setHtml("<ul><li>Đỗ qua đêm</li></ul>");
        
        imgChiDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiDuong(vydo, kinhdo);
            }
        });
        imgLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LienHe();
            }
        });
    }

    private void LienHe(){
        Intent callIntent=new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+sdt));
        startActivity(callIntent);
    }
    
    private void ChiDuong(String sSource, String sDestination){
       try {
           String vtnd = Fragment_Home.txtVTND.getText()+"";//15.5638292 108.4821677
           Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+vtnd+"/" +sSource+" " +sDestination);
           Intent intent = new Intent(Intent.ACTION_VIEW, uri);
           intent.setPackage("com.google.android.apps.maps");
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           startActivity(intent);
       }catch (ActivityNotFoundException e){
           Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
           Intent intent = new Intent(Intent.ACTION_VIEW, uri);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           startActivity(intent);
       }
    }

    private void GetBaiDoXe(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("DanhSach");

        myRef.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                soluong = snapshot.child("soLuong").getValue().toString();
                diachi = snapshot.child("diaChi").getValue().toString();
                ten = snapshot.child("tenBaiDoXe").getValue().toString();
                hinh_anh = snapshot.child("hinhAnh").getValue().toString();
                gio = snapshot.child("gio").getValue().toString();
                sdt = snapshot.child("soDienThoai").getValue().toString();
                kinhdo = snapshot.child("kinhDo").getValue().toString();
                vydo = snapshot.child("viDo").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    
    private void GetData(){
        if (!key.equals("")){
            Query query = FirebaseDatabase.getInstance().getReference().child("ChiTiet")
                    .orderByChild("keyBaiDauXe")
                    .equalTo(key);
            query.addChildEventListener(new ChildEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    ChiTiet chiTiet = dataSnapshot.getValue(ChiTiet.class);
                    int dem = 0;

                    txtDC.setText(diachi);
                    txtGio.setText("Giờ thu phí: "+gio); // cái này là tổng sô ô nè để đỡ run đi
                    txtTitle.setText(ten); // set may cai ni di
                    txtVT.setText("sức chứa "+soluong);
                    Picasso.get().load(hinh_anh).into(imgCT);

                    mData.add(chiTiet);

                    for (int i = 0; i < mData.size(); i++) {
                        if (mData.get(i).getTrangThai().equals("Còn")) {
                            dem++;
                            if (Integer.parseInt(soluong) >= dem) {
                                txtSLT.setTextSize(50);
                                txtSLT.setText(String.valueOf(dem)); // cái này là t ính ô còn trống
                                txtT.setVisibility(View.VISIBLE);

                                if ((Integer.parseInt(soluong) - dem) == 0) {
                                    txtSLT.setText(soluong);
//                                    txtSLT.setTextSize(40);
//                                    txtSLT.setPadding(0, 10, 0, 0);
                                    txtT.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }

                    if (dem == 0) {
                        txtSLT.setText("Hết chỗ");
                        txtSLT.setTextSize(40);
                        txtSLT.setPadding(0, 10, 0, 0);
                        txtT.setVisibility(View.GONE);
                    }



                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

}
