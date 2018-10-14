package net.iqbalfauzan.shopatu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.iqbalfauzan.shopatu.AccessDB.Api;
import net.iqbalfauzan.shopatu.AccessDB.ShopatuClientBuilder;
import net.iqbalfauzan.shopatu.Adapter.AdapterListKategoriJualBeli;
import net.iqbalfauzan.shopatu.Model.ModelKategoriToko;
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

public class Fragment_JualBeli_Kategori extends Fragment {
    public Fragment_JualBeli_Kategori() {
    }
    private String kategori;
    @BindView(R.id.listKategori)
    RecyclerView listKategori;
    Api mApi;
    private List<ModelKategoriToko> modelKategoriTokos;
    GridLayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jualbeli_kategori, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            kategori = bundle.getString("kategori");
            Log.i("TAG", "onCreateView: "+kategori);
        }
        listKategori.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        listKategori.setLayoutManager(layoutManager);
        mApi = ShopatuClientBuilder.apiService();
        getSearchKategori();
        return view;
    }

    private void getSearchKategori(){
        mApi.getSearchKategori("db969f0c56297c6bd3585785f1772789", "iqbalmf68@gmail.com", kategori).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        showSearchKategori(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    private void showSearchKategori(String json){
        try {
            modelKategoriTokos = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            if (result.length() > 0){
                for (int i = 0; i < result.length(); i++) {
                    JSONObject object = result.getJSONObject(i);
                    ModelKategoriToko value = new ModelKategoriToko();
                    String id = object.getString("id");
                    String id_toko = object.getString("id_toko");
                    String id_kategori = object.getString("id_kategori");
                    String id_gender = object.getString("id_gender");
                    String nama_produk = object.getString("nama_produk");
                    String gambar_produk = object.getString("gambar_produk");
                    int harga_produk = object.getInt("harga_produk");
                    String keterangan_produk = object.getString("keterangan_produk");
                    int stok = object.getInt("stok");
                    String nama_toko = object.getString("nama_toko");
                    String nama_kategori = object.getString("nama_kategori");
                    String nama_gender = object.getString("nama_gender");
                    value.setId(id);
                    value.setGambar_produk(gambar_produk);
                    value.setNama_produk(nama_produk);
                    value.setHarga_produk(harga_produk);
                    value.setKeterangan_produk(keterangan_produk);
                    value.setStok(stok);
                    value.setNama_toko(nama_toko);
                    value.setNama_kategori(nama_kategori);
                    value.setNama_gender(nama_gender);
                    modelKategoriTokos.add(value);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterListKategoriJualBeli adapter = new AdapterListKategoriJualBeli(getActivity(), modelKategoriTokos);
        listKategori.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
