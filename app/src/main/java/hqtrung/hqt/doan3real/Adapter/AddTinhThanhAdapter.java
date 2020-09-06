package hqtrung.hqt.doan3real.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hqtrung.hqt.doan3real.Activity.DiaChi_Activity;
import hqtrung.hqt.doan3real.Activity.SigIn_Activity;
import hqtrung.hqt.doan3real.Activity.SignUp_Activity;
import hqtrung.hqt.doan3real.Model.CacTinhThanh;
import hqtrung.hqt.doan3real.R;

public class AddTinhThanhAdapter extends RecyclerView.Adapter<AddTinhThanhAdapter.ViewHolder> {
    public static String ten;
    public static String id;
    private Context context;
    private ArrayList<CacTinhThanh> arrayList;
    public  static Bundle bundle;
    Handler handler;
    public static String diachi;




    public AddTinhThanhAdapter(Context context, ArrayList<CacTinhThanh> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AddTinhThanhAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context  = parent.getContext();
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_dong_tinh_thanh, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddTinhThanhAdapter.ViewHolder holder, int position) {
        final CacTinhThanh tt = arrayList.get(position);
        holder.txtTT.setText(tt.getDiaChi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diachi = tt.getDiaChi().toString();
                DiaChi_Activity.dialog.dismiss();
                DiaChi_Activity.txtDC.setText(diachi);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        context.startActivity(new Intent(context, SignUp_Activity.class));
                    }
                }, 1000);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTT = (TextView) itemView.findViewById(R.id.textViewDialogTT);
        }
    }
}
