package com.example.leaguesfootballv2.di

import com.example.leaguesfootballv2.data.datasource.LocalTeamsDataSource
import com.example.leaguesfootballv2.data.datasource.TeamsDataSource
import com.example.leaguesfootballv2.data.repository.TeamsRepositoryImpl
import com.example.leaguesfootballv2.data.transformer.TeamsToDomainTransformer
import com.example.leaguesfootballv2.domain.repository.TeamsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class TeamsRepositoryModule {

    @Provides
    fun provideTeamsRepository(
        teamsDataSource: TeamsDataSource,
        transformer: TeamsToDomainTransformer,
        localTeamsDataSource: LocalTeamsDataSource
    ): TeamsRepository = TeamsRepositoryImpl(
        teamsDataSource = teamsDataSource,
        transformer = transformer,
        localTeamsDataSource = localTeamsDataSource
    )
}