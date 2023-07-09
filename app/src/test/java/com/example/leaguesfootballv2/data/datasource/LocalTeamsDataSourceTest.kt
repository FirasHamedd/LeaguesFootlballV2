package com.example.leaguesfootballv2.data.datasource

import com.example.leaguesfootballv2.core.local.TeamsByLeagueDao
import com.example.leaguesfootballv2.data.mock.TeamsJsonResponseMock
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.then

@ExtendWith(MockitoExtension::class)
internal class LocalTeamsDataSourceTest {

    @Mock
    private lateinit var teamsByLeagueDao: TeamsByLeagueDao

    @InjectMocks
    private lateinit var dataSource: LocalTeamsDataSource

    @Test
    fun execute() = runTest {
        // When
        dataSource.execute(param = Unit)

        // Then
        then(teamsByLeagueDao).should().getTeams()
        then(teamsByLeagueDao).shouldHaveNoMoreInteractions()
    }

    @Test
    fun save() = runTest {
        // When
        dataSource.save(teams = TeamsJsonResponseMock.jsonTeams.teams!!)

        // Then
        then(teamsByLeagueDao).should().deleteAllTeams()
        then(teamsByLeagueDao).should().insertAll(TeamsJsonResponseMock.jsonTeams.teams!!)
        then(teamsByLeagueDao).shouldHaveNoMoreInteractions()
    }
}