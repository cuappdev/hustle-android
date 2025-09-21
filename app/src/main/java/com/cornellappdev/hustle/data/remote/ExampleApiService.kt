package com.cornellappdev.hustle.data.remote

import com.cornellappdev.hustle.data.model.ExampleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExampleApiService {
    @GET("examples")
    suspend fun getExamples(): Response<List<ExampleModel>>
    @GET("examples/{id}")
    suspend fun getExample(@Path("id") id: Int): Response<ExampleModel>
}