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
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    private String action;

    public customer(String idCustomer, String nama, String alamat, String telp, String photoUrl, String username, String password, String
            action) {
        this.idCustomer = idCustomer;
        this.nama = nama;
        this.alamat = alamat;
        this.telp = telp;
        this.photoUrl = photoUrl;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
