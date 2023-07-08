package com.example.leaguesfootballv2.data.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.datasource.TeamsDataSource
import com.example.leaguesfootballv2.data.transformer.TeamsToDomainTransformer
import com.example.leaguesfootballv2.domain.model.TeamEntity
import com.example.leaguesfootballv2.domain.repository.TeamsRepository
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val teamsDataSource: TeamsDataSource,
    private val transformer: TeamsToDomainTransformer
) : TeamsRepository {

    override suspend fun fetchTeamsByLeague(league: String): Result<List<TeamEntity>> = try {
        transformer.toDomain(jsonTeams = teamsDataSource.execute(param = league))?.let { teams ->
            Result.Success(data = teams)
        } ?: Result.Failure("No Teams for your search")
    } catch (e: Exception) {
        Result.Failure(exception = e)
    }
}