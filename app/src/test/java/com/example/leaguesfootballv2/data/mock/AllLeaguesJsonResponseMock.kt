package com.example.leaguesfootballv2.data.mock

import com.example.leaguesfootballv2.data.model.JsonLeague
import com.example.leaguesfootballv2.data.model.JsonLeagues

object AllLeaguesJsonResponseMock {
     val jsonAllLeagues = JsonLeagues(
        leagues = listOf(
            JsonLeague(
                idLeague = "4328",
                strLeague = "English Premier League",
                strSport = "Soccer",
                strLeagueAlternate = "Premier League, EPL"
            ),
            JsonLeague(
                idLeague = "4329",
                strLeague = "English League Championship",
                strSport = "Soccer",
                strLeagueAlternate = "Championship"
            ),
        )
    )
}