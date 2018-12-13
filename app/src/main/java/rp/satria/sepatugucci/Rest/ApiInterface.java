package rp.satria.sepatugucci.Rest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import rp.satria.sepatugucci.Model.GetCustomer;
import rp.satria.sepatugucci.Model.GetSepatu;
import rp.satria.sepatugucci.Model.GetTransaksi;
import rp.satria.sepatugucci.Model.PosPutDelTransaksi;

public interface ApiInterface {
    @GET("transaksi/user")
    Call<GetTransaksi> getTransaksi();




    @FormUrlEncoded
    @POST("transaksi/user")
    Call<PosPutDelTransaksi> postTransaksi
            (@Field("id_transaksi") String idTransaksi, @Field("id_customer") String idCustomer,
             @Field("tgl_transaksi") String tglTransaksi, @Field("total_harga") String totalHarga,
             @Field("id_sepatu") String idSepatu);

    @FormUrlEncoded
    @PUT("transaksi/user")
    Call<PosPutDelTransaksi> putTransaksi(
            @Field("id_transaksi") String idTransaksi, @Field("id_customer") String idCustomer,
            @Field("tgl_transaksi") String tglTransaksi, @Field("total_harga") String totalHarga,
            @Field("id_sepatu") String idSepatu);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "transaksi/user", hasBody = true)
    Call<PosPutDelTransaksi> deleteTransaksi(@Field("id_transaksi") String idTransaksi);

    /********* PEMBELI *********/
    @GET("customer/all")
    Call<GetCustomer> getCustomer();

    @Multipart
    @POST("customer/all")
    Call<GetCustomer> postGetCustomer(
            @Part MultipartBody.Part file,
            @Part("nama") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("telp") RequestBody telpn,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("customer/all")
    Call<GetCustomer> putGetCustomer(
            @Part MultipartBody.Part file,
            @Part("id_customer") RequestBody idCustomer,
            @Part("nama") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("telp") RequestBody telpn,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("customer/all")
    Call<GetCustomer> deleteCustomer(
            @Part("id_customer") RequestBody idCustomer,
            @Part("action") RequestBody action);

    /********* SEPATU *********/
    @GET("sepatu/all")
    Call<GetSepatu> getSepatu();

    @Multipart
    @POST("sepatu/all")
    Call<GetSepatu> postSepatu(
            @Part MultipartBody.Part file,
            @Part("nama") RequestBody nama,
            @Part("tipe") RequestBody tipe,
            @Part("ukuran") RequestBody ukuran,
            @Part("harga") RequestBody harga,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("sepatu/all")
    Call<GetSepatu> putSepatu(
            @Part MultipartBody.Part file,
            @Part("id_sepatu") RequestBody idSepatu,
            @Part("nama") RequestBody nama,
            @Part("tipe") RequestBody tipe,
            @Part("ukuran") RequestBody ukuran,
            @Part("harga") RequestBody harga,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("sepatu/all")
    Call<GetSepatu> deleteSepatu(
            @Part("id_sepatu") RequestBody idSepatu,
            @Part("action") RequestBody action);

}
