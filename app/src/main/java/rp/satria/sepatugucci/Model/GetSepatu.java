package rp.satria.sepatugucci.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetSepatu {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Sepatu> result = new ArrayList<Sepatu>();
    @SerializedName("message")
    private String message;
    public GetSepatu() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Sepatu> getResult() {
        return result;
    }

    public void setResult(List<Sepatu> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
