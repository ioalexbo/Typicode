package com.softvision.alexlepadatu.typicode.di

import com.softvision.alexlepadatu.typicode.data.repository.AlbumsRepositoryImpl
import com.softvision.alexlepadatu.typicode.domain.repository.AlbumsRepository
import com.softvision.alexlepadatu.typicode.domain.useCase.albumsList.AlbumsUseCase
import com.softvision.alexlepadatu.typicode.domain.useCase.albumsList.AlbumsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAlbumsRepository(repositoryImpl: AlbumsRepositoryImpl): AlbumsRepository = repositoryImpl
}
