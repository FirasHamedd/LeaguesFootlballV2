package com.example.leaguesfootballv2.presentation.state

sealed interface TeamsUiState {
    object Idle : TeamsUiState
    data class Ready(val teamsPics: List<String>) : TeamsUiState
    object Error : TeamsUiState
}