package rp.satria.sepatugucci.Model;

import com.google.gson.annotations.SerializedName;

public class Sepatu {
    @SerializedName("id_sepatu")
    private String idSepatu;
    @SerializedName("nama")
    private String nama;
    @SerializedName("tipe")
    private String tipe;
    @SerializedName("ukuran")
    private String ukuran;
    @SerializedName("harga")
    private String harga;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;

    public Sepatu(String idSepatu, String nama, String tipe, String ukuran, String harga, String photoUrl, String
            action) {
        this.idSepatu = idSepatu;
        this.nama = nama;
        this.tipe = tipe;
        this.ukuran = ukuran;
        this.harga = harga;
        this.photoUrl = photoUrl;
        this.action = action;
    }

    public String getIdSepatu() {
        return idSepatu;
    }

    public void setIdSepatu(String idSepatu) {
        this.idSepatu = idSepatu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }
    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
