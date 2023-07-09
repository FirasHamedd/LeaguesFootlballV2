package com.example.leaguesfootballv2.domain.interactor

import com.example.leaguesfootballv2.domain.repository.TeamDetailsRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.then

@ExtendWith(MockitoExtension::class)
internal class TeamDetailsInteractorTest {

    @Mock
    private lateinit var repositoryImpl: TeamDetailsRepository

    @InjectMocks
    private lateinit var interactor: TeamDetailsInteractor

    @Test
    fun getSingleTeam() = runTest {
        // When
        interactor.getSingleTeam(teamId = "id")

        // Then
        then(repositoryImpl).should().fetchPersistedSingleTeam(teamId = "id")
        then(repositoryImpl).shouldHaveNoMoreInteractions()
    }
}