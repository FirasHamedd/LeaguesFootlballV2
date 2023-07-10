package com.example.leaguesfootballv2.presentation.state

sealed interface TeamsUiState {
    object Idle : TeamsUiState
    object Ready : TeamsUiState
    object Error : TeamsUiState
}