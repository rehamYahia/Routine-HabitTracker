package com.example.routinerhabittracker.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPerfModule {

    @Provides
    @Singleton
    fun provideShard()
    {

    }
}