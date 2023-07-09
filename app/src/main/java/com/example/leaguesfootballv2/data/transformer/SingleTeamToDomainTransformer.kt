package com.example.leaguesfootballv2.data.transformer

import com.example.leaguesfootballv2.data.model.JsonTeam
import com.example.leaguesfootballv2.domain.model.TeamEntity
import javax.inject.Inject

class SingleTeamToDomainTransformer @Inject constructor() {

    fun singleTeamToDomain(jsonTeam: JsonTeam) = with(jsonTeam) {
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