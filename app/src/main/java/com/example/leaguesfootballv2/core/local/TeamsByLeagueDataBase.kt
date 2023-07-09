package com.example.leaguesfootballv2.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.leaguesfootballv2.data.model.JsonTeam

@Database(entities = [JsonTeam::class], version = 1)
abstract class TeamsByLeagueDataBase : RoomDatabase() {
    abstract fun teamsByLeagueDao(): TeamsByLeagueDao
    abstract fun teamDetailsDao(): TeamDetailsDao
}