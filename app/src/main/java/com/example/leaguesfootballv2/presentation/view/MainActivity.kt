package com.example.leaguesfootballv2.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.leaguesfootballv2.presentation.viewmodel.LeaguesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: LeaguesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllLeagues()
        setContent {
            MainScreen(viewModel = viewModel)
        }
    }
}