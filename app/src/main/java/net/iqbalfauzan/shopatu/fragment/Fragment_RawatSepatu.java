package net.iqbalfauzan.shopatu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.iqbalfauzan.shopatu.AccessDB.Api;
import net.iqbalfauzan.shopatu.AccessDB.ShopatuClientBuilder;
import net.iqbalfauzan.shopatu.Adapter.AdapterRawatSepatu;
import net.iqbalfauzan.shopatu.Model.ModelRawatSepatu;
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

public class Fragment_RawatSepatu extends Fragment {
    public Fragment_RawatSepatu() {
    }
    @BindView(R.id.listRawatSepatu)
    RecyclerView listRawatSepatu;
    private List<ModelRawatSepatu> modelRawatSepatus;
    Api mApi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rawatsepatu, container, false);
        ButterKnife.bind(this, view);
        listRawatSepatu.setHasFixedSize(true);
        listRawatSepatu.setLayoutManager(new LinearLayoutManager(getActivity()));
        mApi = ShopatuClientBuilder.apiService();
        getListShoesCare();
        return view;
    }
    private void getListShoesCare(){
        mApi.getListShoesCare("db969f0c56297c6bd3585785f1772789","iqbalmf68@gmail.com","1").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        showListShoesCare(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showListShoesCare(String json){
        try {
            modelRawatSepatus = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            if (result.length() > 0){
                for (int i=0; i< result.length(); i++){
                    JSONObject object = result.getJSONObject(i);
                    ModelRawatSepatu value = new ModelRawatSepatu();
                    String nama_shoescare = object.getString("nama_shoescare");
                    String alamat = object.getString("alamat");
                    String no_tel = object.getString("no_tel");
                    String jarak = object.getString("jarak");
                    value.setNama_shoescare(nama_shoescare);
                    value.setAlamat(alamat);
                    value.setNo_tel(no_tel);
                    value.setJarak(jarak);
                    modelRawatSepatus.add(value);
                    Log.i("TAG", "showListShoesCare: "+value.getNama_shoescare());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterRawatSepatu adapter = new AdapterRawatSepatu(getActivity(), modelRawatSepatus);
        listRawatSepatu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
