package com.example.leaguesfootballv2.data.transformer

import com.example.leaguesfootballv2.data.mock.AllLeaguesMockResponse
import com.example.leaguesfootballv2.domain.model.LeagueEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LeaguesToDomainTransformerTest {

    private val transformer = LeaguesToDomainTransformer()

    private val domain = listOf(
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
    fun `toDomain - should return list of LeagueEntity`() {
        // When
        val result = transformer.toDomain(jsonLeagues = AllLeaguesMockResponse.jsonAllLeagues)

        // Then
        assertThat(result).isEqualTo(domain)
    }
}