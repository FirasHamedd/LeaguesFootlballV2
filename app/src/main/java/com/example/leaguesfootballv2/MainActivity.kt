package com.example.leaguesfootballv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.leaguesfootballv2.data.datasource.LeaguesDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataSource: LeaguesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = dataSource.execute(param = Unit)
                println("hooo ${result.leagues.first().strLeague}")
            } catch (e: Exception) {
                println("hooo $e")
            }
        }
    }
}