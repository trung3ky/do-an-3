package hqtrung.hqt.doan3real.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import hqtrung.hqt.doan3real.Adapter.AddTinhThanhAdapter;
import hqtrung.hqt.doan3real.Model.CacTinhThanh;
import hqtrung.hqt.doan3real.R;

public class DiaChi_Activity extends AppCompatActivity {
    public static TextView txtDC;
    public static Dialog dialog;
    RecyclerView recyclerviewDiaChi;
    ArrayList<CacTinhThanh> arrayListTT;
    AddTinhThanhAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_);
        txtDC = (TextView) findViewById(R.id.TextViewChonDiaChi);

        txtDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });
    }

    private void Dialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_diachi);
        dialog.setCanceledOnTouchOutside(false);



        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP; // vị trí
//        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;// trong suốt
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT; // rộng ngược lại
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        recyclerviewDiaChi = (RecyclerView) dialog.findViewById(R.id.recyclerViewDiaChi);
        arrayListTT =new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DiaChi_Activity.this, RecyclerView.VERTICAL, false);
        recyclerviewDiaChi.setLayoutManager(linearLayoutManager);

        adapter = new AddTinhThanhAdapter(DiaChi_Activity.this, arrayListTT);
        recyclerviewDiaChi.setAdapter(adapter);

        AddTinhThanh();

        dialog.show();
    }

    private void AddTinhThanh(){


        arrayListTT.add(new CacTinhThanh("Thành phố Hà Nội"));
        arrayListTT.add(new CacTinhThanh("Đà Nẵng"));
        arrayListTT.add(new CacTinhThanh("Thành phố Hồ Chí Minh"));
        arrayListTT.add(new CacTinhThanh("Hà Giang"));
        arrayListTT.add(new CacTinhThanh("Cao Bằng"));
        arrayListTT.add(new CacTinhThanh("Bắc Kạn"));
        arrayListTT.add(new CacTinhThanh("Tuyên Quang"));
        arrayListTT.add(new CacTinhThanh("Lào Cai"));
        arrayListTT.add(new CacTinhThanh("Điện Biên"));
        arrayListTT.add(new CacTinhThanh("Lai Châu"));
        arrayListTT.add(new CacTinhThanh("Sơn La"));
        arrayListTT.add(new CacTinhThanh("Yên Bái"));
        arrayListTT.add(new CacTinhThanh("Hoà Bình"));
        arrayListTT.add(new CacTinhThanh("Thái Nguyên"));
        arrayListTT.add(new CacTinhThanh("Lạng Sơn"));
        arrayListTT.add(new CacTinhThanh("Quảng Ninh"));
        arrayListTT.add(new CacTinhThanh("Bắc Giang"));
        arrayListTT.add(new CacTinhThanh("Phú Thọ"));
        arrayListTT.add(new CacTinhThanh("Vĩnh Phúc"));
        arrayListTT.add(new CacTinhThanh("Ninh Bình"));
        arrayListTT.add(new CacTinhThanh("Thanh Hóa"));
        arrayListTT.add(new CacTinhThanh("Nghệ An"));
        arrayListTT.add(new CacTinhThanh("Hà Tĩnh"));
        arrayListTT.add(new CacTinhThanh("Quảng Bình"));
        arrayListTT.add(new CacTinhThanh("Quảng Trị"));
        arrayListTT.add(new CacTinhThanh("Thừa Thiên Huế"));
        arrayListTT.add(new CacTinhThanh("Quảng Nam"));
        arrayListTT.add(new CacTinhThanh("Quảng Ngãi"));
        arrayListTT.add(new CacTinhThanh("Bình Định"));
        arrayListTT.add(new CacTinhThanh("Phú Yên"));
        arrayListTT.add(new CacTinhThanh("Khánh Hòa"));
        arrayListTT.add(new CacTinhThanh("Ninh Thuận"));
        arrayListTT.add(new CacTinhThanh("Bình Thuận"));
        arrayListTT.add(new CacTinhThanh("Kon Tum"));
        arrayListTT.add(new CacTinhThanh("Gia Lai"));
        arrayListTT.add(new CacTinhThanh("Đắk Lắk"));
        arrayListTT.add(new CacTinhThanh("Đắk Nông"));
        arrayListTT.add(new CacTinhThanh("Lâm Đồng"));
        arrayListTT.add(new CacTinhThanh("Bình Phước"));
        arrayListTT.add(new CacTinhThanh("Tây Ninh"));
        arrayListTT.add(new CacTinhThanh("Bình Dương"));
        arrayListTT.add(new CacTinhThanh("Đồng Nai"));
        arrayListTT.add(new CacTinhThanh("Bà Rịa - Vũng Tàu"));
        arrayListTT.add(new CacTinhThanh("Long An"));
        arrayListTT.add(new CacTinhThanh("Tiền Giang"));
        arrayListTT.add(new CacTinhThanh("Bến Tre"));
        arrayListTT.add(new CacTinhThanh("Trà Vinh"));
        arrayListTT.add(new CacTinhThanh("Vĩnh Long"));
        arrayListTT.add(new CacTinhThanh("Đồng Tháp"));
        arrayListTT.add(new CacTinhThanh("An Giang"));
        arrayListTT.add(new CacTinhThanh("Kiên Giang"));
        arrayListTT.add(new CacTinhThanh("Thành phố Cần Thơ"));
        arrayListTT.add(new CacTinhThanh("Hậu Giang"));
        arrayListTT.add(new CacTinhThanh("Sóc Trăng"));
        arrayListTT.add(new CacTinhThanh("Bạc Liêu"));
        arrayListTT.add(new CacTinhThanh("Cà Mau"));
    }
}
