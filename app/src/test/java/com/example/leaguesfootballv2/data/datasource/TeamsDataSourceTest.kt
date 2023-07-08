package com.example.leaguesfootballv2.data.datasource

import com.example.leaguesfootballv2.core.network.ApiService
import com.example.leaguesfootballv2.data.mock.TeamsJsonResponseMock
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class TeamsDataSourceTest {

    @Mock
    private lateinit var apiService: ApiService

    @InjectMocks
    private lateinit var dataSource: TeamsDataSource

    @Test
    fun `execute - when response body is not null - then should return JsonTeams`() = runTest {
        // Given
        given(apiService.fetchTeamsByLeague("league")).willReturn(
            Response.success(TeamsJsonResponseMock.jsonTeams)
        )

        // When
        val result = dataSource.execute(param = "league")

        // Then
        assertThat(result).isEqualTo(TeamsJsonResponseMock.jsonTeams)
    }

    @Test
    fun `execute - when response body is null - then should throw exception`() = runTest {
        // Given
        given(apiService.fetchTeamsByLeague("league")).willReturn(Response.success(null))

        // When
        assertThrows<Exception> { dataSource.execute(param = "league") }
    }
}