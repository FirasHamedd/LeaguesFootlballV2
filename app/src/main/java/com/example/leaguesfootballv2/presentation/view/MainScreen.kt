package com.example.leaguesfootballv2.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.leaguesfootballv2.presentation.state.LeaguesUiState
import com.example.leaguesfootballv2.presentation.state.TeamsUiState
import com.example.leaguesfootballv2.presentation.view.composable.AutoCompleteSearchBar
import com.example.leaguesfootballv2.presentation.viewmodel.LeaguesViewModel

@Composable
fun MainScreen(viewModel: LeaguesViewModel) {
    val allLeaguesUiState by viewModel.leaguesUiState.collectAsStateWithLifecycle()
    val teamsByLeagueUiState by viewModel.teamsUiState.collectAsStateWithLifecycle()
    MainScreenContent(
        allLeaguesUiState = allLeaguesUiState,
        teamsByLeagueUiState = teamsByLeagueUiState,
        onTeamsSearch = { viewModel.getTeamsByLeague(league = it) }
    )
}

@Composable
fun MainScreenContent(
    allLeaguesUiState: LeaguesUiState,
    teamsByLeagueUiState: TeamsUiState,
    onTeamsSearch: (String) -> Unit
) {
    var allLeagues by remember { mutableStateOf(emptyList<String>()) }
    var teamsPics by remember { mutableStateOf(emptyList<String>()) }
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
        is TeamsUiState.Ready ->{
            teamsPics = teamsByLeagueUiState.teamsPics
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
                println("hooo $it")
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
                    .fillMaxSize()
                    .background(if (isUserTyping.value) Color.DarkGray else Color.White),
                columns = GridCells.Fixed(2)
            ) {
                items(teamsPics) { picUrl ->
                    AsyncImage(
                        modifier = Modifier
                            .padding(all = 16.dp),
                        alignment = Alignment.Center,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(picUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                    )
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
            teamsPics = listOf(
                "https://www.thesportsdb.com/images/media/team/badge/q3bx641635067495.png",
                "https://www.thesportsdb.com/images/media/team/badge/n06q811667936857.png",
                "https://www.thesportsdb.com/images/media/team/badge/yk7swg1547214677.png",
                "https://www.thesportsdb.com/images/media/team/badge/d89tpa1589898020.png",
                "https://www.thesportsdb.com/images/media/team/badge/lg7qrc1621594751.png",
                "https://www.thesportsdb.com/images/media/team/badge/d89tpa1589898020.png",
                "https://www.thesportsdb.com/images/media/team/badge/jyijfi1581543162.png",
                "https://www.thesportsdb.com/images/media/team/badge/q3bx641635067495.png",
                "https://www.thesportsdb.com/images/media/team/badge/n06q811667936857.png",
                "https://www.thesportsdb.com/images/media/team/badge/yk7swg1547214677.png",
                "https://www.thesportsdb.com/images/media/team/badge/lg7qrc1621594751.png",
            )
        ),
        onTeamsSearch = {}
    )
}