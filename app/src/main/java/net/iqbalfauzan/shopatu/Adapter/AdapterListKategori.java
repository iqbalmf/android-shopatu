package net.iqbalfauzan.shopatu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.iqbalfauzan.shopatu.KategoriDetailActivity;
import net.iqbalfauzan.shopatu.Model.ModelListKategori;
import net.iqbalfauzan.shopatu.R;

import java.util.List;

public class AdapterListKategori extends RecyclerView.Adapter<AdapterListKategori.Holder> {
    private Context context;
    private List<ModelListKategori> modelListKategoris;

    public AdapterListKategori(Context context, List<ModelListKategori> modelListKategoris) {
        this.context = context;
        this.modelListKategoris = modelListKategoris;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_kategori, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final ModelListKategori modelListKategori = modelListKategoris.get(i);
        if (modelListKategori.getNama_gender() == null){
            holder.textGender.setVisibility(View.GONE);
            holder.textKategori.setText(modelListKategori.getNama_kategori());
            holder.textKategori.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, KategoriDetailActivity.class).putExtra("kategori", modelListKategori.getNama_kategori()));
                }
            });
        }else if (modelListKategori.getNama_kategori() == null){
            holder.textKategori.setVisibility(View.GONE);
            holder.textGender.setText(modelListKategori.getNama_gender());
            holder.textGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, KategoriDetailActivity.class).putExtra("kategori", modelListKategori.getNama_gender()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return modelListKategoris.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textKategori, textGender;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textKategori = itemView.findViewById(R.id.textKategori);
            textGender = itemView.findViewById(R.id.textGender);
        }
    }
}
