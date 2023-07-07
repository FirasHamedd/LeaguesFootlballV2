package com.example.leaguesfootballv2.core.network

import com.example.leaguesfootballv2.data.model.JsonLeagues
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(ServiceCatalog.GET_ALL_LEAGUES)
    suspend fun getAllLeagues(): Response<JsonLeagues>
}