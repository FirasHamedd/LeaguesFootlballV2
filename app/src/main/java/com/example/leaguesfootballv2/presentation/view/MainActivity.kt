package com.example.leaguesfootballv2.presentation.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.leaguesfootballv2.presentation.viewmodel.LeaguesViewModel
import com.example.leaguesfootballv2.presentation.viewmodel.TeamDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var leaguesViewModel: LeaguesViewModel

    @Inject
    lateinit var teamDetailsViewModel: TeamDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamDetailsViewModel.getPersistedSingleTeam("133602")
        setContent {
            MainScreen(viewModel = leaguesViewModel)
        }
    }
}