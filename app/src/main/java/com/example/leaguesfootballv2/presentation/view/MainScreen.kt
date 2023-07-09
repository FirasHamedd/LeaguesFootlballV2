package com.example.leaguesfootballv2.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.leaguesfootballv2.R
import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDisplayModel
import com.example.leaguesfootballv2.presentation.state.LeaguesUiState
import com.example.leaguesfootballv2.presentation.state.TeamsUiState
import com.example.leaguesfootballv2.presentation.view.composable.AutoCompleteSearchBar
import com.example.leaguesfootballv2.presentation.view.composable.ScreenContent
import com.example.leaguesfootballv2.presentation.viewmodel.LeaguesViewModel

private const val COLUMNS_NUMBER = 2

@Composable
fun MainScreen(
    viewModel: LeaguesViewModel,
    onTeamClick: (String) -> Unit
) {
    val allLeaguesUiState by viewModel.leaguesUiState.collectAsStateWithLifecycle()
    val teamsByLeagueUiState by viewModel.teamsUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllLeagues()
        viewModel.getPersistedTeams()
    }
    MainScreenContent(
        allLeaguesUiState = allLeaguesUiState,
        teamsByLeagueUiState = teamsByLeagueUiState,
        onTeamsSearch = { viewModel.getTeamsByLeague(league = it) },
        onTeamClick = onTeamClick
    )
}

@Composable
fun MainScreenContent(
    allLeaguesUiState: LeaguesUiState,
    teamsByLeagueUiState: TeamsUiState,
    onTeamsSearch: (String) -> Unit,
    onTeamClick: (String) -> Unit
) {
    ScreenContent(hasToolbar = false) {
        var allLeagues by remember { mutableStateOf(emptyList<String>()) }
        var teams by remember { mutableStateOf(emptyList<TeamDisplayModel>()) }
        val isUserTyping = remember { mutableStateOf(false) }
        var isSearching by remember { mutableStateOf(false) }

        when (allLeaguesUiState) {
            LeaguesUiState.Idle -> println("hooo idle")
            LeaguesUiState.Loading -> println("hooo loading")
            is LeaguesUiState.Ready -> allLeagues = allLeaguesUiState.leagues
            LeaguesUiState.Error -> println("hooo Error")
        }

        when (teamsByLeagueUiState) {
            TeamsUiState.Idle -> println("hooo idle")
            is TeamsUiState.Ready -> {
                teams = teamsByLeagueUiState.teams
                isSearching = false
            }
            TeamsUiState.Error -> println("hooo Error")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White
                )
        ) {
            AutoCompleteSearchBar(
                modifier = Modifier.fillMaxWidth(),
                items = allLeagues,
                isUserTyping = isUserTyping,
                onSearchClicked = {
                    isSearching = true
                    onTeamsSearch(it)
                },
            )

            if (isSearching) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                LazyVerticalGrid(
                    modifier = Modifier
                        .blur(if (isUserTyping.value) 12.dp else 0.dp)
                        .fillMaxSize()
                        .background(if (isUserTyping.value) Color.LightGray else Color.White),
                    columns = GridCells.Fixed(COLUMNS_NUMBER)
                ) {
                    items(teams) { team ->
                        AsyncImage(
                            modifier = Modifier
                                .clickable {
                                    onTeamClick(team.teamId)
                                }
                                .padding(all = 16.dp),
                            alignment = Alignment.Center,
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(team.teamLogo)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(id = R.drawable.placeholder),
                            contentDescription = null,
                        )
                    }
                }
            }
        }

    }
}

@Preview(name = "MainScreen")
@Composable
fun MainScreenPreview() {
    MainScreenContent(
        allLeaguesUiState = LeaguesUiState.Ready(
            leagues = listOf(
                "Liga",
                "Ligue 1",
                "Premier League",
                "Bundesliga",
                "Eredivisie",
                "Serie A"
            )
        ),
        teamsByLeagueUiState = TeamsUiState.Ready(
            teams = listOf(
                TeamDisplayModel(
                    teamId = "14444",
                    teamName = "Arsenal",
                    teamLogo = "https://www.thesportsdb.com/images/media/team/badge/q3bx641635067495.png"
                ),
                TeamDisplayModel(
                    teamId = "133604",
                    teamName = "Manchester United",
                    teamLogo = "https://www.thesportsdb.com/images/media/team/badge/yk7swg1547214677.png"
                ),
                TeamDisplayModel(
                    teamId = "14444",
                    teamName = "Arsenal",
                    teamLogo = "https://www.thesportsdb.com/images/media/team/badge/n06q811667936857.png"
                ),
                TeamDisplayModel(
                    teamId = "133604",
                    teamName = "Manchester United",
                    teamLogo = "https://www.thesportsdb.com/images/media/team/badge/d89tpa1589898020.png"
                ),
                TeamDisplayModel(
                    teamId = "14444",
                    teamName = "Arsenal",
                    teamLogo = "https://www.thesportsdb.com/images/media/team/badge/q3bx641635067495.png"
                ),
                TeamDisplayModel(
                    teamId = "133604",
                    teamName = "Manchester United",
                    teamLogo = "https://www.thesportsdb.com/images/media/team/badge/n06q811667936857.png"
                ),
            )
        ),
        onTeamsSearch = {},
        onTeamClick = {}
    )
}