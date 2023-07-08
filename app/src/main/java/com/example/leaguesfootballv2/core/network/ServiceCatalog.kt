package com.example.leaguesfootballv2.core.network

object ServiceCatalog {
    const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/50130162/"
    const val GET_ALL_LEAGUES = "all_leagues.php"
    const val GET_TEAMS_BY_LEAGUE = "search_all_teams.php"
}
object QueryParams {
    const val GET_TEAMS_BY_LEAGUE_QUERY = "l"
}