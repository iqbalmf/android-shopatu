package net.iqbalfauzan.shopatu;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.iqbalfauzan.shopatu.AccessDB.Api;
import net.iqbalfauzan.shopatu.AccessDB.ShopatuClientBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity implements View.OnClickListener {
    private String id;
    @BindView(R.id.imageDetail)
    ImageView imageDetail;
    @BindView(R.id.textNamaToko)
    TextView textNamaToko;
    @BindView(R.id.textNamaProduk)
    TextView textNamaProduk;
    @BindView(R.id.textKetProduk)
    TextView textKetProduk;
    @BindView(R.id.textHarga)
    TextView textHarga;
    @BindView(R.id.butTanyakan)
    Button butTanyakan;
    @BindView(R.id.butBeli)
    Button butBeli;
    Api mApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Sepatu");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            id = bundle.getString("idProduk");
        }
        butTanyakan.setOnClickListener(this);
        butBeli.setOnClickListener(this);
        mApi = ShopatuClientBuilder.apiService();
        getDetailProdukToko();
    }
    private void getDetailProdukToko(){
        mApi.getDetailProdukToko("db969f0c56297c6bd3585785f1772789","iqbalmf68@gmail.com", id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        showDetailProduk(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showDetailProduk(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject object = jsonObject.getJSONObject("result");
            String namaProduk = object.getString("nama_produk");
            int harga_produk = object.getInt("harga_produk");
            String keterangan_produk = object.getString("keterangan_produk");
            String gambar_produk = object.getString("gambar_produk");
            String nama_toko = object.getString("nama_toko");
            textNamaProduk.setText(namaProduk);
            textKetProduk.setText(keterangan_produk);
            textNamaToko.setText(nama_toko);
            Glide.with(DetailProductActivity.this).load(gambar_produk).into(imageDetail);
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatUang = NumberFormat.getCurrencyInstance(localeID);
            textHarga.setText(formatUang.format(harga_produk));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.butTanyakan:
                break;
            case R.id.butBeli:
                final ProgressDialog load = ProgressDialog.show(DetailProductActivity.this, null, "Mohon Tunggu...", true, false);
                HashMap<String, String> map = new HashMap<>();
                map.put("user_uid","db969f0c56297c6bd3585785f1772789");
                map.put("email_user","iqbalmf68@gmail.com");
                map.put("id_produk",id);
                map.put("jumlah","1");
                mApi.postTambahKeranjang(map).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        load.dismiss();
                        if (response.isSuccessful()){
                            try {
                                Toast.makeText(DetailProductActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        load.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
