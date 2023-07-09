package com.example.leaguesfootballv2.data.datasource

import com.example.leaguesfootballv2.core.local.TeamDetailsDao
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.then

@ExtendWith(MockitoExtension::class)
class LocalTeamDetailsDataSourceTest {

    @Mock
    private lateinit var teamDetailsDao: TeamDetailsDao

    @InjectMocks
    private lateinit var dataSource: LocalTeamDetailsDataSource

    @Test
    fun fetchSingleTeam() = runTest {
        // When
        dataSource.execute(param = "id")

        // Then
        then(teamDetailsDao).should().getSingleTeam(teamId = "id")
        then(teamDetailsDao).shouldHaveNoMoreInteractions()
    }
}