package rp.satria.sepatugucci.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rp.satria.sepatugucci.Model.Transaksi;
import rp.satria.sepatugucci.LayarDetail;
import rp.satria.sepatugucci.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Transaksi> mTransaksiList;

    public MyAdapter(List<Transaksi> transaksiList) {
        mTransaksiList = transaksiList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewIdTransaksi.setText("Id Transaksi :  " + mTransaksiList.get(position)
                .getId_transaksi());
        holder.mTextViewIdCustomer.setText("Id Customer :  " + mTransaksiList.get(position)
                .getId_customer());
        holder.mTextViewIdSepatu.setText("Id Sepatu :  " + mTransaksiList.get(position).getId_sepatu());
        holder.mTextViewTanggal.setText("Tanggal Transaksi :  " + mTransaksiList.get(position)
                .getTgl_transaksi());
        holder.mTextViewTotalHarga.setText("Total Harga :  " + String.valueOf(mTransaksiList.get
                (position).getTotal_harga()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {

                                               @Override
                                               public void  onClick(View view) {
                                                   Intent mIntent = new Intent(view.getContext(), LayarDetail.class);
                                                   mIntent.putExtra("id_transaksi", mTransaksiList.get(position).getId_transaksi());
                                                   mIntent.putExtra("id_customer", mTransaksiList.get(position).getId_customer());
                                                   mIntent.putExtra("tanggal_transaksi", mTransaksiList.get(position).getTgl_transaksi());
                                                   mIntent.putExtra("id_sepatu", mTransaksiList.get(position).getId_sepatu());
                                                   mIntent.putExtra("total_harga", String.valueOf(mTransaksiList.get(position).getTotal_harga()));
                                                   view.getContext().startActivity(mIntent);
                                               }
                                           }
        );
    }

    @Override
    public int getItemCount() {
        return mTransaksiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdTransaksi, mTextViewIdCustomer, mTextViewTanggal,mTextViewIdSepatu,mTextViewTotalHarga;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdTransaksi = (TextView) itemView.findViewById(R.id.tvIdTransaksi);
            mTextViewIdCustomer = (TextView) itemView.findViewById(R.id.tvIdCustomer);
            mTextViewTanggal = (TextView) itemView.findViewById(R.id.tvTanggalTransaksi);
            mTextViewIdSepatu = (TextView) itemView.findViewById(R.id.tvIdSepatu);
            mTextViewTotalHarga = (TextView) itemView.findViewById(R.id.tvTotalHarga);
        }
    }
}
