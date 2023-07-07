package com.example.leaguesfootballv2.domain.interactor

import com.example.leaguesfootballv2.domain.repository.LeaguesRepository
import javax.inject.Inject

class LeaguesInteractor @Inject constructor(
    private val leaguesRepository: LeaguesRepository
){
    suspend fun getAllLeagues() = leaguesRepository.fetchAllLeagues()
}