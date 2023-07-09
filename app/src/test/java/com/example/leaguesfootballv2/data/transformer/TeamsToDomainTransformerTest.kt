package com.example.leaguesfootballv2.data.transformer

import com.example.leaguesfootballv2.data.mock.TeamsJsonResponseMock
import com.example.leaguesfootballv2.data.mock.TeamsMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TeamsToDomainTransformerTest {

    private val transformer = TeamsToDomainTransformer()

    @Test
    fun `toDomain - should return list of LeagueEntity`() {
        // When
        val result = transformer.toDomain(jsonTeams = TeamsJsonResponseMock.jsonTeams.teams!!)

        // Then
        assertThat(result).isEqualTo(TeamsMock.teams)
    }
}