package br.com.raphaeloliveira.testebancopan.model

import com.google.gson.annotations.SerializedName

class TopGames (
    @SerializedName("_total")
    val total: Int,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("top")
    val gameItemList: List<GameItem>)