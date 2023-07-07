package com.example.leaguesfootballv2.di

import com.example.leaguesfootballv2.data.datasource.LeaguesDataSource
import com.example.leaguesfootballv2.data.repository.LeaguesRepositoryImpl
import com.example.leaguesfootballv2.data.transformer.LeaguesToDomainTransformer
import com.example.leaguesfootballv2.domain.repository.LeaguesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class LeaguesRepositoryModule {

    @Provides
    fun provideLeaguesRepository(
        leaguesDataSource: LeaguesDataSource,
        transformer: LeaguesToDomainTransformer
    ): LeaguesRepository = LeaguesRepositoryImpl(
        dataSource = leaguesDataSource,
        transformer = transformer
    )
}