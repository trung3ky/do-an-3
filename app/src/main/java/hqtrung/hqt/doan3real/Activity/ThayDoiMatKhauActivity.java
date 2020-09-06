package hqtrung.hqt.doan3real.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;

import hqtrung.hqt.doan3real.Fragment.Fragment_User;
import hqtrung.hqt.doan3real.R;

public class ThayDoiMatKhauActivity extends AppCompatActivity {

    ImageView imgBack;
    EditText edtMKC, edtMKM;
    Button btn;

    String matkhau;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_mat_khau);

        Intent intent = getIntent();
        matkhau = intent.getStringExtra("matkhau");
        Toast.makeText(this, ""+matkhau, Toast.LENGTH_SHORT).show();
        AnhXa();
    }

    private void AnhXa() {
        imgBack = (ImageView) findViewById(R.id.ImageViewBackMatKhaut);
        edtMKC = (EditText) findViewById(R.id.matkhaucu);
        edtMKM = (EditText) findViewById(R.id.matkhaumoi);
        btn = (Button) findViewById(R.id.ButtonCapNhatpass);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThayDoiMatKhauActivity.this, SuaThongTinCaNhan_Activity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhap();
            }
        });
    }

    private void CapNhap(){
        if (edtMKC.getText().length() > 1 && edtMKM.getText().length() > 1){
            if (edtMKC.getText().toString().equals(matkhau)){
                database = FirebaseDatabase.getInstance();
                reference  = database.getReference().child("KhachHang");
                String key = Fragment_User.key.toString();
                reference.child(key).child("matkhau").setValue(edtMKM.getText().toString());
                Toast.makeText(this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThayDoiMatKhauActivity.this, SuaThongTinCaNhan_Activity.class));
            }else{
                Toast.makeText(this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
        }
    }
}
