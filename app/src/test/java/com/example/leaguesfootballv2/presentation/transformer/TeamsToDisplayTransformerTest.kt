package com.example.leaguesfootballv2.presentation.transformer

import com.example.leaguesfootballv2.data.mock.TeamsMock
import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDisplayModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TeamsToDisplayTransformerTest {

    private val transformer = TeamsToDisplayTransformer()

    @Test
    fun teamsToDisplayModel() {
        // When
        val result = transformer.teamsToDisplayModel(teams = TeamsMock.teams)

        // Then
        assertThat(result).isEqualTo(
            listOf(
                TeamDisplayModel(
                    teamId = "133604",
                    teamName = "Arsenal",
                    teamLogo = "arsenalBageUrl"
                ),
                TeamDisplayModel(
                    teamId = "14444",
                    teamName = "Manchester United",
                    teamLogo = "manchesterBageUrl"
                ),
            )
        )
    }
}