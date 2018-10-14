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
import net.iqbalfauzan.shopatu.Model.ModelListKeranjang;
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

public class Fragment_Keranjang extends Fragment {
    public static Fragment_Keranjang newInstance() {
        return new Fragment_Keranjang();
    }
    @BindView(R.id.listKeranjang)
    RecyclerView listKeranjang;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefresh;
    Api mApi;
    private List<ModelListKeranjang> modelListKeranjangs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_keranjang, container, false);
        ButterKnife.bind(this, view);
        listKeranjang.setHasFixedSize(true);
        listKeranjang.setLayoutManager(new LinearLayoutManager(getActivity()));
        mApi = ShopatuClientBuilder.apiService();
        getListKeranjang();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListKeranjang();
            }
        });
        return view;
    }
    private void getListKeranjang(){
        mApi.getListKeranjang("db969f0c56297c6bd3585785f1772789","iqbalmf68@gmail.com","2").enqueue(new Callback<ResponseBody>() {
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
            modelListKeranjangs = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            if (result.length() > 0){
                for (int i=0;i<result.length();i++){
                    JSONObject object = result.getJSONObject(i);
                    ModelListKeranjang value = new ModelListKeranjang();
                    String id = object.getString("id");
                    String id_user = object.getString("id_user");
                    String id_produk = object.getString("id_produk");
                    String tgl_cart = object.getString("tgl_cart");
                    String jumlah = object.getString("jumlah");
                    String status = object.getString("status");
                    String nama_produk = object.getString("nama_produk");
                    int hargaProduk = object.getInt("harga_produk");
                    value.setId(id);
                    value.setId_user(id_user);
                    value.setId_produk(id_produk);
                    value.setTgl_cart(tgl_cart);
                    value.setJumlah(jumlah);
                    value.setStatus(status);
                    value.setNama_produk(nama_produk);
                    value.setHarga_produk(hargaProduk);
                    modelListKeranjangs.add(value);


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterKeranjang adapter = new AdapterKeranjang(getActivity(), modelListKeranjangs);
        listKeranjang.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
