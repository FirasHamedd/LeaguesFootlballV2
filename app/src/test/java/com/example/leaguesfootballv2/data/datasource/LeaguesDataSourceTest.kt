package com.example.leaguesfootballv2.data.datasource

import com.example.leaguesfootballv2.core.network.ApiService
import com.example.leaguesfootballv2.data.model.JsonLeague
import com.example.leaguesfootballv2.data.model.JsonLeagues
import kotlinx.coroutines.test.*
import okhttp3.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import retrofit2.Response
import org.junit.jupiter.api.assertThrows

@ExtendWith(MockitoExtension::class)
class LeaguesDataSourceTest {

    @Mock
    private lateinit var apiService: ApiService

    @InjectMocks
    private lateinit var dataSource: LeaguesDataSource

    private val response = JsonLeagues(
        leagues = listOf(
            JsonLeague(
                idLeague = "4328",
                strLeague = "English Premier League",
                strSport = "Soccer",
                strLeagueAlternate = "Premier League, EPL"
            ),
            JsonLeague(
                idLeague = "4329",
                strLeague = "English League Championship",
                strSport = "Soccer",
                strLeagueAlternate = "Championship"
            ),
        )
    )


    @Test
    fun `execute - when response body is not null - then should return JsonLeagues`() = runTest {
        // Given
        given(apiService.getAllLeagues()).willReturn(Response.success(response))

        // When
        val result = dataSource.execute(param = Unit)

        // Then
        assertThat(result).isEqualTo(response)
    }

    @Test
    fun `execute - when response body is null - then should throw exception`() = runTest {
        // Given
        given(apiService.getAllLeagues()).willReturn(Response.success(null))

        // When
        assertThrows<Exception> { dataSource.execute(Unit) }
    }
}