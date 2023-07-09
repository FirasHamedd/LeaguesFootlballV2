package com.example.leaguesfootballv2.core.di

import android.content.Context
import androidx.room.Room
import com.example.leaguesfootballv2.core.local.TeamDetailsDao
import com.example.leaguesfootballv2.core.local.TeamsByLeagueDao
import com.example.leaguesfootballv2.core.local.TeamsByLeagueDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataBaseModule {

    @Provides
    fun provideTeamsDao(database: TeamsByLeagueDataBase): TeamsByLeagueDao {
        return database.teamsByLeagueDao()
    }

    @Provides
    fun provideTeamDetailsDao(database: TeamsByLeagueDataBase): TeamDetailsDao {
        return database.teamDetailsDao()
    }

    @Provides
    @Singleton
    fun provideTeamsByLeagueDataBase(@ApplicationContext appContext: Context): TeamsByLeagueDataBase {
        return Room.databaseBuilder(
            appContext,
            TeamsByLeagueDataBase::class.java,
            TeamsByLeagueDataBase::class.java.simpleName
        ).build()
    }
}