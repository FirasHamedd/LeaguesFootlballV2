package com.example.leaguesfootballv2.presentation.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.leaguesfootballv2.R
import com.example.leaguesfootballv2.presentation.dispalymodel.TeamDetailsDisplayModel
import com.example.leaguesfootballv2.presentation.state.TeamDetailsUiState
import com.example.leaguesfootballv2.presentation.view.composable.ScreenContent
import com.example.leaguesfootballv2.presentation.viewmodel.TeamDetailsViewModel

private const val EMPTY_STRING = ""

@Composable
fun TeamsDetailsScreen(
    viewModel: TeamDetailsViewModel,
    teamId: String?,
    onBackClicked: () -> Unit
) {
    BackHandler { onBackClicked() }
    val teamDetailsState by viewModel.teamDetailsUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.getPersistedSingleTeam(teamId = teamId!!)
    }
    TeamDetailsScreenContent(
        teamDetailsState = teamDetailsState,
        onBackClicked = onBackClicked
    )
}

@Composable
fun TeamDetailsScreenContent(
    teamDetailsState: TeamDetailsUiState,
    onBackClicked: () -> Unit
) {
    var team by remember { mutableStateOf(TeamDetailsDisplayModel()) }

    LaunchedEffect(key1 = teamDetailsState) {
        when (teamDetailsState) {
            TeamDetailsUiState.Idle -> Unit
            is TeamDetailsUiState.Ready -> team = teamDetailsState.teamDetails
            TeamDetailsUiState.Error -> Unit
        }
    }
    ScreenContent(
        hasToolbar = true,
        toolbarTitle = team.teamName ?: EMPTY_STRING,
        toolbarIcon = painterResource(id = R.drawable.ic_arrow_back),
        onBackClicked = onBackClicked
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .padding(horizontal = 48.dp),
                alignment = Alignment.Center,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(team.teamBadge)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
            )
            team.teamShortName?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = it,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
            }

            team.league?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    text = stringResource(id = R.string.team_league, it),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            }

            team.formedYear?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = stringResource(
                        id = R.string.team_formed_year,
                        it.toString()
                    ),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            }

            team.stadiumCapacity?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = stringResource(
                        id = R.string.team_stadium_capacity,
                        it.toString()
                    ),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            }

            team.description?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = stringResource(id = R.string.team_description, it),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview("TeamDetailsScreenContentPreview")
@Composable
fun TeamDetailsScreenContentPreview() {
    TeamDetailsScreenContent(
        teamDetailsState = TeamDetailsUiState.Ready(
            teamDetails = TeamDetailsDisplayModel(
                teamName = "Arsenal",
                teamBadge = "https://www.thesportsdb.com/images/media/team/badge/n06q811667936857.png",
                teamShortName = "ARS",
                stadiumCapacity = 59897,
                formedYear = 1900,
                league = "Premier League",
                description = "Arsenal Football Club, communément appelé Arsenal, est un club anglais de football, fondé le 1er décembre 1886 à Londres. Son siège est situé dans le borough londonien d'Islington, au nord de la capitale britannique."
            )
        ),
        onBackClicked = {}
    )
}