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
import rp.satria.sepatugucci.Adapter.CustomerAdapter;
import rp.satria.sepatugucci.Model.GetCustomer;
import rp.satria.sepatugucci.Model.customer;
import rp.satria.sepatugucci.Rest.ApiClient;
import rp.satria.sepatugucci.Rest.ApiInterface;

public class LayarListCustomer extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    FloatingActionButton btGet, btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_customer);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btGet = findViewById(R.id.btGet);
        btAddData = findViewById(R.id.btAddData);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetCustomer> mCustomerCall = mApiInterface.getCustomer();
                mCustomerCall.enqueue(new Callback<GetCustomer>() {
                    @Override
                    public void onResponse(Call<GetCustomer> call, Response<GetCustomer> response) {
                        Log.d("Get Customer",response.body().getStatus());
                        List<customer> listCustomer = response.body().getResult();
                        mAdapter = new CustomerAdapter(listCustomer);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetCustomer> call, Throwable t) {
                        Log.d("Get Customer",t.getMessage());
                    }
                });
            }
        });

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarInsertCustomer.class);
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
