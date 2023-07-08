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
import com.example.leaguesfootballv2.R
import com.example.leaguesfootballv2.presentation.view.composable.AutoCompleteSearchBar

@Composable
fun MainScreen() {
    val leagues = listOf("Liga", "Ligue 1", "Premier League", "Bundesliga", "Eredivisie", "Serie A")
    val isUserTyping = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        AutoCompleteSearchBar(
            modifier = Modifier.fillMaxWidth(),
            items = leagues,
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
        }else {
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
    MainScreen()
}