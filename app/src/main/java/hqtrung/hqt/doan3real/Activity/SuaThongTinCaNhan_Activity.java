package hqtrung.hqt.doan3real.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import hqtrung.hqt.doan3real.Fragment.Fragment_User;
import hqtrung.hqt.doan3real.R;

public class SuaThongTinCaNhan_Activity extends AppCompatActivity {

    private EditText txtName, txtAdress, txtEmail;
    TextView txtPhone,txtPass;
    TextView btnCN;
    private ImageView imgName, imgPhone, imgAdress, imgPass, imgEmail, imgBack;
    FirebaseDatabase database;
    DatabaseReference reference;

    String matkhau;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String name,email,diachi;
    public Boolean kt = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin_ca_nhan_);

        AnhXa();
        GetData();

    }
    private void AnhXa(){
        txtAdress = (EditText) findViewById(R.id.EditPlace);
        txtEmail = (EditText) findViewById(R.id.EditEmail);
        txtName = (EditText) findViewById(R.id.EditName);
        txtPhone = (TextView) findViewById(R.id.EditPhone);
        txtPass = (TextView) findViewById(R.id.EditPass);
        btnCN = (TextView) findViewById(R.id.Button);

        imgName = (ImageView) findViewById(R.id.ImageName);
        imgAdress = (ImageView) findViewById(R.id.ImageDiaChi);
        imgEmail = (ImageView) findViewById(R.id.ImageEmail);
        imgPass = (ImageView) findViewById(R.id.ImagePass);
        imgPhone = (ImageView) findViewById(R.id.ImagePhone);
        imgBack = (ImageView) findViewById(R.id.ImageViewBackTTCN);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuaThongTinCaNhan_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        txtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuaThongTinCaNhan_Activity.this, ThayDoiMatKhauActivity.class);
                intent.putExtra("matkhau", matkhau);
                startActivity(intent);
            }
        });



        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCN.setTextColor(Color.WHITE);
                btnCN.setBackgroundColor(Color.parseColor("#4949e7"));
                kt = false;
            }
        });
        txtAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCN.setTextColor(Color.WHITE);
                btnCN.setBackgroundColor(Color.parseColor("#4949e7"));
                kt = false;
            }
        });
        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCN.setTextColor(Color.WHITE);
                btnCN.setBackgroundColor(Color.parseColor("#4949e7"));
                kt = false;
            }
        });

        btnCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    CapNhap();
            }
        });

    }

    private void CapNhap(){

        if (!txtName.getText().toString().equals("") && !txtAdress.getText().toString().equals("") && !txtEmail.getText().toString().equals("")){
            String key = Fragment_User.key.toString();
            database = FirebaseDatabase.getInstance();
            reference = database.getReference().child("KhachHang").child(key);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String ten = snapshot.child("ten").getValue().toString();
                    String diachi = snapshot.child("diachi").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();

                    if (ten.equals(txtName.getText().toString().trim()) && diachi.equals(txtAdress.getText().toString().trim()) && email.equals(txtEmail.getText().toString().trim())){

                    }else{
                        if (txtEmail.getText().toString().trim().matches(emailPattern)){
                            reference.child("ten").setValue(txtName.getText().toString().trim());
                            reference.child("diachi").setValue(txtAdress.getText().toString().trim());
                            reference.child("email").setValue(txtEmail.getText().toString().trim());
                            Toast.makeText(SuaThongTinCaNhan_Activity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SuaThongTinCaNhan_Activity.this, "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }else{
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }

    }

    private void GetData(){
        String key = Fragment_User.key.toString();
        if (!key.equals("")){
            database = FirebaseDatabase.getInstance();
            reference = database.getReference().child("KhachHang");
            reference.child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.child("ten").getValue().toString();
                    String phone = snapshot.child("sodienthoai").getValue().toString();
                    String diachi = snapshot.child("diachi").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();
                    matkhau = snapshot.child("matkhau").getValue().toString();

                    if (!name.isEmpty()){
                        txtName.setText(name);
                        txtName.setBackgroundResource(R.drawable.custom_ttcn2);
                    }
                    if (!phone.isEmpty()){
                        txtPhone.setText(phone);
                        txtPhone.setBackgroundResource(R.drawable.custom_ttcn2);
                    }
                    if (!diachi.isEmpty()){
                        txtAdress.setText(diachi);
                        txtAdress.setBackgroundResource(R.drawable.custom_ttcn2);
                    }
                    if (!email.isEmpty()){
                        txtEmail.setText(email);
                        txtEmail.setBackgroundResource(R.drawable.custom_ttcn2);
                    }
                    if (!matkhau.isEmpty()){
                        txtPass.setText("********");
                        txtPass.setBackgroundResource(R.drawable.custom_ttcn2);
                        imgPass.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}
