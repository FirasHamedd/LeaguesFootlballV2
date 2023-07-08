package com.example.leaguesfootballv2.data.mock

import com.example.leaguesfootballv2.data.model.JsonTeam
import com.example.leaguesfootballv2.data.model.JsonTeams

object TeamsJsonResponseMock {

    val jsonTeams = JsonTeams(
        teams = listOf(
            JsonTeam(
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
            JsonTeam(
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
    )
}
