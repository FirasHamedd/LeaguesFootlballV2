package com.example.leaguesfootballv2.data.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.data.datasource.LeaguesDataSource
import com.example.leaguesfootballv2.data.mock.AllLeaguesJsonResponseMock
import com.example.leaguesfootballv2.data.mock.AllLeaguesMock
import com.example.leaguesfootballv2.data.transformer.LeaguesToDomainTransformer
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

    @Test
    fun `fetchAllLeagues - when data source call is success - then should return Success with data`() = runTest {
        // Given
        given(dataSource.execute(param = Unit)).willReturn(AllLeaguesJsonResponseMock.jsonAllLeagues)
        given(transformer.toDomain(jsonLeagues = AllLeaguesJsonResponseMock.jsonAllLeagues)).willReturn(AllLeaguesMock.leagues)

        // When
        val result = repository.fetchAllLeagues()

        // Then
        assertThat(result).isEqualTo(Result.Success(data = AllLeaguesMock.leagues))
    }
}