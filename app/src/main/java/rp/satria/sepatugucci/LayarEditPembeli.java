package rp.satria.sepatugucci;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rp.satria.sepatugucci.Model.GetCustomer;
import rp.satria.sepatugucci.Rest.ApiClient;
import rp.satria.sepatugucci.Rest.ApiInterface;

public class LayarEditPembeli extends AppCompatActivity {

    ImageView mPhotoUrl;
    EditText edtIdCustomer, edtNama, edtAlamat, edtTelp, edtUsername, edtPassword;
    TextView tvMessage;
    Context mContext;
    FloatingActionButton btUpdate, btDelete, btBack, btPhotoUrl;
    String pathImage="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_pembeli);

        mContext = getApplicationContext();

        mPhotoUrl = (ImageView) findViewById(R.id.imgPhotoId);
        edtIdCustomer = (EditText) findViewById(R.id.edtIdCustomer);
        edtNama = (EditText) findViewById(R.id.edtNamaCustomer);
        edtAlamat = (EditText) findViewById(R.id.edtAlamatCustomer);
        edtTelp = (EditText) findViewById(R.id.edtTelpnCustomer);
        edtUsername= findViewById(R.id.edtUsernameCustomer);
        edtPassword = findViewById(R.id.edtPasswordCustomer);

//        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);
        btBack = findViewById(R.id.btBack);
        btPhotoUrl = findViewById(R.id.btPhotoId);

        Intent mIntent = getIntent();

