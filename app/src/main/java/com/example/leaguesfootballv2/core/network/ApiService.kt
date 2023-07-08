package com.example.leaguesfootballv2.core.network

import com.example.leaguesfootballv2.data.model.JsonLeagues
import com.example.leaguesfootballv2.data.model.JsonTeams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ServiceCatalog.GET_ALL_LEAGUES)
    suspend fun fetchAllLeagues(): Response<JsonLeagues>

    @GET(ServiceCatalog.GET_TEAMS_BY_LEAGUE)
    suspend fun fetchTeamsByLeague(@Query(QueryParams.GET_TEAMS_BY_LEAGUE_QUERY) league: String): Response<JsonTeams>
}