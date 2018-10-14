package net.iqbalfauzan.shopatu.AccessDB;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @GET("/api/getListKategori/{user_uid}/{email_user}")
    Call<ResponseBody> getListKategori(@Path("user_uid") String user_uid,
                                     @Path("email_user") String email);

    @GET("/api/getSearchKategori/{user_uid}/{email_user}/{query}")
    Call<ResponseBody> getSearchKategori(@Path("user_uid") String user_uid,
                                       @Path("email_user") String email,
                                       @Path("query") String query);
    @GET("/api/getListProdukTitipJual/{user_uid}/{email_user}")
    Call<ResponseBody> getListProdukTitipJual(@Path("user_uid") String user_uid,
                                       @Path("email_user") String email);
    @GET("/api/getListProdukToko/{user_uid}/{email_user}")
    Call<ResponseBody> getListProdukToko(@Path("user_uid") String user_uid,
                                       @Path("email_user") String email);
    @GET("/api/getListShoesCare/{user_uid}/{email_user}/{id_user}")
    Call<ResponseBody> getListShoesCare(@Path("user_uid") String user_uid,
                                       @Path("email_user") String email,
                                        @Path("id_user") String id_user);

    @GET("/api/getDetailUser/{user_uid}/{email_user}")
    Call<ResponseBody> getDetailUser(@Path("user_uid") String user_uid,
                                       @Path("email_user") String email);
    @GET("/api/getDetailProdukToko/{user_uid}/{email_user}/{id_produk}")
    Call<ResponseBody> getDetailProdukToko(@Path("user_uid") String user_uid,
                                     @Path("email_user") String email,
                                           @Path("id_produk") String id_Produk);
    @GET("/api/getListJualBeli/{user_uid}/{email_user}/{idUser}")
    Call<ResponseBody> getListJualBeli(@Path("user_uid") String user_uid,
                                           @Path("email_user") String email,
                                           @Path("idUser") String idUser);
    @GET("/api/getListKeranjang/{user_uid}/{email_user}/{idUser}")
    Call<ResponseBody> getListKeranjang(@Path("user_uid") String user_uid,
                                           @Path("email_user") String email,
                                           @Path("idUser") String idUser);

    @FormUrlEncoded
    @POST("/api/postTambahKeranjang")
    Call<ResponseBody> postTambahKeranjang(@FieldMap HashMap<String, String> map); //user_uid, email_user, id_produk, jumlah

    @FormUrlEncoded
    @POST("/api/postBayarProduk")
    Call<ResponseBody> postbayarProduk(@FieldMap HashMap<String, String> map); //user_uid, email_user, id_keranjang, id_ekspedisi
}
