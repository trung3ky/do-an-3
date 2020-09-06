package hqtrung.hqt.doan3real.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hqtrung.hqt.doan3real.R;

public class SigIn_Activity extends AppCompatActivity {

    TextInputLayout layoutPhone, layoutPass;
    EditText edtPhone, edtPass;
    Button btnSI;
    TextView txtSU;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_in_);
        AnhXa();
    }
    private void AnhXa() {
        layoutPhone = (TextInputLayout) findViewById(R.id.LayoutPhoneSI);
        layoutPass = (TextInputLayout) findViewById(R.id.LayoutPassSI);
        edtPhone = (EditText) findViewById(R.id.EditTextPhoneSI);
        edtPass = (EditText) findViewById(R.id.EditTextPassSI);
        btnSI = (Button) findViewById(R.id.buttonSignIn);
        txtSU = (TextView) findViewById(R.id.TextViewSignUp);

        btnSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    SignIn();
                }
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

        txtSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigIn_Activity.this, DiaChi_Activity.class));
            }
        });
    }
    private boolean validate(){
        if (edtPhone.getText().toString().isEmpty()){
            layoutPhone.setErrorEnabled(true);
            layoutPhone.setError("Vui lòng nhập địa chỉ email");
            return false;
        }
        if (edtPass.getText().toString().isEmpty()){
            layoutPass.setErrorEnabled(true);
            layoutPass.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        return true;
    }

    private void SignIn(){
        final String phone = edtPhone.getText().toString();
        final String password = edtPass.getText().toString();
        if (!phone.isEmpty() && !password.isEmpty()){
            database = FirebaseDatabase.getInstance();
            reference = database.getReference().child("KhachHang");
            reference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    if (!phone.toString().equals(snapshot.child("sodienthoai").getValue().toString()) || !password.toString().equals(snapshot.child("matkhau").getValue().toString())){

                    }else{
                        String key = snapshot.getKey().toString();
                        Intent intent = new Intent(SigIn_Activity.this, MainActivity.class);
                        intent.putExtra("key",key);
                        startActivity(intent);

                        Toast.makeText(SigIn_Activity.this, "Đăng nhập hành công", Toast.LENGTH_SHORT).show();
                    }
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
    }
}
