package com.example.leaguesfootballv2.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.leaguesfootballv2.R
import com.example.leaguesfootballv2.presentation.state.LeaguesUiState
import com.example.leaguesfootballv2.presentation.view.composable.AutoCompleteSearchBar
import com.example.leaguesfootballv2.presentation.viewmodel.LeaguesViewModel

@Composable
fun MainScreen(viewModel: LeaguesViewModel) {
    val allLeaguesUiState by viewModel.leaguesUiState.collectAsStateWithLifecycle()
    MainScreenContent(allLeaguesUiState = allLeaguesUiState)
}

@Composable
fun MainScreenContent(allLeaguesUiState: LeaguesUiState) {
    var allLeagues by remember { mutableStateOf(emptyList<String>()) }
    val isUserTyping = remember { mutableStateOf(false) }

    when (allLeaguesUiState) {
        is LeaguesUiState.Idle -> println("hooo idle")
        is LeaguesUiState.Loading -> println("hooo loading")
        is LeaguesUiState.Ready -> allLeagues = allLeaguesUiState.leagues
        is LeaguesUiState.Error -> println("hooo Error")
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
                println("hooo $it")
            },
        )

        if (isUserTyping.value.not()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isUserTyping.value) Color.DarkGray else Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
                Icon(imageVector = Icons.Filled.List, contentDescription = null)
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
    )
}