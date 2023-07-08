package com.example.leaguesfootballv2.domain.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.domain.model.TeamEntity

interface TeamsRepository {

    suspend fun fetchTeamsByLeague(league: String): Result<List<TeamEntity>>
}