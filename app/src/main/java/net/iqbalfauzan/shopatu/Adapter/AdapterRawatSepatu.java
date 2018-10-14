package net.iqbalfauzan.shopatu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.iqbalfauzan.shopatu.Model.ModelRawatSepatu;
import net.iqbalfauzan.shopatu.R;

import java.util.List;

public class AdapterRawatSepatu extends RecyclerView.Adapter<AdapterRawatSepatu.Holder> {
    private Context context;
    private List<ModelRawatSepatu> modelRawatSepatus;

    public AdapterRawatSepatu(Context context, List<ModelRawatSepatu> modelRawatSepatus) {
        this.context = context;
        this.modelRawatSepatus = modelRawatSepatus;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_rawat, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        ModelRawatSepatu modelRawatSepatu = modelRawatSepatus.get(i);
        holder.textNama.setText("NAMA TOKO : "+modelRawatSepatu.getNama_shoescare());
        holder.textAlamat.setText("ALAMAT : "+modelRawatSepatu.getAlamat());
        holder.textNoTel.setText("NO TELEFON : "+modelRawatSepatu.getNo_tel());
        holder.textJarak.setText("JARAK LOKASI : "+modelRawatSepatu.getJarak());
    }

    @Override
    public int getItemCount() {
        return modelRawatSepatus.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textNama, textAlamat, textNoTel, textJarak;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.textNama);
            textAlamat = itemView.findViewById(R.id.textAlamat);
            textNoTel = itemView.findViewById(R.id.textNoTel);
            textJarak = itemView.findViewById(R.id.textJarak);
        }
    }
}
