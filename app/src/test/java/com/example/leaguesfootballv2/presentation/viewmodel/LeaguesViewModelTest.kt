package com.example.leaguesfootballv2.presentation.viewmodel

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.mock.AllLeaguesMock
import com.example.leaguesfootballv2.data.mock.TeamsMock
import com.example.leaguesfootballv2.domain.interactor.LeaguesInteractor
import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDisplayModel
import com.example.leaguesfootballv2.presentation.state.LeaguesUiState
import com.example.leaguesfootballv2.presentation.state.TeamsUiState
import com.example.leaguesfootballv2.presentation.transformer.TeamsToDisplayTransformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
internal class LeaguesViewModelTest {

    @Mock
    private lateinit var interactor: LeaguesInteractor

    @Mock
    private lateinit var transformer: TeamsToDisplayTransformer

    private lateinit var viewModel: LeaguesViewModel

    private val scheduler: TestCoroutineScheduler = TestCoroutineScheduler()

    private val testDispatcher: TestDispatcher = StandardTestDispatcher(scheduler)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LeaguesViewModel(
            interactor = interactor,
            dispatcher = testDispatcher,
            transformer = transformer
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllLeagues - when Result is Success - then should update leaguesUiState`() = runTest {
        // Given
        given(interactor.getAllLeagues()).willReturn(Result.Success(data = AllLeaguesMock.leagues))

        // When
        viewModel.getAllLeagues()

        // Then
        scheduler.advanceUntilIdle()
        assertThat(viewModel.leaguesUiState.value).isEqualTo(
            LeaguesUiState.Ready(
                leagues = listOf(
                    "English Premier League",
                    "English League Championship"
                )
            )
        )
    }

    @Test
    fun `getAllLeagues - when Result is Failure - then should update leaguesUiState`() = runTest {
        // Given
        given(interactor.getAllLeagues()).willReturn(Result.Failure())

        // When
        viewModel.getAllLeagues()

        // Then
        scheduler.advanceUntilIdle()
        assertThat(viewModel.leaguesUiState.value).isEqualTo(LeaguesUiState.Error)
    }

    @Test
    fun `getTeamsByLeague - when Result is Success - then should update teamsUiState`() = runTest {
        // Given
        given(interactor.getTeamsByLeague(league = "league")).willReturn(Result.Success(data = TeamsMock.teams))

        // When
        viewModel.getTeamsByLeague(league = "league")

        // Then
        scheduler.advanceUntilIdle()
        assertThat(viewModel.teamsUiState.value).isEqualTo(TeamsUiState.Ready)
    }

    @Test
    fun `getTeamsByLeague - when Result is Failure - then should update teamsUiState`() = runTest {
        // Given
        given(interactor.getTeamsByLeague("league")).willReturn(Result.Failure())

        // When
        viewModel.getTeamsByLeague("league")

        // Then
        scheduler.advanceUntilIdle()
        assertThat(viewModel.teamsUiState.value).isEqualTo(TeamsUiState.Error)
    }

    @Test
    fun `getPersistedTeams - when Result is Success - then should update teamsUiState`() = runTest {
        // Given
        val displayModels = listOf(
            TeamDisplayModel(
                teamId = "14444",
                teamName = "Arsenal",
                teamLogo = "arsenalBageUrl"
            ),
            TeamDisplayModel(
                teamId = "133604",
                teamName = "Manchester United",
                teamLogo = "manchesterBageUrl"
            ),
        )
        val flow = flowOf(value = TeamsMock.teams)
        given(interactor.getPersistedTeams()).willReturn(flow)
        given(transformer.teamsToDisplayModel(teams = TeamsMock.teams)).willReturn(displayModels)

        // When
        viewModel.getPersistedTeams()

        // Then
        scheduler.advanceUntilIdle()
        assertThat(viewModel.persistedTeams.value).isEqualTo(displayModels)
    }
}