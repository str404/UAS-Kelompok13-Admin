package rp.satria.sepatugucci;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import rp.satria.sepatugucci.Adapter.SepatuAdapter;
import rp.satria.sepatugucci.Model.GetSepatu;
import rp.satria.sepatugucci.Model.Sepatu;
import rp.satria.sepatugucci.Rest.ApiClient;
import rp.satria.sepatugucci.Rest.ApiInterface;

public class LayarListSepatu extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    FloatingActionButton btGet, btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_sepatu);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btGet = findViewById(R.id.btGet);
        btAddData = findViewById(R.id.btAddData);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetSepatu> mSepatuCall = mApiInterface.getSepatu();
                mSepatuCall.enqueue(new Callback<GetSepatu>() {
                    @Override
                    public void onResponse(Call<GetSepatu> call, Response<GetSepatu> response) {
                        Log.d("Get Sepatu",response.body().getStatus());
                        List<Sepatu> listSepatu = response.body().getResult();
                        mAdapter = new SepatuAdapter(listSepatu);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetSepatu> call, Throwable t) {
                        Log.d("Get Sepatu",t.getMessage());
                    }
                });
            }
        });

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarInsertSepatu.class);
                startActivity(intent);
            }
        });



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
