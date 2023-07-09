package com.example.leaguesfootballv2.presentation.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.leaguesfootballv2.presentation.viewmodel.LeaguesViewModel
import com.example.leaguesfootballv2.presentation.viewmodel.TeamDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TEAM_ID = "teamId"
        private const val TEAM_DETAILS_ROUTING_PARAM = "{teamId}"
    }

    @Inject
    lateinit var leaguesViewModel: LeaguesViewModel

    @Inject
    lateinit var teamDetailsViewModel: TeamDetailsViewModel

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leaguesViewModel.getAllLeagues()
        leaguesViewModel.getPersistedTeams()
        setContent {
            navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavHostRouting.MAIN.route
            ) {
                composable(route = NavHostRouting.MAIN.route) {
                    MainScreen(
                        viewModel = leaguesViewModel,
                        onTeamClick = ::navigateToTeamDetails
                    )
                }
                composable(
                    route = NavHostRouting.DETAILS.route,
                    arguments = listOf(navArgument(TEAM_ID) { type = NavType.StringType })
                ) { navBackStackEntry ->
                    val teamId = navBackStackEntry.arguments?.getString(TEAM_ID)
                    TeamsDetailsScreen(
                        viewModel = teamDetailsViewModel,
                        teamId = teamId,
                        onBackClicked = ::onBackClicked
                    )
                }
            }
        }
    }

    private fun onBackClicked() {
        navController.popBackStack()
    }

    private fun navigateToTeamDetails(teamId: String) {
        navController.navigate(
            NavHostRouting.DETAILS.route.replace(
                TEAM_DETAILS_ROUTING_PARAM,
                teamId
            )
        )
    }

    enum class NavHostRouting(val route: String) {
        MAIN("main"),
        DETAILS("details/{teamId}")
    }
}