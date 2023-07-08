package com.example.leaguesfootballv2.data.datasource

import com.example.leaguesfootballv2.core.datasource.DataSource
import com.example.leaguesfootballv2.core.network.ApiService
import com.example.leaguesfootballv2.data.model.JsonLeagues
import javax.inject.Inject

class LeaguesDataSource @Inject constructor(
    private val apiService: ApiService
) : DataSource<Unit, JsonLeagues> {

    override suspend fun execute(param: Unit): JsonLeagues {
        return apiService.fetchAllLeagues().body() ?: throw Exception()
    }
}