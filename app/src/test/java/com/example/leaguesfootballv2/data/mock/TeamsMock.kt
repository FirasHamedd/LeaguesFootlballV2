package com.example.leaguesfootballv2.data.mock

import com.example.leaguesfootballv2.domain.model.TeamEntity

object TeamsMock {

    val teams = listOf(
        TeamEntity(
            idTeam = "133604",
            strTeam = "Arsenal",
            strTeamShort = "ARS",
            intStadiumCapacity = 60338,
            intFormedYear = 1892,
            strLeague = "English Premier League",
            strTeamBadge = "arsenalBageUrl",
            strDescriptionFR = "Description FR",
            strCountry = "Country",
            strTeamBanner = "teamBanner"
        ),
        TeamEntity(
            idTeam = "14444",
            strTeam = "Manchester United",
            strTeamShort = "MUC",
            intStadiumCapacity = 50000,
            intFormedYear = 1900,
            strLeague = "English Premier League",
            strTeamBadge = "manchesterBageUrl",
            strDescriptionFR = "Description FR",
            strCountry = "Country",
            strTeamBanner = "teamBanner"
        ),
    )
}