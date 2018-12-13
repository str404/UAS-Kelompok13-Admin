package rp.satria.sepatugucci.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import rp.satria.sepatugucci.LayarEditPembeli;
import rp.satria.sepatugucci.LayarEditSepatu;
import rp.satria.sepatugucci.Model.Sepatu;
import rp.satria.sepatugucci.Model.customer;
import rp.satria.sepatugucci.R;
import rp.satria.sepatugucci.Rest.ApiClient;

public class SepatuAdapter extends RecyclerView.Adapter<SepatuAdapter.SepatuViewHolder> {
    List<Sepatu> listSepatu;

    public SepatuAdapter(List<Sepatu> listSepatu) {
        this.listSepatu = listSepatu;
    }

    @Override
    public SepatuAdapter.SepatuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sepatu, parent, false);
        SepatuAdapter.SepatuViewHolder mHolder = new SepatuAdapter.SepatuViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(SepatuAdapter.SepatuViewHolder holder, final int position) {

//        holder.tvIdPembeli.setText(listPembeli.get(position).getIdPembeli());
        holder.tvNama.setText(listSepatu.get(position).getNama());
        holder.tvTipe.setText(listSepatu.get(position).getTipe());
        holder.tvUkuran.setText(listSepatu.get(position).getUkuran());
        holder.tvHarga.setText(listSepatu.get(position).getHarga());
        if (listSepatu.get(position).getPhotoUrl() != null ){
//            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listPembeli.get(position).getPhotoId())
//                    .into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listSepatu.get
                    (position).getPhotoUrl())
                    .into(holder.mPhotoURL);
        } else {
//          Picasso.with(holder.itemView.getContext()).load(R.drawable.photoid).into(holder
// .mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(R.drawable.default_user).into(holder
                    .mPhotoURL);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LayarEditSepatu.class);
                intent.putExtra("id_sepatu", listSepatu.get(position).getIdSepatu());
                intent.putExtra("nama", listSepatu.get(position).getNama());
                intent.putExtra("tipe", listSepatu.get(position).getTipe());
                intent.putExtra("ukuran", listSepatu.get(position).getUkuran());
                intent.putExtra("harga", listSepatu.get(position).getHarga());
                intent.putExtra("photo_url", listSepatu.get(position).getPhotoUrl());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSepatu.size();
    }

    public class SepatuViewHolder extends RecyclerView.ViewHolder {
        ImageView mPhotoURL;
        TextView tvNama, tvTipe, tvUkuran, tvHarga;
//        tvIdPembeli,


        public SepatuViewHolder(View itemView) {
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.shoes_img);
//            tvIdPembeli = (TextView) itemView.findViewById(R.id.tvIdPembeli);
            tvNama = (TextView) itemView.findViewById(R.id.shoes_name);
            tvTipe = (TextView) itemView.findViewById(R.id.shoes_tipe);
            tvUkuran = (TextView) itemView.findViewById(R.id.shoes_ukuran);
            tvHarga = (TextView) itemView.findViewById(R.id.shoes_harga);
        }
    }
}
