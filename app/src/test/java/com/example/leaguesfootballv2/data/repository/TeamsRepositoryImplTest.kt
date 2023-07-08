package com.example.leaguesfootballv2.data.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.datasource.TeamsDataSource
import com.example.leaguesfootballv2.data.mock.TeamsJsonResponseMock
import com.example.leaguesfootballv2.data.mock.TeamsMock
import com.example.leaguesfootballv2.data.transformer.TeamsToDomainTransformer
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
internal class TeamsRepositoryImplTest {

    @Mock
    private lateinit var dataSource: TeamsDataSource

    @Mock
    private lateinit var transformer: TeamsToDomainTransformer

    @InjectMocks
    private lateinit var repository: TeamsRepositoryImpl

    @Test
    fun `fetchTeamsByLeague - when data source call is success - then should return Success with data`() =
        runTest {
            // Given
            given(dataSource.execute(param = "league")).willReturn(TeamsJsonResponseMock.jsonTeams)
            given(transformer.toDomain(jsonTeams = TeamsJsonResponseMock.jsonTeams)).willReturn(
                TeamsMock.teams
            )

            // When
            val result = repository.fetchTeamsByLeague("league")

            // Then
            assertThat(result).isEqualTo(Result.Success(data = TeamsMock.teams))
        }
}