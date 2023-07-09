package com.example.leaguesfootballv2.domain.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.domain.model.TeamEntity
import kotlinx.coroutines.flow.Flow

interface TeamsRepository {

    suspend fun fetchTeamsByLeague(league: String): Result<List<TeamEntity>>

    suspend fun fetchPersistedTeams(): Flow<List<TeamEntity>>
}