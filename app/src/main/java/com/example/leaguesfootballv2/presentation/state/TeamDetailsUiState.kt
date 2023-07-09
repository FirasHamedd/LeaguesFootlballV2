package com.example.leaguesfootballv2.presentation.state

import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDetailsDisplayModel

interface TeamDetailsUiState {
    object Idle: TeamDetailsUiState
    data class Ready(val teamDetails: TeamDetailsDisplayModel): TeamDetailsUiState
    object Error: TeamDetailsUiState
}