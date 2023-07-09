package com.example.leaguesfootballv2.data.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.datasource.LocalTeamsDataSource
import com.example.leaguesfootballv2.data.datasource.TeamsDataSource
import com.example.leaguesfootballv2.data.mock.TeamsJsonResponseMock
import com.example.leaguesfootballv2.data.mock.TeamsMock
import com.example.leaguesfootballv2.data.model.JsonTeams
import com.example.leaguesfootballv2.data.transformer.TeamsToDomainTransformer
import com.example.leaguesfootballv2.domain.model.TeamEntity
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import org.mockito.kotlin.then

@ExtendWith(MockitoExtension::class)
internal class TeamsRepositoryImplTest {

    @Mock
    private lateinit var dataSource: TeamsDataSource

    @Mock
    private lateinit var localTeamsDataSource: LocalTeamsDataSource

    @Mock
    private lateinit var transformer: TeamsToDomainTransformer

    @InjectMocks
    private lateinit var repository: TeamsRepositoryImpl

    @Test
    fun `fetchTeamsByLeague - when data source call is success and teams is not null - then should return Success with data`() =
        runTest {
            // Given
            given(dataSource.execute(param = "league")).willReturn(TeamsJsonResponseMock.jsonTeams)
            given(transformer.toDomain(jsonTeams = TeamsJsonResponseMock.jsonTeams.teams!!)).willReturn(
                TeamsMock.teams
            )

            // When
            val result = repository.fetchTeamsByLeague("league")

            // Then
            assertThat(result).isEqualTo(Result.Success(data = TeamsMock.teams))
            then(localTeamsDataSource).should()
                .save(teams = TeamsJsonResponseMock.jsonTeams.teams!!)
            then(localTeamsDataSource).shouldHaveNoMoreInteractions()
        }

    @Test
    fun `fetchTeamsByLeague - when data source call is success and teams is null - then should return Failure`() =
        runTest {
            // Given
            val response = JsonTeams(teams = null)
            given(dataSource.execute(param = "league")).willReturn(response)

            // When
            val result = repository.fetchTeamsByLeague("league")

            // Then
            assertThat(result).isEqualTo(Result.Failure<List<TeamEntity>>("No Teams for your search"))
        }

    @Test
    fun `fetchPersistedTeams - when local data source call is success - then should return Success with data`() =
        runBlocking {
            // Given
            val flow = flowOf(TeamsJsonResponseMock.jsonTeams.teams!!)
            given(localTeamsDataSource.execute(param = Unit)).willReturn(flow)
            given(transformer.toDomain(jsonTeams = TeamsJsonResponseMock.jsonTeams.teams!!)).willReturn(
                TeamsMock.teams
            )

            // When
            val result = repository.fetchPersistedTeams()

            // Then
            result.collect{
                assertThat(it).isEqualTo(TeamsMock.teams)
            }
        }
}