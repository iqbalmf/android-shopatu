package net.iqbalfauzan.shopatu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.iqbalfauzan.shopatu.Model.ModelListTransaksi;
import net.iqbalfauzan.shopatu.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterTransaksi extends RecyclerView.Adapter<AdapterTransaksi.Holder> {
    private Context context;
    private List<ModelListTransaksi> modelListTransaksis;

    public AdapterTransaksi(Context context, List<ModelListTransaksi> modelListTransaksis) {
        this.context = context;
        this.modelListTransaksis = modelListTransaksis;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_layout_transaksi, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        ModelListTransaksi modelListTransaksi = modelListTransaksis.get(i);
        holder.textNamaUser.setText(modelListTransaksi.getNama_user());
        holder.textalamat_user.setText(modelListTransaksi.getAlamat_user());
        holder.textnama_produk.setText(modelListTransaksi.getNama_produk());
        int stat = modelListTransaksi.getStatus();
        if (stat == 0){
            holder.textStatus.setText("Belum Lunas");
            holder.textStatus.setBackgroundColor(context.getResources().getColor(R.color.hijau));
        }else {
            holder.textStatus.setText("Lunas");
        }
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatUang = NumberFormat.getCurrencyInstance(localeID);
        holder.textHarga.setText(formatUang.format(modelListTransaksi.getHarga_produk()));
    }

    @Override
    public int getItemCount() {
        return modelListTransaksis.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textNamaUser,textalamat_user,textnama_produk,textHarga,textStatus;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textNamaUser = itemView.findViewById(R.id.textNamaUser);
            textalamat_user = itemView.findViewById(R.id.textalamat_user);
            textnama_produk = itemView.findViewById(R.id.textnama_produk);
            textHarga = itemView.findViewById(R.id.textHarga);
            textStatus = itemView.findViewById(R.id.textStatus);
        }
    }
}
