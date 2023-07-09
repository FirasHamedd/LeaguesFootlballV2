package com.example.leaguesfootballv2.data.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.datasource.LocalTeamDetailsDataSource
import com.example.leaguesfootballv2.data.transformer.SingleTeamToDomainTransformer
import com.example.leaguesfootballv2.domain.model.TeamEntity
import com.example.leaguesfootballv2.domain.repository.TeamDetailsRepository
import javax.inject.Inject

class TeamDetailsRepositoryImpl @Inject constructor(
    private val teamDetailsDataSource: LocalTeamDetailsDataSource,
    private val transformer: SingleTeamToDomainTransformer
) : TeamDetailsRepository {

    override suspend fun fetchPersistedSingleTeam(teamId: String): Result<TeamEntity> = try {
        teamDetailsDataSource.execute(param = teamId).let { jsonTeam ->
            Result.Success(transformer.singleTeamToDomain(jsonTeam = jsonTeam))
        }
    } catch (e: Exception) {
        Result.Failure(exception = e)
    }
}