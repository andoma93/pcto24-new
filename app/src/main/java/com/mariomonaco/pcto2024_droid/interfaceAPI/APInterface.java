package com.mariomonaco.pcto2024_droid.interfaceAPI;

import com.mariomonaco.pcto2024_droid.models.Prodotto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


interface APIInterface {

    @GET("/api/v1/prodotti")
    Call<List<Prodotto>> doGetListProdotti();
}
