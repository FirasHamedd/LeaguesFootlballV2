package com.example.leaguesfootballv2.data.mock

import com.example.leaguesfootballv2.domain.model.LeagueEntity

object AllLeaguesMock {

    val leagues = listOf(
        LeagueEntity(
            idLeague = "4328",
            strLeague = "English Premier League",
            strSport = "Soccer",
            strLeagueAlternate = "Premier League, EPL"
        ),
        LeagueEntity(
            idLeague = "4329",
            strLeague = "English League Championship",
            strSport = "Soccer",
            strLeagueAlternate = "Championship"
        ),
    )
}