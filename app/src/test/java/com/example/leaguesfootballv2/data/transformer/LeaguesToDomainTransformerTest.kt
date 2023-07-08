package com.example.leaguesfootballv2.data.transformer

import com.example.leaguesfootballv2.data.mock.AllLeaguesJsonResponseMock
import com.example.leaguesfootballv2.data.mock.AllLeaguesMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LeaguesToDomainTransformerTest {

    private val transformer = LeaguesToDomainTransformer()

    @Test
    fun `toDomain - should return list of LeagueEntity`() {
        // When
        val result = transformer.toDomain(jsonLeagues = AllLeaguesJsonResponseMock.jsonAllLeagues)

        // Then
        assertThat(result).isEqualTo(AllLeaguesMock.leagues)
    }
}