        edtIdCustomer.setText(mIntent.getStringExtra("id_customer"));
        edtNama.setText(mIntent.getStringExtra("nama"));
        edtAlamat.setText(mIntent.getStringExtra("alamat"));
        edtTelp.setText(mIntent.getStringExtra("telp"));
        edtUsername.setText(mIntent.getStringExtra("username"));
        edtPassword.setText(mIntent.getStringExtra("password"));

//        if (mIntent.getStringExtra("photo_url").length()>0) Picasso.with(mContext).load
// (ApiClient.BASE_URL + mIntent.getStringExtra("photo_url")).into(mPhotoUrl);
//        else Picasso.with(mContext).load(R.drawable.photoid).into(mPhotoUrl);
        if (mIntent.getStringExtra("photo_url") != null) {
            Glide.with(mContext).load(ApiClient.BASE_URL+mIntent.getStringExtra("photo_url")).into(mPhotoUrl);
        }
        else {
            Glide.with(mContext).load(R.drawable.default_user).into(mPhotoUrl);
        }
        pathImage = mIntent.getStringExtra("photo_url");
        setListener();
    }

    private void setListener() {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MultipartBody.Part body = null;
                //dicek apakah image sama dengan yang ada di server atau berubah
                //jika sama dikirim lagi jika berbeda akan dikirim ke server
                if ((!pathImage.contains("uploads/" + edtIdCustomer.getText().toString())) &&
                        (pathImage.length()>0)){
                    //File creating from selected URL
                    File file = new File(pathImage);

                    // create RequestBody instance from file
                    RequestBody requestFile = RequestBody.create(
                            MediaType.parse("multipart/form-data"), file);

                    // MultipartBody.Part is used to send also the actual file name
                    body = MultipartBody.Part.createFormData("photo_url", file.getName(),
                            requestFile);
                }

                RequestBody reqIdCustomer =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtIdCustomer.getText().toString().isEmpty())?
                                        "" : edtIdCustomer.getText().toString());

                RequestBody reqNama =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtNama.getText().toString().isEmpty())?
                                        "" : edtNama.getText().toString());

                RequestBody reqAlamat =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAlamat.getText().toString().isEmpty())?
                                        "" : edtAlamat.getText().toString());

                RequestBody reqTelp =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtTelp.getText().toString().isEmpty())?
                                        "" : edtTelp.getText().toString());
                RequestBody reqUsername =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtUsername.getText().toString().isEmpty())?
                                        "" : edtUsername.getText().toString());
                RequestBody reqPassword =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtPassword.getText().toString().isEmpty())?
                                        "" : edtPassword.getText().toString());

                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"), "update");

                Call<GetCustomer> callUpdate = mApiInterface.putGetCustomer(body, reqIdCustomer, reqNama,
                        reqAlamat, reqTelp, reqUsername, reqPassword, reqAction);

                callUpdate.enqueue(new Callback<GetCustomer>() {
                    @Override
                    public void onResponse(Call<GetCustomer> call, Response<GetCustomer> response) {
                        //Log.d("Update Retrofit ", response.body().getStatus());
                        if (response.body().getStatus().equals("failed")){
                            Toast.makeText(getApplicationContext(), "Retrofit Update \n Status = " + response.body()
                                            .getStatus()+"\n"+
                                            "Message = "+response.body().getMessage()+"\n",
                                    Toast.LENGTH_LONG).show();
//                            tvMessage.setText("Retrofit Update \n Status = " + response.body()
//                                    .getStatus()+"\n"+
//                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "id_customer = "+response.body().getResult().get(0).getIdCustomer()+"\n"+
                                    "nama = "+response.body().getResult().get(0).getNama()+"\n"+
                                    "alamat = "+response.body().getResult().get(0).getAlamat()+"\n"+
                                    "telp = "+response.body().getResult().get(0).getTelp()+"\n"+
                                    "username = "+response.body().getResult().get(0).getTelp()+"\n"+
                                    "password = "+response.body().getResult().get(0).getTelp()+"\n"+
                                    "photo_url = "+response.body().getResult().get(0).getPhotoUrl()
                                    +"\n";
                            Toast.makeText(getApplicationContext(), "Retrofit Update \n Status = "+response.body().getStatus()+"\n"+
                                            "Message = "+response.body().getMessage()+detail,
                                    Toast.LENGTH_LONG).show();
//                            tvMessage.setText("Retrofit Update \n Status = "+response.body().getStatus()+"\n"+
//                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetCustomer> call, Throwable t) {
                        //Log.d("Update Retrofit ", t.getMessage());
                        Toast.makeText(getApplicationContext(), "Retrofit Update \n Status = "+ t.getMessage(),
                                Toast.LENGTH_LONG).show();
//                        tvMessage.setText("Retrofit Update \n Status = "+ t.getMessage());
                    }
                });

            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody reqIdCustomer =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtIdCustomer.getText().toString().isEmpty())?
                                        "" : edtIdCustomer.getText().toString());
                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"), "delete");

                Call<GetCustomer> callDelete = mApiInterface.deleteCustomer(reqIdCustomer,reqAction);
                callDelete.enqueue(new Callback<GetCustomer>() {
                    @Override
                    public void onResponse(Call<GetCustomer> call, Response<GetCustomer> response) {
                        Toast.makeText(getApplicationContext(), "Retrofit Delete \n Status = "+response.body()
                                        .getStatus() +"\n"+
                                        "Message = "+response.body().getMessage()+"\n",
                                Toast.LENGTH_LONG).show();
//                        tvMessage.setText("Retrofit Delete \n Status = "+response.body()
//                                .getStatus() +"\n"+
//                                "Message = "+response.body().getMessage()+"\n");
                    }

                    @Override
                    public void onFailure(Call<GetCustomer> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Retrofit Delete \n Status = "+ t.getMessage(),
                                Toast.LENGTH_LONG).show();
//                        tvMessage.setText("Retrofit Delete \n Status = "+ t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempIntent = new Intent(mContext, LayarListCustomer.class);
                startActivity(tempIntent);
            }
        });

        btPhotoUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(galleryIntent, "Pilih foto untuk " +
                        "di-upload");
                startActivityForResult(intentChoose, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10) {
            if (data == null) {
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                pathImage = cursor.getString(columnIndex);

                //Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
                Glide.with(mContext).load(new File(pathImage)).into(mPhotoUrl);
                cursor.close();
            } else {
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
            }
        }
    }
}
