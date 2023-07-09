package com.example.leaguesfootballv2.data.transformer

import com.example.leaguesfootballv2.data.model.JsonTeam
import com.example.leaguesfootballv2.domain.model.TeamEntity
import javax.inject.Inject

class TeamsToDomainTransformer @Inject constructor() {

    fun toDomain(jsonTeams: List<JsonTeam>) = jsonTeams.map { team ->
        with(team) {
            TeamEntity(
                idTeam = idTeam,
                strTeam = strTeam,
                strTeamShort = strTeamShort,
                intStadiumCapacity = intStadiumCapacity,
                intFormedYear = intFormedYear,
                strLeague = strLeague,
                strTeamBadge = strTeamBadge,
                strDescriptionFR = strDescriptionFR,
                strCountry = strCountry,
                strTeamBanner = strTeamBanner
            )
        }
    }
}