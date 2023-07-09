package com.example.leaguesfootballv2.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.leaguesfootballv2.data.model.JsonTeam

@Dao
interface TeamsByLeagueDao {

    @Query("SELECT * FROM teams")
    suspend fun getTeams(): List<JsonTeam>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(teams: List<JsonTeam>)

    @Query("DELETE FROM teams")
    suspend fun deleteAllTeams()
}