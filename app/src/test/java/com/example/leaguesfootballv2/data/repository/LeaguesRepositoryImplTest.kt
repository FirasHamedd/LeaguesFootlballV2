package com.example.leaguesfootballv2.data.repository

import com.example.leaguesfootballv2.data.datasource.LeaguesDataSource
import com.example.leaguesfootballv2.data.mock.AllLeaguesMockResponse
import com.example.leaguesfootballv2.data.transformer.LeaguesToDomainTransformer
import com.example.leaguesfootballv2.domain.model.LeagueEntity
import com.example.leaguesfootballv2.core.Result
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
internal class LeaguesRepositoryImplTest {

    @Mock
    private lateinit var dataSource: LeaguesDataSource

    @Mock
    private lateinit var transformer: LeaguesToDomainTransformer

    @InjectMocks
    private lateinit var repository: LeaguesRepositoryImpl

    private val leagues = listOf(
        LeagueEntity(
            idLeague = "4328",
            strLeague = "English Premier League",
            strSport = "Soccer",
            strLeagueAlternate = "Premier League, EPL"
        ),
        LeagueEntity(
            idLeague = "4329",
            strLeague = "English League Championship",
            strSport = "Soccer",
            strLeagueAlternate = "Championship"
        ),
    )

    @Test
    fun `fetchAllLeagues - when data source call is success - then should return Success with data`() = runTest {
        // Given
        given(dataSource.execute(param = Unit)).willReturn(AllLeaguesMockResponse.jsonAllLeagues)
        given(transformer.toDomain(jsonLeagues = AllLeaguesMockResponse.jsonAllLeagues)).willReturn(leagues)

        // When
        val result = repository.fetchAllLeagues()

        // Then
        assertThat(result).isEqualTo(Result.Success(data = leagues))
    }

    @Test
    fun `fetchAllLeagues - when data source throws exception - then should return Failure`() = runTest {
        // TODO test exception
        // Given
        given(dataSource.execute(param = Unit)).willThrow(Exception::class.java)

        // When
        val result = repository.fetchAllLeagues()

        // Then
        assertThat(result).isEqualTo(Result.Failure<List<LeagueEntity>>())
    }
}