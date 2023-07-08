package com.example.leaguesfootballv2.data.datasource

import com.example.leaguesfootballv2.core.datasource.DataSource
import com.example.leaguesfootballv2.core.network.ApiService
import com.example.leaguesfootballv2.data.model.JsonTeams
import javax.inject.Inject

class TeamsDataSource @Inject constructor(
    private val apiService: ApiService
): DataSource<String, JsonTeams> {

    override suspend fun execute(param: String): JsonTeams {
        return apiService.fetchTeamsByLeague(league = param).body() ?: throw Exception()
    }
}