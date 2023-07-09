package com.example.leaguesfootballv2.presentation.dispalymodel

data class TeamDetailsDisplayModel(
    val teamName: String,
    val teamBadge: String,
    val teamShortName: String?,
    val stadiumCapacity: Int,
    val formedYear: Int,
    val league: String,
    val description: String?,
)
