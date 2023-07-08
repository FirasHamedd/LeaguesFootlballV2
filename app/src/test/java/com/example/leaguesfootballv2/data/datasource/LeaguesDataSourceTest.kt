package com.example.leaguesfootballv2.data.datasource

import com.example.leaguesfootballv2.core.network.ApiService
import com.example.leaguesfootballv2.data.mock.AllLeaguesJsonResponseMock
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

    @Test
    fun `execute - when response body is not null - then should return JsonLeagues`() = runTest {
        // Given
        given(apiService.fetchAllLeagues()).willReturn(Response.success(AllLeaguesJsonResponseMock.jsonAllLeagues))

        // When
        val result = dataSource.execute(param = Unit)

        // Then
        assertThat(result).isEqualTo(AllLeaguesJsonResponseMock.jsonAllLeagues)
    }

    @Test
    fun `execute - when response body is null - then should throw exception`() = runTest {
        // Given
        given(apiService.fetchAllLeagues()).willReturn(Response.success(null))

        // When
        assertThrows<Exception> { dataSource.execute(Unit) }
    }
}