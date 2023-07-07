package com.example.leaguesfootballv2.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.leaguesfootballv2.R
import com.example.leaguesfootballv2.presentation.state.LeaguesUiState
import com.example.leaguesfootballv2.presentation.viewmodel.LeaguesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: LeaguesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getAllLeagues()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.leaguesUiState.collect { list ->
                    when(list) {
                        is LeaguesUiState.Idle -> println("hooo idle")
                        is LeaguesUiState.Loading -> println("hooo loading")
                        is LeaguesUiState.Ready -> println("hooo ${list.leagues[Random.nextInt( 0, list.leagues.size)]}")
                        is LeaguesUiState.Error -> println("hooo Error")
                    }
                }
            }
        }
    }
}