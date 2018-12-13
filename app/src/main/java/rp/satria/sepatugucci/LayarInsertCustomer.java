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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

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

public class LayarInsertCustomer extends AppCompatActivity {

    Context mContext;
    ImageView mImageView;
    FloatingActionButton btAddPhotoId, btAddBack, btAddData;
    EditText edtAddIdPembeli, edtAddNamaCustomer, edtAddAlamatCustomer;
    EditText edtAddTelpCustomer;
    //    TextView tvAddMessage;
    String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_customer);

        mContext = getApplicationContext();
        mImageView = (ImageView) findViewById(R.id.imgAddPhotoId);
        btAddPhotoId = findViewById(R.id.btAddPhotoId);
//        edtAddIdPembeli = (EditText) findViewById(R.id.edtAddIdPembeli);
        edtAddNamaCustomer = (EditText) findViewById(R.id.edtAddNamaCustomer);
        edtAddAlamatCustomer = (EditText) findViewById(R.id.edtAddAlamatCustomer);
        edtAddTelpCustomer = (EditText) findViewById(R.id.edtAddTelpCustomer);

        btAddData = findViewById(R.id.btAddData);
        btAddBack = findViewById(R.id.btAddBack);
//        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()){
                    // Buat file dari image yang dipilih
                    File file = new File(imagePath);

                    // Buat RequestBody instance dari file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);

                    // MultipartBody.Part digunakan untuk mendapatkan nama file
                    body = MultipartBody.Part.createFormData("photo_url", file.getName(),
                            requestFile);
                }
                RequestBody reqNama = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaCustomer.getText().toString().isEmpty())?"":edtAddNamaCustomer.getText().toString());
                RequestBody reqAlamat = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaCustomer.getText().toString().isEmpty())?"":edtAddAlamatCustomer.getText().toString());
                RequestBody reqTelp = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaCustomer.getText().toString().isEmpty())?"":edtAddTelpCustomer.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "insert");
                Call<GetCustomer> mCustomerCall = mApiInterface.postGetCustomer(body, reqNama,
                        reqAlamat, reqTelp, reqAction );
                mCustomerCall.enqueue(new Callback<GetCustomer>() {
                    @Override
                    public void onResponse(Call<GetCustomer> call, Response<GetCustomer> response) {
//                      Log.d("Insert Retrofit",response.body().getMessage());
                        if (response.body().getStatus().equals("failed")){
                            Toast.makeText(getApplicationContext(), "Retrofit Insert \n Status = "+response.body()
                                            .getStatus()+"\n"+
                                            "Message = "+response.body().getMessage()+"\n",
                                    Toast.LENGTH_LONG).show();
//                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body()
//                                    .getStatus()+"\n"+
//                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "id_customer = "+response.body().getResult().get(0).getIdCustomer()+"\n"+
                                    "nama = "+response.body().getResult().get(0).getNama()+"\n"+
                                    "alamat = "+response.body().getResult().get(0).getAlamat()+"\n"+
                                    "telp = "+response.body().getResult().get(0).getTelp()+"\n"+
                                    "photo_url = "+response.body().getResult().get(0).getPhotoUrl()
                                    +"\n";
                            Toast.makeText(getApplicationContext(), "Retrofit Insert \n Status = "+response.body().getStatus()+"\n"+
                                            "Message = "+response.body().getMessage()+detail,
                                    Toast.LENGTH_LONG).show();
//                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body().getStatus()+"\n"+
//                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetCustomer> call, Throwable t) {
//                      Log.d("Insert Retrofit", t.getMessage());
                        Toast.makeText(getApplicationContext(), "Retrofit Insert Failure \n Status = "+ t.getMessage
                                        (),
                                Toast.LENGTH_LONG).show();
//                        tvAddMessage.setText("Retrofit Insert Failure \n Status = "+ t.getMessage
//                                ());
                    }
                });
            }
        });
        btAddBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarListCustomer.class);
                startActivity(intent);
            }
        });
        btAddPhotoId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(
                        galleryIntent,
                        "Pilih foto untuk di-upload");
                startActivityForResult(intentChoose, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10){
            if (data==null){
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
                return;
            }

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath =cursor.getString(columnIndex);

                Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
//                Glide.with(mContext).load(new File(imagePath)).into(mImageView);
                cursor.close();
            }else{
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
            }
        }

    }
}
