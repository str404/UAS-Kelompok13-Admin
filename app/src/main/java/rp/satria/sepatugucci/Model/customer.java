package rp.satria.sepatugucci.Model;

import com.google.gson.annotations.SerializedName;

public class customer {
    @SerializedName("id_customer")
    private String idCustomer;
    @SerializedName("nama")
    private String nama;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("telp")
    private String telp;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;

    public customer(String idCustomer, String nama, String alamat, String telp, String photoUrl, String
            action) {
        this.idCustomer = idCustomer;
        this.nama = nama;
        this.alamat = alamat;
        this.telp = telp;
        this.photoUrl = photoUrl;
        this.action = action;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
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
