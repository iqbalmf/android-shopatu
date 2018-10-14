package net.iqbalfauzan.shopatu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.iqbalfauzan.shopatu.DetailProductActivity;
import net.iqbalfauzan.shopatu.Model.ModelKategoriToko;
import net.iqbalfauzan.shopatu.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterListKategoriJualBeli extends RecyclerView.Adapter<AdapterListKategoriJualBeli.Holder> {
    private Context context;
    private List<ModelKategoriToko> modelKategoriTokos;

    public AdapterListKategoriJualBeli(Context context, List<ModelKategoriToko> modelKategoriTokos) {
        this.context = context;
        this.modelKategoriTokos = modelKategoriTokos;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_kategori_jualbeli, viewGroup, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final ModelKategoriToko modelKategoriToko = modelKategoriTokos.get(i);
        holder.judul.setText(modelKategoriToko.getNama_toko());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatUang = NumberFormat.getCurrencyInstance(localeID);
        holder.harga.setText(formatUang.format(modelKategoriToko.getHarga_produk()));
        Glide.with(context).load(modelKategoriToko.getGambar_produk()).into(holder.thumbnail);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DetailProductActivity.class).putExtra("idProduk", modelKategoriToko.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelKategoriTokos.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView judul,harga;
        ImageView thumbnail;
        RelativeLayout layout;
        public Holder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            harga = itemView.findViewById(R.id.harga);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
