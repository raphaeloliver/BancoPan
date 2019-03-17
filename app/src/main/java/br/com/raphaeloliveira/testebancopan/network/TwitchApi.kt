package br.com.raphaeloliveira.testebancopan.network

import br.com.raphaeloliveira.testebancopan.model.TopGames
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TwitchApi{

    @Headers("Client-ID: mun359un587x8vqqw57m7jcj2ovphz")
    @GET("/kraken/games/top")
    fun getTopGames(@Query("limit") limit: Int?, @Query("offset") offset: Int?): Call<TopGames>

}