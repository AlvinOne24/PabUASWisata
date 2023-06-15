package com.si61.wisataalvin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si61.wisataalvin.Model.ModelWisata;
import com.si61.wisataalvin.R;

import java.util.List;

public class AdapterWisata extends RecyclerView.Adapter<AdapterWisata.VHWisata> {
    private Context ctx;
    private List<ModelWisata>listWisata;

    public AdapterWisata(Context ctx, List<ModelWisata> listWisata) {
        this.ctx = ctx;
        this.listWisata = listWisata;
    }

    @NonNull
    @Override
    public VHWisata onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_wisata,parent, false);
        return new VHWisata(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHWisata holder, int position) {
        ModelWisata MW = listWisata.get(position);
        holder.tvId.setText(MW.getId());
        holder.tvNama.setText(MW.getNama());
        holder.tvLokasi.setText(MW.getLokasi());
        holder.tvUrlMap.setText(MW.getUrlmap());
    }

    @Override
    public int getItemCount() {
        return listWisata.size();
    }

    public class VHWisata extends RecyclerView.ViewHolder{

        TextView tvId, tvNama, tvLokasi, tvUrlMap;


        public VHWisata(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvLokasi = itemView.findViewById(R.id.tv_lokasi);
            tvUrlMap = itemView.findViewById(R.id.tv_urlmap);
        }
    }
}
