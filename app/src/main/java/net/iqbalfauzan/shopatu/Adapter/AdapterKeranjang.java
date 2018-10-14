package net.iqbalfauzan.shopatu.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.iqbalfauzan.shopatu.AccessDB.Api;
import net.iqbalfauzan.shopatu.AccessDB.ShopatuClientBuilder;
import net.iqbalfauzan.shopatu.Model.ModelListKeranjang;
import net.iqbalfauzan.shopatu.R;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterKeranjang extends RecyclerView.Adapter<AdapterKeranjang.Holder> {
    private Context context;
    private List<ModelListKeranjang> modelListKeranjangs;
    Api mApi;

    public AdapterKeranjang(Context context, List<ModelListKeranjang> modelListKeranjangs) {
        this.context = context;
        this.modelListKeranjangs = modelListKeranjangs;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_layout_keranjang, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final ModelListKeranjang modelListKeranjang = modelListKeranjangs.get(i);
        holder.textNama.setText("Nama Produk : "+modelListKeranjang.getNama_produk());
        holder.textJumlah.setText("Jumlah : "+modelListKeranjang.getJumlah());
        holder.textCart.setText(modelListKeranjang.getTgl_cart());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatUang = NumberFormat.getCurrencyInstance(localeID);
        holder.textharga.setText("Biaya Total "+formatUang.format(modelListKeranjang.getHarga_produk()));
        holder.butbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApi = ShopatuClientBuilder.apiService();
                HashMap<String, String> map = new HashMap<>();
                map.put("user_uid","db969f0c56297c6bd3585785f1772789");
                map.put("email_user","iqbalmf68@gmail.com");
                map.put("id_keranjang",modelListKeranjang.getId());
                map.put("id_ekspedisi","1");
                Log.i("TAG", "onClick: "+modelListKeranjang.getId());
                final ProgressDialog load = ProgressDialog.show(context, null, "Mohon Tunggu...", true, false);
                mApi.postbayarProduk(map).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        load.dismiss();
                        if (response.isSuccessful()){
                            try {
                                Toast.makeText(context, response.body().string(), Toast.LENGTH_SHORT).show();
                                Log.i("TAG", "onResponse: "+response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        load.dismiss();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelListKeranjangs.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textCart, textNama, textharga, textJumlah;
        Button butbayar;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textCart = itemView.findViewById(R.id.textTglCart);
            textNama = itemView.findViewById(R.id.textNamaProduk);
            textharga = itemView.findViewById(R.id.textHarga);
            textJumlah = itemView.findViewById(R.id.textJumlah);
            butbayar = itemView.findViewById(R.id.butBayar);
        }
    }
}
