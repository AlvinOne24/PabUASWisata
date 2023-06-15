package com.si61.wisataalvin.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si61.wisataalvin.API.APIRequestData;
import com.si61.wisataalvin.API.RetroServer;
import com.si61.wisataalvin.Activity.MainActivity;
import com.si61.wisataalvin.Activity.TambahActivity;
import com.si61.wisataalvin.Activity.UbahActivity;
import com.si61.wisataalvin.Model.ModelResponse;
import com.si61.wisataalvin.Model.ModelWisata;
import com.si61.wisataalvin.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Anda Memilih "+tvNama.getText().toString()+"Operasi apa yang akan dilakukan");

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent kirim = new Intent(ctx, UbahActivity.class);
                            kirim.putExtra("xId", tvId.getText().toString());
                            kirim.putExtra("xNama", tvNama.getText().toString());
                            kirim.putExtra("xLokasi", tvLokasi.getText().toString());
                            kirim.putExtra("xUrlmap", tvUrlMap.getText().toString());
                            ctx.startActivity(kirim);
                        }
                    });
                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            prosesHapus(tvId.getText().toString());

                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }
        void prosesHapus(String id){
            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = API.arddelete(id);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveWisata();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi Server: "+ t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
