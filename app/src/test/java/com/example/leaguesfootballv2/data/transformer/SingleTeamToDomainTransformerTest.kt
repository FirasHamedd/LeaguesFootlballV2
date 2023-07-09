package com.example.leaguesfootballv2.data.transformer

import com.example.leaguesfootballv2.data.mock.TeamsJsonResponseMock
import com.example.leaguesfootballv2.data.mock.TeamsMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SingleTeamToDomainTransformerTest {

    private val transformer = SingleTeamToDomainTransformer()

    @Test
    fun `singleTeamToDomain - should return TeamEntity`() {
        // When
        val result = transformer.singleTeamToDomain(jsonTeam = TeamsJsonResponseMock.jsonTeams.teams!!.first())

        // Then
        assertThat(result).isEqualTo(TeamsMock.teams.first())
    }
}