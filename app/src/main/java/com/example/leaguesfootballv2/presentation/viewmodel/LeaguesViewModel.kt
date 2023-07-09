package com.example.leaguesfootballv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.core.di.IoDispatcher
import com.example.leaguesfootballv2.domain.interactor.LeaguesInteractor
import com.example.leaguesfootballv2.presentation.state.LeaguesUiState
import com.example.leaguesfootballv2.presentation.state.TeamsUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LeaguesViewModel @Inject constructor(
    private val interactor: LeaguesInteractor,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _leaguesUiState = MutableStateFlow<LeaguesUiState>(value = LeaguesUiState.Idle)
    val leaguesUiState: StateFlow<LeaguesUiState> = _leaguesUiState

    private var _teamsUiState = MutableStateFlow<TeamsUiState>(value = TeamsUiState.Idle)
    val teamsUiState: StateFlow<TeamsUiState> = _teamsUiState

    fun getAllLeagues() = viewModelScope.launch(context = dispatcher) {
        _leaguesUiState.value = interactor.getAllLeagues().let { leagues ->
            if (leagues is Result.Success)
                LeaguesUiState.Ready(leagues = leagues.data.map { it.strLeague })
            else
                LeaguesUiState.Error
        }
    }

    fun getTeamsByLeague(league: String) = viewModelScope.launch(context = dispatcher) {
        _teamsUiState.value = interactor.getTeamsByLeague(league = league).let { teams ->
            if (teams is Result.Success)
                TeamsUiState.Ready(teamsPics = teams.data.map { it.strTeamBadge })
            else
                TeamsUiState.Error
        }
    }

    fun getPersistedTeams() = viewModelScope.launch(context = dispatcher) {
        _teamsUiState.value = interactor.getPersistedTeams().let { persistedTeams ->
            if (persistedTeams is Result.Success)
                TeamsUiState.Ready(teamsPics = persistedTeams.data.map { it.strTeamBadge })
            else
                TeamsUiState.Error
        }
    }
}