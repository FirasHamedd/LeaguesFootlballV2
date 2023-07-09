package com.example.leaguesfootballv2.presentation.viewmodel

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.mock.TeamsMock
import com.example.leaguesfootballv2.domain.interactor.TeamDetailsInteractor
import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDetailsDisplayModel
import com.example.leaguesfootballv2.presentation.state.TeamDetailsUiState
import com.example.leaguesfootballv2.presentation.transformer.SingleTeamToDisplayTransformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
internal class TeamDetailsViewModelTest {

    @Mock
    private lateinit var interactor: TeamDetailsInteractor

    @Mock
    private lateinit var transformer: SingleTeamToDisplayTransformer

    private lateinit var viewModel: TeamDetailsViewModel

    private val scheduler: TestCoroutineScheduler = TestCoroutineScheduler()

    private val testDispatcher: TestDispatcher = StandardTestDispatcher(scheduler)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TeamDetailsViewModel(
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
    fun `getPersistedSingleTeam - when Result is Success - then should update teamDetailsUiState`() = runTest {
        // Given
        val teamDisplayModel = TeamDetailsDisplayModel(
            teamName = "Arsenal",
            teamBadge = "arsenalBageUrl",
            teamShortName = "ARS",
            stadiumCapacity = 60338,
            formedYear = 1892,
            league = "English Premier League",
            description = "Description FR"
        )
        given(interactor.getSingleTeam(teamId = "id")).willReturn(Result.Success(data = TeamsMock.teams.first()))
        given(transformer.singleTeamToDisplay(team = TeamsMock.teams.first())).willReturn(teamDisplayModel)

        // When
        viewModel.getPersistedSingleTeam(teamId = "id")

        // Then
        scheduler.advanceUntilIdle()
        assertThat(viewModel.teamDetailsUiState.value).isEqualTo(
            TeamDetailsUiState.Ready(teamDetails = teamDisplayModel)
        )
    }

    @Test
    fun `getPersistedSingleTeam - when Result is Failure - then should update teamDetailsUiState`() = runTest {
        // Given
        given(interactor.getSingleTeam(teamId = "id")).willReturn(Result.Failure())

        // When
        viewModel.getPersistedSingleTeam(teamId = "id")

        // Then
        scheduler.advanceUntilIdle()
        assertThat(viewModel.teamDetailsUiState.value).isEqualTo(TeamDetailsUiState.Error)
    }
}