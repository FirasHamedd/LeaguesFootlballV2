package com.example.leaguesfootballv2.presentation.state

import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDisplayModel

sealed interface TeamsUiState {
    object Idle : TeamsUiState
    data class Ready(val teams: List<TeamDisplayModel>) : TeamsUiState
    object Error : TeamsUiState
}