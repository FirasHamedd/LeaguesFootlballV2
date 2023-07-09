package com.example.leaguesfootballv2.di

import com.example.leaguesfootballv2.data.datasource.LocalTeamDetailsDataSource
import com.example.leaguesfootballv2.data.repository.TeamDetailsRepositoryImpl
import com.example.leaguesfootballv2.data.transformer.SingleTeamToDomainTransformer
import com.example.leaguesfootballv2.domain.repository.TeamDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class TeamDetailsRepositoryModule {

    @Provides
    fun provideTeamDetailsRepository(
        leaguesDataSource: LocalTeamDetailsDataSource,
        transformer: SingleTeamToDomainTransformer
    ): TeamDetailsRepository = TeamDetailsRepositoryImpl(
        teamDetailsDataSource = leaguesDataSource,
        transformer = transformer
    )
}