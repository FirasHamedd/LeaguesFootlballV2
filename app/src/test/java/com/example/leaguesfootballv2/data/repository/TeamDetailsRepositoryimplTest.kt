package com.example.leaguesfootballv2.data.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.datasource.LocalTeamDetailsDataSource
import com.example.leaguesfootballv2.data.mock.TeamsJsonResponseMock
import com.example.leaguesfootballv2.data.mock.TeamsMock
import com.example.leaguesfootballv2.data.transformer.SingleTeamToDomainTransformer
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
internal class TeamDetailsRepositoryimplTest {

    @Mock
    private lateinit var localTeamDetailsDataSource: LocalTeamDetailsDataSource

    @Mock
    private lateinit var transformer: SingleTeamToDomainTransformer

    @InjectMocks
    private lateinit var repository: TeamDetailsRepositoryImpl

    @Test
    fun `fetchPersistedSingleTeam - when local data source call is success - then should return Success with data`() =
        runTest {
            // Given
            given(localTeamDetailsDataSource.execute(param = "id")).willReturn(TeamsJsonResponseMock.jsonTeams.teams!!.first())
            given(transformer.singleTeamToDomain(jsonTeam = TeamsJsonResponseMock.jsonTeams.teams!!.first())).willReturn(
                TeamsMock.teams.first()
            )

            // When
            val result = repository.fetchPersistedSingleTeam(teamId = "id")

            // Then
            assertThat(result).isEqualTo(Result.Success(data = TeamsMock.teams.first()))
        }
}