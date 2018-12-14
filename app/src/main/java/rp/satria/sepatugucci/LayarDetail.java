package rp.satria.sepatugucci;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rp.satria.sepatugucci.Model.PosPutDelTransaksi;
import rp.satria.sepatugucci.Model.Transaksi;
import rp.satria.sepatugucci.Rest.ApiClient;
import rp.satria.sepatugucci.Rest.ApiInterface;

public class LayarDetail extends AppCompatActivity {

    EditText edtIdTransaksi, edtIdCustomer, edtTanggalTransaksi, edtIdSepatu, edtTotalHarga;
    FloatingActionButton btInsert , btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail);

        edtIdTransaksi = (EditText) findViewById(R.id.edtIdTransaksi);
        edtIdCustomer = (EditText) findViewById(R.id.edtIdCustomer);
        edtTanggalTransaksi = (EditText) findViewById(R.id.edtTanggalTransaksi);
        edtIdSepatu = (EditText) findViewById(R.id.edtIdSepatu);
        edtTotalHarga = (EditText) findViewById(R.id.edtTotalHarga);
//        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = findViewById(R.id.btInsert2);
        btUpdate = findViewById(R.id.btUpdate2);
        btDelete = findViewById(R.id.btDelete2);
        btBack = findViewById(R.id.btBack);



        Intent mIntent = getIntent();
        edtIdTransaksi.setText(mIntent.getStringExtra("id_transaksi"));
        edtIdCustomer.setText(mIntent.getStringExtra("id_customer"));
        edtTanggalTransaksi.setText(mIntent.getStringExtra("tanggal_transaksi"));
        edtIdSepatu.setText(mIntent.getStringExtra("id_sepatu"));
        edtTotalHarga.setText(mIntent.getStringExtra("total_harga"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PosPutDelTransaksi> updateTransaksiCall = mApiInterface.putTransaksi(
                        edtIdTransaksi.getText().toString(),
                        edtIdCustomer.getText().toString(),
                        edtTanggalTransaksi.getText().toString(),
                        edtTotalHarga.getText().toString(),
                        edtIdSepatu.getText().toString());

                updateTransaksiCall.enqueue(new Callback<PosPutDelTransaksi>() {
                    @Override
                    public void onResponse(Call<PosPutDelTransaksi> call, Response<PosPutDelTransaksi> response) {
                        Toast.makeText(getApplicationContext(), " Retrofit Update: " +
                                        "\n " + " Status Update : " +response.body().getStatus() +
                                        "\n " + " Message Update : "+ response.body().getMessage(),
                                Toast.LENGTH_LONG).show();
//                        tvMessage.setText();
                    }

                    @Override
                    public void onFailure(Call<PosPutDelTransaksi> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Retrofit Update: \n Status Update :"+ t.getMessage(),
                                Toast.LENGTH_LONG).show();
//                        tvMessage.setText();
                    }
                });
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PosPutDelTransaksi> postPembelianCall = mApiInterface.postTransaksi(
                        edtIdTransaksi.getText().toString(),
                        edtIdCustomer.getText().toString(),
                        edtTanggalTransaksi.getText().toString(),
                        edtTotalHarga.getText().toString(),
                        edtIdSepatu.getText().toString());

                postPembelianCall.enqueue(new Callback<PosPutDelTransaksi>() {
                    @Override
                    public void onResponse(Call<PosPutDelTransaksi> call, Response<PosPutDelTransaksi> response) {
                        Toast.makeText(getApplicationContext(), " Retrofit Insert: " +
                                        "\n " + " Status Insert : " +
                                        response.body().getStatus() +
                                        "\n " + " Message Insert : "+ response.body().getMessage(),
                                Toast.LENGTH_LONG).show();
//                        tvMessage.setText();
                    }

                    @Override
                    public void onFailure(Call<PosPutDelTransaksi> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Retrofit Insert: \n Status Insert :"+ t.getMessage(),
                                Toast.LENGTH_LONG).show();
//                        tvMessage.setText();
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtIdTransaksi.getText().toString().trim().isEmpty()){

                    Call<PosPutDelTransaksi> deletePembelian = mApiInterface.deleteTransaksi(edtIdTransaksi.getText().toString());
                    deletePembelian.enqueue(new Callback<PosPutDelTransaksi>() {
                        @Override
                        public void onResponse(Call<PosPutDelTransaksi> call, Response<PosPutDelTransaksi> response) {
                            Toast.makeText(getApplicationContext(), " Retrofit Delete: " +
                                            "\n " + " Status Delete : " +response.body().getStatus() +
                                            "\n " + " Message Delete : "+ response.body().getMessage(),
                                    Toast.LENGTH_LONG).show();
//                            tvMessage.setText();
                        }

                        @Override
                        public void onFailure(Call<PosPutDelTransaksi> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Retrofit Delete: \n Status Delete :"+ t.getMessage(),
                                    Toast.LENGTH_LONG).show();
//                            tvMessage.setText();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "id_transaksi harus diisi",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(), TransaksiActivity.class);
                startActivity(mIntent);
            }
        });

    }
}
