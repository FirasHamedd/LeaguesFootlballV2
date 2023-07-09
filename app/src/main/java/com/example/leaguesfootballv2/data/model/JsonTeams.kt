package com.example.leaguesfootballv2.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonTeams(
    @JsonProperty("teams")
    val teams: List<JsonTeam>?
)

@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(tableName = "teams")
data class JsonTeam(
    @JsonProperty("idTeam")
    @PrimaryKey
    val idTeam: String,
    @JsonProperty("strTeam")
    val strTeam: String,
    @JsonProperty("strTeamShort")
    val strTeamShort: String?,
    @JsonProperty("intStadiumCapacity")
    val intStadiumCapacity: Int,
    @JsonProperty("intFormedYear")
    val intFormedYear: Int,
    @JsonProperty("strLeague")
    val strLeague: String,
    @JsonProperty("strTeamBadge")
    val strTeamBadge: String,
    @JsonProperty("strDescriptionFR")
    val strDescriptionFR: String?,
    @JsonProperty("strCountry")
    val strCountry: String,
    @JsonProperty("strTeamBanner")
    val strTeamBanner: String?,
)