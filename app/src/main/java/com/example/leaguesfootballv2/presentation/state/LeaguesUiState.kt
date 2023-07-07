package com.example.leaguesfootballv2.presentation.state

sealed interface LeaguesUiState {
    object Idle : LeaguesUiState
    object Loading : LeaguesUiState
    data class Ready(val leagues: List<String>) : LeaguesUiState
    object Error : LeaguesUiState
}