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

import net.iqbalfauzan.shopatu.AccessDB.Api;
import net.iqbalfauzan.shopatu.AccessDB.ShopatuClientBuilder;
import net.iqbalfauzan.shopatu.Adapter.AdapterListKategori;
import net.iqbalfauzan.shopatu.Model.ModelListKategori;
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

public class Fragment_Kategori extends Fragment {
    public static Fragment_Kategori newInstance() {

        return new Fragment_Kategori();
    }
    @BindView(R.id.listKategoriAtas)
    RecyclerView listKategoriAtas;
    Api mApi;
    private List<ModelListKategori> modelListKategoris;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_kategori, container, false);
        ButterKnife.bind(this, view);
        listKategoriAtas.setHasFixedSize(true);
        listKategoriAtas.setLayoutManager(new LinearLayoutManager(getActivity()));
        mApi = ShopatuClientBuilder.apiService();
        getListKategori();
        return view;
    }
    private void getListKategori(){
        mApi.getListKategori("db969f0c56297c6bd3585785f1772789", "iqbalmf68@gmail.com").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        showListKategori(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    private void showListKategori(String json){
        try {
            modelListKategoris = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONArray result1 = jsonObject.getJSONArray("result2");
            if (result.length() >0){
                for (int i=0; i < result.length(); i++){
                    JSONObject object = result.getJSONObject(i);
                    ModelListKategori value = new ModelListKategori();
                    String namaGender = object.getString("nama_gender");
                    value.setNama_gender(namaGender);
                    modelListKategoris.add(value);
                }
            }
            if (result1.length() > 0){
                for (int i =0; i <result1.length(); i++){
                    JSONObject object = result1.getJSONObject(i);
                    ModelListKategori value = new ModelListKategori();
                    String namaKategori = object.getString("nama_kategori");
                    value.setNama_kategori(namaKategori);
                    modelListKategoris.add(value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterListKategori adapter = new AdapterListKategori(getActivity(), modelListKategoris);
        listKategoriAtas.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
