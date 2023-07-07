package com.example.leaguesfootballv2.data.repository

import com.example.leaguesfootballv2.data.datasource.LeaguesDataSource
import com.example.leaguesfootballv2.domain.repository.LeaguesRepository
import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.transformer.LeaguesToDomainTransformer
import javax.inject.Inject

class LeaguesRepositoryImpl @Inject constructor(
    private val dataSource: LeaguesDataSource,
    private val transformer: LeaguesToDomainTransformer
) : LeaguesRepository {

    override suspend fun fetchAllLeagues() = try {
        transformer.toDomain(jsonLeagues = dataSource.execute(param = Unit)).let { leagues ->
            Result.Success(data = leagues)
        }
    } catch (e: Exception) {
        Result.Failure(exception = e)
    }
}