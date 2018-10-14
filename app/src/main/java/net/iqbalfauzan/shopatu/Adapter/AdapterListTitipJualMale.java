package net.iqbalfauzan.shopatu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.iqbalfauzan.shopatu.Model.ModelTitipJual;
import net.iqbalfauzan.shopatu.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterListTitipJualMale extends RecyclerView.Adapter<AdapterListTitipJualMale.Holder> {
    private Context context;
    private List<ModelTitipJual> modelTitipJuals;

    public AdapterListTitipJualMale(Context context, List<ModelTitipJual> modelTitipJuals) {
        this.context = context;
        this.modelTitipJuals = modelTitipJuals;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_terbaru, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        ModelTitipJual modelTitipJual = modelTitipJuals.get(i);
        holder.tvTitle.setText(modelTitipJual.getNama_produk());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatUang = NumberFormat.getCurrencyInstance(localeID);
        holder.textHarga.setText(formatUang.format(modelTitipJual.getHarga_produk()));
        Glide.with(context).load(modelTitipJual.getGambar_produk()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return modelTitipJuals.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvTitle,textHarga;
        ImageView itemImage;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            textHarga = itemView.findViewById(R.id.textHarga);
            itemImage = itemView.findViewById(R.id.itemImage);
        }
    }
}
