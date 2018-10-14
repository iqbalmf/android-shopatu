package net.iqbalfauzan.shopatu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.iqbalfauzan.shopatu.AccessDB.Api;
import net.iqbalfauzan.shopatu.AccessDB.ShopatuClientBuilder;
import net.iqbalfauzan.shopatu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Profil extends Fragment {
    public static Fragment_Profil newInstance() {
        return new Fragment_Profil();
    }
    @BindView(R.id.textNama)
    TextView textNama;
    @BindView(R.id.textEmail) TextView textEmail;
    @BindView(R.id.textAlamat) TextView textAlamat;
    @BindView(R.id.textNoTelUser) TextView textNoTelUser;
    @BindView(R.id.textNamaToko) TextView textNamaToko;
    @BindView(R.id.textAlamatToko) TextView textAlamatToko;
    @BindView(R.id.textNoTelToko) TextView textNoTelToko;
    @BindView(R.id.textNamaRawat) TextView textNamaRawat;
    @BindView(R.id.textAlamatRawat) TextView textAlamatRawat;
    @BindView(R.id.textNoTelRawat) TextView textNoTelRawat;
    Api mApi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        ButterKnife.bind(this, view);
        mApi = ShopatuClientBuilder.apiService();
        getDetailUser();
        return view;
    }
    private void getDetailUser(){
        mApi.getDetailUser("db969f0c56297c6bd3585785f1772789","iqbalmf68@gmail.com").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        showDetailUser(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showDetailUser(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
                JSONObject object = jsonObject.getJSONObject("result");
                String email_user = object.getString("email_user");
                String nama_user = object.getString("nama_user");
                String no_hp = object.getString("no_hp");
                String alamat = object.getString("alamat");
                textEmail.setText(email_user);
                textNama.setText(nama_user);
                textNoTelUser.setText(no_hp);
                textAlamat.setText(alamat);

                JSONObject object2 = jsonObject.getJSONObject("result2");
                String namaToko = object2.getString("nama_toko");
                String alamat_toko = object2.getString("alamat_toko");
                String no_tel = object2.getString("no_tel");
                textNamaToko.setText(namaToko);
                textAlamatToko.setText(alamat_toko);
                textNoTelToko.setText(no_tel);

                JSONObject object3 = jsonObject.getJSONObject("result3");
                String nama_shoescare = object3.getString("nama_shoescare");
                String alamatrawat = object3.getString("alamat");
                String no_telrawat = object3.getString("no_tel");
                textNamaRawat.setText(nama_shoescare);
                textAlamatRawat.setText(alamatrawat);
                textNoTelRawat.setText(no_telrawat);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
