package com.example.leaguesfootballv2.domain.interactor

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.mock.TeamsMock
import com.example.leaguesfootballv2.domain.model.TeamEntity
import com.example.leaguesfootballv2.domain.repository.LeaguesRepository
import com.example.leaguesfootballv2.domain.repository.TeamsRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import org.mockito.kotlin.then

@ExtendWith(MockitoExtension::class)
internal class LeaguesInteractorTest {

    @Mock
    private lateinit var leaguesRepository: LeaguesRepository

    @Mock
    private lateinit var teamsRepository: TeamsRepository

    @InjectMocks
    private lateinit var interactor: LeaguesInteractor

    @Test
    fun getAllLeagues() = runTest {
        // When
        interactor.getAllLeagues()

        // Then
        then(leaguesRepository).should().fetchAllLeagues()
        then(leaguesRepository).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `getTeamsByLeague - when result is Success - then should return Success with sorted list`() =
        runTest {
            // Given
            given(teamsRepository.fetchTeamsByLeague("league")).willReturn(Result.Success(data = TeamsMock.teams))

            // When
            val result = interactor.getTeamsByLeague("league")

            // Then
            assertThat(result).isEqualTo(
                Result.Success(
                    data = listOf(
                        TeamEntity(
                            idTeam = "14444",
                            strTeam = "Manchester United",
                            strTeamShort = "MUC",
                            intStadiumCapacity = 50000,
                            intFormedYear = 1900,
                            strLeague = "English Premier League",
                            strTeamBadge = "manchesterBageUrl",
                            strDescriptionFR = "Description FR",
                            strCountry = "Country",
                            strTeamBanner = "teamBanner"
                        )
                    )
                )
            )
        }

    @Test
    fun `getTeamsByLeague - when result is Failure - then should return Failure`() =
        runTest {
            // Given
            given(teamsRepository.fetchTeamsByLeague("league")).willReturn(Result.Failure())

            // When
            val result = interactor.getTeamsByLeague("league")

            // Then
            assertThat(result).isEqualTo(Result.Failure<List<TeamEntity>>())
        }

    @Test
    fun `getPersistedTeams - when result is Success - then should return Success with sorted list`() =
        runBlocking {
            // Given
            val flow = flowOf(TeamsMock.teams)
            given(teamsRepository.fetchPersistedTeams()).willReturn(flow)

            // When
            val result = interactor.getPersistedTeams()

            // Then
            result.collect {
                assertThat(it).isEqualTo(
                    listOf(
                        TeamEntity(
                            idTeam = "14444",
                            strTeam = "Manchester United",
                            strTeamShort = "MUC",
                            intStadiumCapacity = 50000,
                            intFormedYear = 1900,
                            strLeague = "English Premier League",
                            strTeamBadge = "manchesterBageUrl",
                            strDescriptionFR = "Description FR",
                            strCountry = "Country",
                            strTeamBanner = "teamBanner"
                        )
                    )
                )
            }
        }
}