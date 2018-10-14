package net.iqbalfauzan.shopatu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.iqbalfauzan.shopatu.AccessDB.Api;
import net.iqbalfauzan.shopatu.AccessDB.ShopatuClientBuilder;
import net.iqbalfauzan.shopatu.Adapter.AdapterListTitipJualFemale;
import net.iqbalfauzan.shopatu.Adapter.AdapterListTitipJualMale;
import net.iqbalfauzan.shopatu.Adapter.AdapterListTitipJualTerbaru;
import net.iqbalfauzan.shopatu.Model.ModelTitipJual;
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

public class Fragment_JualBeli extends Fragment {
    public Fragment_JualBeli() {
    }
    Api mApi;
    @BindView(R.id.listTerbaru)
    RecyclerView listTerbaru;
    @BindView(R.id.listMale) RecyclerView listMale;
    @BindView(R.id.listFemale) RecyclerView listFemale;
    private List<ModelTitipJual> modelTitipJuals;
    private List<ModelTitipJual> modelTitipJuals1;
    private List<ModelTitipJual> modelTitipJuals2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jualbeli, container, false);
        ButterKnife.bind(this,view);
        listTerbaru.setHasFixedSize(true);
        listTerbaru.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        listMale.setHasFixedSize(true);
        listMale.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        listFemale.setHasFixedSize(true);
        listFemale.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mApi = ShopatuClientBuilder.apiService();
        getListProdukJualBeli();
        return view;
    }
    private void getListProdukJualBeli(){
        mApi.getListProdukToko("db969f0c56297c6bd3585785f1772789", "iqbalmf68@gmail.com").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        showListProdukJualBeli(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showListProdukJualBeli(String json){
        try {
            modelTitipJuals = new ArrayList<>();
            modelTitipJuals1 = new ArrayList<>();
            modelTitipJuals2 = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONArray result1 = jsonObject.getJSONArray("result1");
            JSONArray result2 = jsonObject.getJSONArray("result2");
            if (result.length() > 0){
                for (int i=0; i< result.length(); i++){
                    JSONObject object = result.getJSONObject(i);
                    ModelTitipJual value = new ModelTitipJual();
                    String id = object.getString("id");
                    String id_user = object.getString("id_toko");
                    String id_kategori = object.getString("id_kategori");
                    String id_gender = object.getString("id_gender");
                    String nama_produk =object.getString("nama_produk");
                    String gambar_produk =object.getString("gambar_produk");
                    int harga_produk =object.getInt("harga_produk");
                    String keterangan_produk =object.getString("keterangan_produk");
                    int stok =object.getInt("stok");
                    int status =object.getInt("status");
                    String nama_user =object.getString("nama_toko");
                    String nama_kategori =object.getString("nama_kategori");
                    String nama_gender =object.getString("nama_gender");
                    value.setId(id);
                    value.setGambar_produk(gambar_produk);
                    value.setNama_produk(nama_produk);
                    value.setHarga_produk(harga_produk);
                    value.setStok(stok);
                    value.setStatus(status);
                    value.setNama_user(nama_user);
                    modelTitipJuals.add(value);
                }
            }
            if (result1.length() > 0){
                for (int i=0; i< result1.length(); i++){
                    JSONObject object = result1.getJSONObject(i);
                    ModelTitipJual value = new ModelTitipJual();
                    String id = object.getString("id");
                    String id_user = object.getString("id_toko");
                    String id_kategori = object.getString("id_kategori");
                    String id_gender = object.getString("id_gender");
                    String nama_produk =object.getString("nama_produk");
                    String gambar_produk =object.getString("gambar_produk");
                    int harga_produk =object.getInt("harga_produk");
                    String keterangan_produk =object.getString("keterangan_produk");
                    int stok =object.getInt("stok");
                    int status =object.getInt("status");
                    String nama_user =object.getString("nama_toko");
                    String nama_kategori =object.getString("nama_kategori");
                    String nama_gender =object.getString("nama_gender");
                    value.setId(id);
                    value.setGambar_produk(gambar_produk);
                    value.setNama_produk(nama_produk);
                    value.setHarga_produk(harga_produk);
                    value.setStok(stok);
                    value.setStatus(status);
                    value.setNama_user(nama_user);
                    modelTitipJuals1.add(value);
                }
            }
            if (result2.length() > 0){
                for (int i=0; i< result2.length(); i++){
                    JSONObject object = result2.getJSONObject(i);
                    ModelTitipJual value = new ModelTitipJual();
                    String id = object.getString("id");
                    String id_user = object.getString("id_toko");
                    String id_kategori = object.getString("id_kategori");
                    String id_gender = object.getString("id_gender");
                    String nama_produk =object.getString("nama_produk");
                    String gambar_produk =object.getString("gambar_produk");
                    int harga_produk =object.getInt("harga_produk");
                    String keterangan_produk =object.getString("keterangan_produk");
                    int stok =object.getInt("stok");
                    int status =object.getInt("status");
                    String nama_user =object.getString("nama_toko");
                    String nama_kategori =object.getString("nama_kategori");
                    String nama_gender =object.getString("nama_gender");
                    value.setId(id);
                    value.setGambar_produk(gambar_produk);
                    value.setNama_produk(nama_produk);
                    value.setHarga_produk(harga_produk);
                    value.setStok(stok);
                    value.setStatus(status);
                    value.setNama_user(nama_user);
                    modelTitipJuals2.add(value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterListTitipJualTerbaru adapter = new AdapterListTitipJualTerbaru(getActivity(), modelTitipJuals);
        AdapterListTitipJualMale adapterMale = new AdapterListTitipJualMale(getActivity(), modelTitipJuals1);
        AdapterListTitipJualFemale adapterFemale = new AdapterListTitipJualFemale(getActivity(), modelTitipJuals2);
        listTerbaru.setAdapter(adapter);
        listMale.setAdapter(adapterMale);
        listFemale.setAdapter(adapterFemale);
        adapter.notifyDataSetChanged();
        adapterMale.notifyDataSetChanged();
        adapterFemale.notifyDataSetChanged();
    }
}
