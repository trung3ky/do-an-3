package hqtrung.hqt.doan3real.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hqtrung.hqt.doan3real.Activity.ChiTietBaiDoXeActivity;
import hqtrung.hqt.doan3real.Fragment.Fragment_DanhMuc;
import hqtrung.hqt.doan3real.Model.BaiDoXe;
import hqtrung.hqt.doan3real.R;

public class BaiDoXeAdapter extends RecyclerView.Adapter<BaiDoXeAdapter.ViewHolder> {
    private Context context;
    public static ArrayList<BaiDoXe> arrayList;
    public static String key = "";

    public BaiDoXeAdapter(Context context, ArrayList<BaiDoXe> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_do_xe, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final BaiDoXe bdx = arrayList.get(position);
        Picasso.get().load(bdx.getHinh_anh()).into(holder.imgAnh);
        holder.txtName.setText(bdx.getTen());
        holder.txtDiaChi.setText(bdx.getDiaChi());
        holder.txtClock.setText(bdx.getGio());
        if (bdx.getConlai().equals("0")){
            holder.txtTrangThai.setText("Hết chổ");
        }else{
            holder.txtTrangThai.setText(bdx.getConlai()+" vị trí còn trống");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = bdx.getKey().toString();
                Intent intent = new Intent(context, ChiTietBaiDoXeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("key", arrayList.get(position).getKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filterList(ArrayList<BaiDoXe> filterList){
        if (filterList.size() > 0){
            Fragment_DanhMuc.layoutTb.setVisibility(View.GONE);
            arrayList = filterList;
            notifyDataSetChanged();
        }else{
            arrayList.clear();
            Fragment_DanhMuc.layoutTb.setVisibility(View.VISIBLE);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnh;
        TextView txtName, txtDiaChi, txtClock, txtTrangThai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnh = (ImageView) itemView.findViewById(R.id.imageViewBaiDoXe);
            txtName = (TextView) itemView.findViewById(R.id.textViewBaiDoXe);
            txtDiaChi = (TextView) itemView.findViewById(R.id.TextViewDiaChi);
            txtClock = (TextView)  itemView.findViewById(R.id.TextViewClock);
            txtTrangThai = (TextView) itemView.findViewById(R.id.TextViewTrangThai);
        }
    }
}
