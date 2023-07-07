package com.example.leaguesfootballv2.domain.interactor

import com.example.leaguesfootballv2.domain.repository.LeaguesRepository
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
    private lateinit var repository: LeaguesRepository

    @InjectMocks
    private lateinit var interactor: LeaguesInteractor

    @Test
    fun getAllLeagues() = runTest {
        // When
        interactor.getAllLeagues()

        // Then
        then(repository).should().fetchAllLeagues()
    }
}