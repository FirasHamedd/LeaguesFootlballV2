package com.example.leaguesfootballv2.core.local

import androidx.room.Dao
import androidx.room.Query
import com.example.leaguesfootballv2.data.model.JsonTeam

@Dao
interface TeamDetailsDao {

    @Query("SELECT * FROM teams WHERE idTeam=:teamId")
    suspend fun getSingleTeam(teamId: String): JsonTeam
}