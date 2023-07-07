package com.example.leaguesfootballv2.data.transformer

import com.example.leaguesfootballv2.data.model.JsonLeagues
import com.example.leaguesfootballv2.domain.model.LeagueEntity
import javax.inject.Inject

class LeaguesToDomainTransformer @Inject constructor() {

    fun toDomain(jsonLeagues: JsonLeagues): List<LeagueEntity> = jsonLeagues.leagues.map { jsonLeague ->
        LeagueEntity(
            idLeague = jsonLeague.idLeague,
            strLeague = jsonLeague.strLeague,
            strSport = jsonLeague.strSport,
            strLeagueAlternate = jsonLeague.strLeagueAlternate
        )
    }
}