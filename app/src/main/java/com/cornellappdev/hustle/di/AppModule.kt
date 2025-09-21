package com.cornellappdev.hustle.di

import com.cornellappdev.hustle.data.repository.ExampleRepository
import com.cornellappdev.hustle.data.repository.ExampleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindExampleRepository(
        exampleRepositoryImpl: ExampleRepositoryImpl
    ): ExampleRepository
}
