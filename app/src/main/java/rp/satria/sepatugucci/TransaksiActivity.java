package rp.satria.sepatugucci;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rp.satria.sepatugucci.Adapter.MyAdapter;
import rp.satria.sepatugucci.Model.GetTransaksi;
//import rp.satria.sepatugucci.Model.PosPutDelTransaksi;
import rp.satria.sepatugucci.Model.Transaksi;
import rp.satria.sepatugucci.Rest.ApiClient;
import rp.satria.sepatugucci.Rest.ApiInterface;

public class TransaksiActivity extends AppCompatActivity {

//    FloatingActionButton btGet, btUpdate, btInsert, btDelete;
    FloatingActionButton btGet;
    ApiInterface mApiInterface;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        btGet = findViewById(R.id.btGet);
//        btUpdate = findViewById(R.id.btUpdate);
//        btInsert = findViewById(R.id.btInsert);
//        btDelete = findViewById(R.id.btDelete);

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface  = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetTransaksi> transaksiCall = mApiInterface.getTransaksi();
                transaksiCall.enqueue(new Callback<GetTransaksi>() {
                    @Override
                    public void onResponse(Call<GetTransaksi> call, Response<GetTransaksi> response) {
                        List<Transaksi> transaksiList = response.body().getListDataTransaksi();
                        Log.d("Retrofit Get", "Jumlah data transaksi: " + String.valueOf(transaksiList.size()));
                        mAdapter = new MyAdapter(transaksiList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetTransaksi> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });

//        btUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<PosPutDelTransaksi> updateTransaksiCall = mApiInterface.putTransaksi("6","1","2018-10-23","500000","1");
//                updateTransaksiCall.enqueue(new Callback<PosPutDelTransaksi>() {
//                    @Override
//                    public void onResponse(Call<PosPutDelTransaksi> call, Response<PosPutDelTransaksi> response) {
//                        Log.d("Retrofit Update", "Status Update: " + String.valueOf(response.body().getStatus()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<PosPutDelTransaksi> call, Throwable t) {
//                        Log.d("Retrofit Update", t.getMessage());
//                    }
//                });
//            }
//        });
//
//        btInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<PosPutDelTransaksi> postTransaksiCall = mApiInterface.postTransaksi("6", "1","2018-10-23","500000","1");
//                postTransaksiCall.enqueue(new Callback<PosPutDelTransaksi>() {
//                    @Override
//                    public void onResponse(Call<PosPutDelTransaksi> call, Response<PosPutDelTransaksi> response) {
//                        Log.d("Retrofit Insert", "Status Insert: " + String.valueOf(response.body().getStatus()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<PosPutDelTransaksi> call, Throwable t) {
//                        Log.d("Retrofit Insert", t.getMessage());
//                    }
//                });
//            }
//        });
//
//        btDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<PosPutDelTransaksi> deleteTransaksi = mApiInterface.deleteTransaksi("6");
//                deleteTransaksi.enqueue(new Callback<PosPutDelTransaksi>() {
//                    @Override
//                    public void onResponse(Call<PosPutDelTransaksi> call, Response<PosPutDelTransaksi> response) {
//                        Log.i("Retrofit Delete", "Status Delete: " + String.valueOf(response.body().getStatus()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<PosPutDelTransaksi> call, Throwable t) {
//                        Log.i("Retrofit Delete", t.getMessage());
//                    }
//                });
//            }
//        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {

            case R.id.TambahTransaksi:
                mIntent = new Intent(this, LayarDetail.class);
                startActivity(mIntent);
                return true;

            case R.id.ListTransaksi:
                mIntent = new Intent(this, TransaksiActivity.class);
                startActivity(mIntent);
                return true;

            case R.id.InsertDataCustomer:
                Intent intent = new Intent(this, LayarInsertCustomer.class);
                startActivity(intent);
                return true;

            case R.id.ListCustomer:
                mIntent = new Intent(this, LayarListCustomer.class);
                startActivity(mIntent);
                return true;

            case R.id.InsertDataSepatu:
                Intent intent2 = new Intent(this, LayarInsertSepatu.class);
                startActivity(intent2);
                return true;

            case R.id.ListSepatu:
                mIntent = new Intent(this, LayarListSepatu.class);
                startActivity(mIntent);
                return true;

            case R.id.Logout:
                SharedPreferences handler = getSharedPreferences("LoginActivity", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = handler.edit();

                editor.clear();
                editor.apply();
                mIntent = new Intent(this, LoginActivity.class);
                startActivity(mIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
