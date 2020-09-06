package hqtrung.hqt.doan3real.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hqtrung.hqt.doan3real.Activity.MainActivity;
import hqtrung.hqt.doan3real.Activity.SigIn_Activity;
import hqtrung.hqt.doan3real.Activity.SuaThongTinCaNhan_Activity;
import hqtrung.hqt.doan3real.R;

public class Fragment_User extends Fragment {
    TextView txtName, txtSdt, txtEdit, txtLogOut, txtLogIn;
    RelativeLayout layoutLogIn, layoutLogOut;
    View view;

    FirebaseDatabase database;
    DatabaseReference reference;

    public static String key;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        key = MainActivity.preferences.getString("key", "");

        AnhXa();
        GetData();

        return view;
    }

    private void GetData() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("KhachHang");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (key.equals(snapshot.getKey().toString())){
                    txtName.setText(snapshot.child("ten").getValue().toString());
                    txtSdt.setText(snapshot.child("sodienthoai").getValue().toString());
                    layoutLogOut.setVisibility(View.VISIBLE);
                    layoutLogIn.setVisibility(View.GONE);


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

    private void AnhXa(){
        txtName = (TextView) view.findViewById(R.id.name);
        txtSdt = (TextView) view.findViewById(R.id.sdt);
        txtEdit = (TextView) view.findViewById(R.id.sua_thong_tin);
        txtLogOut = (TextView) view.findViewById(R.id.Dang_xuat);
        txtLogIn = (TextView) view.findViewById(R.id.Dang_Nhap);
        layoutLogIn = (RelativeLayout) view.findViewById(R.id.LayOut_SignIn);
        layoutLogOut = (RelativeLayout) view.findViewById(R.id.LayOut_SignUp);

        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn đăng xuất tài khoản này không?");
                builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.preferences.edit();
                        MainActivity.editor.clear();
                        MainActivity.editor.apply();
                        layoutLogIn.setVisibility(View.VISIBLE);
                        layoutLogOut.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();

//                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Framlayout, new Fragment_User()).commit();
//                        MainActivity.bottomNavigationView.setSelectedItemId(R.id.More);
                        startActivity(new Intent(getActivity(), SigIn_Activity.class));
                    }
                });
                builder.show();

//                Fragment fragment = new Fragment_User();
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                activity.getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.Framlayout, fragment)
//                        .commit();
            }
        });
        txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SigIn_Activity.class));
            }
        });

        if (!key.equals("")){
            txtEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), SuaThongTinCaNhan_Activity.class));
                }
            });
        }

    }
}
