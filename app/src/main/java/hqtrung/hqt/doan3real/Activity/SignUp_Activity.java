package hqtrung.hqt.doan3real.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import hqtrung.hqt.doan3real.Adapter.AddTinhThanhAdapter;
import hqtrung.hqt.doan3real.Model.User;
import hqtrung.hqt.doan3real.R;

public class SignUp_Activity extends AppCompatActivity {

    TextInputLayout layoutUser, layoutPhone, layoutPass, layoutConfim;
    EditText edtUser, edtPhone, edtPass,edtConfim;
    Button btnSU;
    TextView txtSI;
    ProgressDialog progressDialog;

    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        AnhXa();
    }

    private void AnhXa() {
        layoutUser = (TextInputLayout)  findViewById(R.id.LayoutUserSU);
        layoutPhone = (TextInputLayout) findViewById(R.id.LayoutPhoneSU);
        layoutPass = (TextInputLayout) findViewById(R.id.LayoutPassSU);
        layoutConfim = (TextInputLayout) findViewById(R.id.LayoutConfimSU);
        edtUser = (EditText) findViewById(R.id.EditTextUserSU);
        edtPhone = (EditText) findViewById(R.id.EditTextPhoneSU);
        edtPass = (EditText) findViewById(R.id.EditTextPassSU);
        edtConfim = (EditText) findViewById(R.id.EditTextConfimSU);
        btnSU = (Button) findViewById(R.id.buttonSignUp);
        txtSI = (TextView) findViewById(R.id.TextViewSignIn);

        progressDialog = new ProgressDialog(this);

        txtSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_Activity.this, SigIn_Activity.class));
            }
        });

        btnSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()){
                    progressDialog.setTitle("Đang Chạy");
                    progressDialog.show();
                    SignUp();
                }
            }
        });
        edtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtUser.getText().toString().isEmpty()){
                    layoutUser.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtPhone.getText().toString().isEmpty()){
                    layoutPhone.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtPass.getText().toString().length() > 7){
                    layoutPass.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtConfim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtConfim.getText().toString().equals(edtPass.getText().toString())){
                    layoutConfim.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean Validate(){
        if (edtUser.getText().toString().isEmpty()){
            layoutUser.setErrorEnabled(true);
            layoutUser.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }
        if (edtPhone.getText().toString().isEmpty()){
            layoutPhone.setErrorEnabled(true);
            layoutPhone.setError("Vui lòng nhập số điện thoại");
            return false;
        }
        if (edtPass.getText().toString().isEmpty()){
            layoutPass.setErrorEnabled(true);
            layoutPass.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        if (edtConfim.getText().toString().isEmpty()){
            layoutConfim.setErrorEnabled(true);
            layoutConfim.setError("Vui lòng Xác nhận mật khẩu");
            return false;
        }
        return true;
    }


    private void SignUp(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        String ten = edtUser.getText().toString();
        String sdt = edtPhone.getText().toString();
        String mk = edtPass.getText().toString();
        String mk1 = edtConfim.getText().toString();
        String diachi = AddTinhThanhAdapter.diachi.toString();

        if (!ten.isEmpty() || !sdt.isEmpty() || !mk.isEmpty() || !mk1.isEmpty()) {
            if (mk.equals(mk1)) {
                User user = new User(ten, diachi, mk, sdt, "");
                reference.child("KhachHang").push().setValue(user);
                progressDialog.dismiss();
                Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUp_Activity.this, SigIn_Activity.class));
            }else{
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }


    }

}
