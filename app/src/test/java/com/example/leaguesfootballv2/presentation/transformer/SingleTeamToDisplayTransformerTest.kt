package com.example.leaguesfootballv2.presentation.transformer

import com.example.leaguesfootballv2.data.mock.TeamsMock
import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDetailsDisplayModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SingleTeamToDisplayTransformerTest {

    private val transformer = SingleTeamToDisplayTransformer()

    @Test
    fun singleTeamToDisplay() {
        // When
        val result = transformer.singleTeamToDisplay(team = TeamsMock.teams.first())

        // Then
        assertThat(result).isEqualTo(
            TeamDetailsDisplayModel(
                teamName = "Arsenal",
                teamBadge = "arsenalBageUrl",
                teamShortName = "ARS",
                stadiumCapacity = 60338,
                formedYear = 1892,
                league = "English Premier League",
                description = "Description FR"
            )
        )
    }
}