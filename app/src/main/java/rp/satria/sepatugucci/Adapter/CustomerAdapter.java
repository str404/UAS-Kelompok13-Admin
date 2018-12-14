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
import rp.satria.sepatugucci.Model.customer;
import rp.satria.sepatugucci.R;
import rp.satria.sepatugucci.Rest.ApiClient;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    List<customer> listCustomer;

    public CustomerAdapter(List<customer> listCustomer) {
        this.listCustomer = listCustomer;
    }

    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customer, parent, false);
        CustomerViewHolder mHolder = new CustomerViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(CustomerAdapter.CustomerViewHolder holder, final int position) {

//        holder.tvIdPembeli.setText(listPembeli.get(position).getIdPembeli());
        holder.tvNama.setText(listCustomer.get(position).getNama());
        holder.tvAlamat.setText(listCustomer.get(position).getAlamat());
        holder.tvTelp.setText(listCustomer.get(position).getTelp());
        holder.tvUsername.setText(listCustomer.get(position).getUsername());
//        holder.tvPassword.setText(listCustomer.get(position).getPassword());
        if (listCustomer.get(position).getPhotoUrl() != null ){
//            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listPembeli.get(position).getPhotoId())
//                    .into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listCustomer.get
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
                Intent intent = new Intent(view.getContext(), LayarEditPembeli.class);
                intent.putExtra("id_customer", listCustomer.get(position).getIdCustomer());
                intent.putExtra("nama", listCustomer.get(position).getNama());
                intent.putExtra("alamat", listCustomer.get(position).getAlamat());
                intent.putExtra("telp", listCustomer.get(position).getTelp());
                intent.putExtra("photo_url", listCustomer.get(position).getPhotoUrl());
                intent.putExtra("username", listCustomer.get(position).getUsername());
                intent.putExtra("password", listCustomer.get(position).getPassword());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCustomer.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        ImageView mPhotoURL;
        TextView tvNama, tvAlamat, tvTelp, tvUsername, tvPassword;
//        tvIdPembeli,


        public CustomerViewHolder(View itemView) {
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.imgCustomer);
//            tvIdPembeli = (TextView) itemView.findViewById(R.id.tvIdPembeli);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamatContent);
            tvTelp = (TextView) itemView.findViewById(R.id.tvTelpContent);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
//            tvPassword = (TextView) itemView.findViewById(R.id.tvPassword);
        }
    }
}
