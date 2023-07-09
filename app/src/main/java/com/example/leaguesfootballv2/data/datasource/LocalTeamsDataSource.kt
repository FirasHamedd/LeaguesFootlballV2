package com.example.leaguesfootballv2.data.datasource

import com.example.leaguesfootballv2.core.datasource.DataSource
import com.example.leaguesfootballv2.core.local.TeamsByLeagueDao
import com.example.leaguesfootballv2.data.model.JsonTeam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalTeamsDataSource @Inject constructor(
    private val teamsByLeagueDao: TeamsByLeagueDao
) : DataSource<Unit, Flow<List<JsonTeam>>> {

    override suspend fun execute(param: Unit): Flow<List<JsonTeam>> {
        return teamsByLeagueDao.getTeams()
    }

    suspend fun save(teams: List<JsonTeam>) {
        teamsByLeagueDao.deleteAllTeams()
        teamsByLeagueDao.insertAll(teams = teams)
    }
}