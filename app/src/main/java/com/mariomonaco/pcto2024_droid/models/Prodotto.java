package com.mariomonaco.pcto2024_droid.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Prodotto {


        @SerializedName("marca")
        public Integer marca;
        @SerializedName("nome")
        public String nome;
        @SerializedName("prezzo")
        public double prezzo;
        @SerializedName("descrizione")
        public String descrizione;

        @SerializedName("id")
        public Integer id;

}
