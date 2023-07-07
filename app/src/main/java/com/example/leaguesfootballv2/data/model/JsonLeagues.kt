package com.example.leaguesfootballv2.data.model

import androidx.annotation.Keep
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonLeagues(
    @JsonProperty("leagues")
    val leagues: List<JsonLeague>
)

@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonLeague(
    @JsonProperty("idLeague")
    val idLeague: String,
    @JsonProperty("strLeague")
    val strLeague: String,
    @JsonProperty("strSport")
    val strSport: String,
    @JsonProperty("strLeagueAlternate")
    val strLeagueAlternate: String?
)