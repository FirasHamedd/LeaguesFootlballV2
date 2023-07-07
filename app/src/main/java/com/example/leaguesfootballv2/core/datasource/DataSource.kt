package com.example.leaguesfootballv2.core.datasource

interface DataSource<PARAM, RESPONSE> {

    suspend fun execute(param: PARAM): RESPONSE
}
