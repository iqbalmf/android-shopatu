package net.iqbalfauzan.shopatu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.iqbalfauzan.shopatu.AccessDB.Api;
import net.iqbalfauzan.shopatu.AccessDB.ShopatuClientBuilder;
import net.iqbalfauzan.shopatu.Adapter.AdapterKeranjang;
import net.iqbalfauzan.shopatu.Adapter.AdapterTransaksi;
import net.iqbalfauzan.shopatu.Model.ModelListTransaksi;
import net.iqbalfauzan.shopatu.Model.ModelListTransaksi;
import net.iqbalfauzan.shopatu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Transaksi extends Fragment {
    public static Fragment_Transaksi newInstance(){
        return new Fragment_Transaksi();
    }
    @BindView(R.id.listTransaksi)
    RecyclerView listTransaksi;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefresh;
    private List<ModelListTransaksi> modelListTransaksis;
    Api mApi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaksi, container, false);
        ButterKnife.bind(this, view);
        mApi = ShopatuClientBuilder.apiService();
        listTransaksi.setHasFixedSize(true);
        listTransaksi.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListJualBeli();
            }
        });
        getListJualBeli();
        return view;
    }
    private void getListJualBeli(){
        mApi.getListJualBeli("db969f0c56297c6bd3585785f1772789","iqbalmf68@gmail.com","2").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    swipeRefresh.setRefreshing(false);
                    try {
                        showListKeranjang(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
            }
        });
    }
    private void showListKeranjang(String json){
        try {
            modelListTransaksis = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            if (result.length() > 0){
                for (int i=0;i<result.length();i++){
                    JSONObject object = result.getJSONObject(i);
                    ModelListTransaksi value = new ModelListTransaksi();
                    String id = object.getString("id");
                    String id_keranjang = object.getString("id_keranjang");
                    String nama_user = object.getString("nama_user");
                    String alamat_user = object.getString("alamat_user");
                    String id_ekspedisi = object.getString("id_ekspedisi");
                    int status = object.getInt("status");
                    String nama_produk = object.getString("nama_produk");
                    int hargaProduk = object.getInt("harga_produk");
                    value.setId(id);
                    value.setId(id);
                    value.setId_keranjang(id_keranjang);
                    value.setNama_user(nama_user);
                    value.setAlamat_user(alamat_user);
                    value.setStatus(status);
                    value.setId_ekspedisi(id_ekspedisi);
                    value.setNama_produk(nama_produk);
                    value.setHarga_produk(hargaProduk);
                    modelListTransaksis.add(value);


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterTransaksi adapter = new AdapterTransaksi(getActivity(), modelListTransaksis);
        listTransaksi.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
