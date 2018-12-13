package rp.satria.sepatugucci.Model;

import com.google.gson.annotations.SerializedName;

public class PosPutDelTransaksi {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    Transaksi mTransaksi;
    @SerializedName("message")
    String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Transaksi getTransaksi() {
        return mTransaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        mTransaksi = transaksi;
    }
}
