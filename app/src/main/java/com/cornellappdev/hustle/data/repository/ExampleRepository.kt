package com.cornellappdev.hustle.data.repository

import com.cornellappdev.hustle.data.model.ExampleModel
import com.cornellappdev.hustle.data.remote.ExampleApiService
import javax.inject.Inject
import javax.inject.Singleton

interface ExampleRepository {
    suspend fun getExamples(): List<ExampleModel>
    suspend fun getExample(id: Int): ExampleModel?
}

@Singleton
class ExampleRepositoryImpl @Inject constructor(
    private val exampleApiService: ExampleApiService
) : ExampleRepository {
    override suspend fun getExamples(): List<ExampleModel> {
        return exampleApiService.getExamples().body() ?: emptyList()
    }

    override suspend fun getExample(id: Int): ExampleModel? {
        return exampleApiService.getExample(id).body()
    }
}