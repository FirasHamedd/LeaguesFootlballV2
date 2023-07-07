package com.example.leaguesfootballv2.domain.repository

import com.example.leaguesfootballv2.core.Result
import com.example.leaguesfootballv2.domain.model.LeagueEntity

interface LeaguesRepository {

    suspend fun fetchAllLeagues(): Result<List<LeagueEntity>>
}