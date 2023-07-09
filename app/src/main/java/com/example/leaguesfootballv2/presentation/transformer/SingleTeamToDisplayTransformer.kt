package com.example.leaguesfootballv2.presentation.transformer

import com.example.leaguesfootballv2.domain.model.TeamEntity
import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDetailsDisplayModel
import javax.inject.Inject

class SingleTeamToDisplayTransformer @Inject constructor() {

    fun singleTeamToDisplay(team: TeamEntity) = with(team) {
        TeamDetailsDisplayModel(
            teamName = strTeam,
            teamBadge = strTeamBadge,
            teamShortName = strTeamShort,
            stadiumCapacity = intStadiumCapacity,
            formedYear = intFormedYear,
            league = strLeague,
            description = strDescriptionFR
        )
    }
}