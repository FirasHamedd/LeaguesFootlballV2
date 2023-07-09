package com.example.leaguesfootballv2.domain.interactor

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.domain.model.TeamEntity
import com.example.leaguesfootballv2.domain.repository.LeaguesRepository
import com.example.leaguesfootballv2.domain.repository.TeamsRepository
import javax.inject.Inject

class LeaguesInteractor @Inject constructor(
    private val leaguesRepository: LeaguesRepository,
    private val teamsRepository: TeamsRepository
) {
    suspend fun getAllLeagues() = leaguesRepository.fetchAllLeagues()

    suspend fun getTeamsByLeague(league: String) =
        teamsRepository.fetchTeamsByLeague(league = league).let { result ->
            if (result is Result.Success)
                Result.Success(result.data.sortDescendingWithHalfTeams())
            else
                result
        }

    suspend fun getPersistedTeams() = teamsRepository.fetchPersistedTeams().let { result ->
        if (result is Result.Success)
            Result.Success(result.data.sortDescendingWithHalfTeams())
        else
            result
    }

    private fun List<TeamEntity>.sortDescendingWithHalfTeams() =
        sortedByDescending { it.strTeam }.subList(0, (size + if (size % 2 == 0) 0 else 1) / 2)

}