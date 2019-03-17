package br.com.raphaeloliveira.testebancopan.network

import br.com.raphaeloliveira.testebancopan.model.TopGames
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TwitchService{
    private val twitchApi: TwitchApi

    init {
        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.twitch.tv")
            .build()


        twitchApi = builder.create(TwitchApi::class.java)
    }

    fun getGames(limit: Int, offset: Int): Call<TopGames> {
        return twitchApi.getTopGames(limit, offset)
    }
}