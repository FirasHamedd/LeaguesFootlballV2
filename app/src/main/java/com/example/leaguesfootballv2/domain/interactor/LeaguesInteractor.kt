package com.example.leaguesfootballv2.domain.interactor

import com.example.leaguesfootballv2.domain.repository.LeaguesRepository
import com.example.leaguesfootballv2.domain.repository.TeamsRepository
import javax.inject.Inject

class LeaguesInteractor @Inject constructor(
    private val leaguesRepository: LeaguesRepository,
    private val teamsRepository: TeamsRepository
) {
    suspend fun getAllLeagues() = leaguesRepository.fetchAllLeagues()

    suspend fun getTeamsByLeague(league: String) =
        teamsRepository.fetchTeamsByLeague(league = league)
}