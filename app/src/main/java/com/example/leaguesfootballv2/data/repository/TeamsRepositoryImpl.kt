package com.example.leaguesfootballv2.data.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.datasource.LocalTeamsDataSource
import com.example.leaguesfootballv2.data.datasource.TeamsDataSource
import com.example.leaguesfootballv2.data.transformer.TeamsToDomainTransformer
import com.example.leaguesfootballv2.domain.model.TeamEntity
import com.example.leaguesfootballv2.domain.repository.TeamsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val teamsDataSource: TeamsDataSource,
    private val transformer: TeamsToDomainTransformer,
    private val localTeamsDataSource: LocalTeamsDataSource
) : TeamsRepository {

    override suspend fun fetchTeamsByLeague(league: String): Result<List<TeamEntity>> = try {
        teamsDataSource.execute(param = league).teams?.let { jsonTeams ->
            localTeamsDataSource.save(teams = jsonTeams)
            Result.Success(data = transformer.toDomain(jsonTeams = jsonTeams))
        } ?: Result.Failure("No Teams for your search")
    } catch (e: Exception) {
        Result.Failure(exception = e)
    }

    override suspend fun fetchPersistedTeams(): Flow<List<TeamEntity>> =
        localTeamsDataSource.execute(param = Unit).map { persistedTeams ->
            transformer.toDomain(jsonTeams = persistedTeams)
        }
}