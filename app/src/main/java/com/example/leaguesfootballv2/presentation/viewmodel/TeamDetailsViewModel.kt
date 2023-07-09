package com.example.leaguesfootballv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.core.di.IoDispatcher
import com.example.leaguesfootballv2.domain.interactor.TeamDetailsInteractor
import com.example.leaguesfootballv2.presentation.state.TeamDetailsUiState
import com.example.leaguesfootballv2.presentation.transformer.SingleTeamToDisplayTransformer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamDetailsViewModel @Inject constructor(
    private val interactor: TeamDetailsInteractor,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val transformer: SingleTeamToDisplayTransformer
): ViewModel() {

    private var _teamDetailsUiState = MutableStateFlow<TeamDetailsUiState>(value = TeamDetailsUiState.Idle)
    val teamDetailsUiState: StateFlow<TeamDetailsUiState> = _teamDetailsUiState

    fun getPersistedSingleTeam(teamId: String) = viewModelScope.launch(context = dispatcher) {
        _teamDetailsUiState.value = interactor.getSingleTeam(teamId = teamId).let { team ->
            if (team is Result.Success)
                TeamDetailsUiState.Ready(teamDetails = transformer.singleTeamToDisplay(team = team.data))
            else
                TeamDetailsUiState.Error
        }
    }
}