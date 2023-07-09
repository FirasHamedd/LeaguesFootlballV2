package com.example.leaguesfootballv2.presentation.transformer

import com.example.leaguesfootballv2.domain.model.TeamEntity
import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDisplayModel
import javax.inject.Inject

class TeamsToDisplayTransformer @Inject constructor() {

    fun teamsToDisplayModel(teams: List<TeamEntity>) = teams.map {
        TeamDisplayModel(
            teamId = it.idTeam,
            teamName = it.strTeam,
            teamLogo = it.strTeamBadge
        )
    }
}