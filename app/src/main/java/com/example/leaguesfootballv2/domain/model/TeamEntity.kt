package com.example.leaguesfootballv2.domain.model

data class TeamEntity(
    val idTeam: String,
    val strTeam: String,
    val strTeamShort: String?,
    val intStadiumCapacity: Int,
    val intFormedYear: Int,
    val strLeague: String,
    val strTeamBadge: String,
    val strDescriptionFR: String?,
    val strCountry: String,
    val strTeamBanner: String?,
)