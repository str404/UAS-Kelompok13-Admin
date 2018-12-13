package rp.satria.sepatugucci.Model;

import com.google.gson.annotations.SerializedName;

public class Transaksi {

        @SerializedName("id_transaksi")
        private String id_transaksi;
        @SerializedName("id_customer")
        private String id_customer;
        @SerializedName("tgl_transaksi")
        private String tgl_transaksi;
        @SerializedName("total_harga")
        private int total_harga;
        @SerializedName("id_sepatu")
        private String id_sepatu;

        public Transaksi(String id_transaksi, String id_customer, String tgl_transaksi, int total_harga, String id_sepatu) {
            this.id_transaksi = id_transaksi;
            this.id_customer = id_customer;
            this.tgl_transaksi = tgl_transaksi;
            this.total_harga = total_harga;
            this.id_sepatu = id_sepatu;
        }

        public String getId_transaksi() {
            return id_transaksi;
        }

        public void setId_transaksi(String id_transaksi) {
            this.id_transaksi = id_transaksi;
        }

        public String getId_customer() {
            return id_customer;
        }

        public void setId_customer(String id_customer) {
            this.id_customer = id_customer;
        }

        public String getTgl_transaksi() {
            return tgl_transaksi;
        }

        public void setTgl_transaksi(String tgl_transaksi) {
            this.tgl_transaksi = tgl_transaksi;
        }

        public int getTotal_harga() {
            return total_harga;
        }

        public void setTotal_harga(int total_harga) {
            this.total_harga = total_harga;
        }

        public String getId_sepatu() {
            return id_sepatu;
        }

        public void setId_sepatu(String id_sepatu) {
            this.id_sepatu = id_sepatu;
        }

}
