package br.com.raphaeloliveira.testebancopan.model

import com.google.gson.annotations.SerializedName

data class Game(
        val name: String? = null,
        val box: ImagesUrls? = null,
        val logo: ImagesUrls? = null,
        @SerializedName("_id")
        val id: String? = null,
        @SerializedName("giantbomb_id")
        val giantBombId: String? = null)