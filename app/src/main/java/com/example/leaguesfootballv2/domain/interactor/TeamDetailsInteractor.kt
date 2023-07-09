package com.example.leaguesfootballv2.domain.interactor

import com.example.leaguesfootballv2.domain.repository.TeamDetailsRepository
import javax.inject.Inject

class TeamDetailsInteractor @Inject constructor(
    private val repository: TeamDetailsRepository
) {

    suspend fun getSingleTeam(teamId: String) = repository.fetchPersistedSingleTeam(teamId = teamId)
}