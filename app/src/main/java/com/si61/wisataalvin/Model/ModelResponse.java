package com.si61.wisataalvin.Model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List<ModelWisata> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelWisata> getData() {
        return data;
    }
}
