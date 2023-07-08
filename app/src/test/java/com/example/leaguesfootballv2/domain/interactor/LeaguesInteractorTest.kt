package com.example.leaguesfootballv2.domain.interactor

import com.example.leaguesfootballv2.domain.repository.LeaguesRepository
import com.example.leaguesfootballv2.domain.repository.TeamsRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.then

@ExtendWith(MockitoExtension::class)
internal class LeaguesInteractorTest {

    @Mock
    private lateinit var leaguesRepository: LeaguesRepository

    @Mock
    private lateinit var teamsRepository: TeamsRepository

    @InjectMocks
    private lateinit var interactor: LeaguesInteractor

    @Test
    fun getAllLeagues() = runTest {
        // When
        interactor.getAllLeagues()

        // Then
        then(leaguesRepository).should().fetchAllLeagues()
        then(leaguesRepository).shouldHaveNoMoreInteractions()
    }

    @Test
    fun getTeamsByLeague() = runTest {
        // When
        interactor.getTeamsByLeague("league")

        // Then
        then(teamsRepository).should().fetchTeamsByLeague("league")
        then(teamsRepository).shouldHaveNoMoreInteractions()
    }
}