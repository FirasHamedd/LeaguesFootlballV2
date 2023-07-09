package com.example.leaguesfootballv2.data.datasource

import com.example.leaguesfootballv2.core.datasource.DataSource
import com.example.leaguesfootballv2.core.local.TeamDetailsDao
import com.example.leaguesfootballv2.data.model.JsonTeam
import javax.inject.Inject

class LocalTeamDetailsDataSource @Inject constructor(
    private val teamDetailsDao: TeamDetailsDao
) : DataSource<String, JsonTeam> {

    override suspend fun execute(param: String): JsonTeam {
        return teamDetailsDao.getSingleTeam(teamId = param)
    }
}