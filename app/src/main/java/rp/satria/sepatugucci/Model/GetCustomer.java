package rp.satria.sepatugucci.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetCustomer {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<customer> result = new ArrayList<customer>();
    @SerializedName("message")
    private String message;
    public GetCustomer() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<customer> getResult() {
        return result;
    }

    public void setResult(List<customer> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
