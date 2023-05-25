package com.si61.wisataalvin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.si61.wisataalvin.API.APIRequestData;
import com.si61.wisataalvin.API.RetroServer;
import com.si61.wisataalvin.Adapter.AdapterWisata;
import com.si61.wisataalvin.Model.ModelResponse;
import com.si61.wisataalvin.Model.ModelWisata;
import com.si61.wisataalvin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvWisata;
    private ProgressBar pbWisata;
    private FloatingActionButton fabTambah;
    private RecyclerView.Adapter adWisata;
    private RecyclerView.LayoutManager lmWisata;
    private List<ModelWisata>listWisata = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvWisata = findViewById(R.id.rv_wisata);
        pbWisata = findViewById(R.id.pb_wisata);
        fabTambah = findViewById(R.id.fab_tambah);

        lmWisata = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvWisata.setLayoutManager(lmWisata);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    public  void retrieveWisata(){
        pbWisata.setVisibility(View.VISIBLE);
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listWisata = response.body().getData();

                adWisata = new AdapterWisata(MainActivity.this, listWisata);
                rvWisata.setAdapter(adWisata);
                adWisata.notifyDataSetChanged();
                pbWisata.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error : Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbWisata.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveWisata();
    }
}