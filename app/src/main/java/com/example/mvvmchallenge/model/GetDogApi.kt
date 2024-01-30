package com.example.mvvmchallenge.model

import com.example.mvvmchallenge.model.data.GetDogResponse
import retrofit2.Response
import retrofit2.http.GET

interface GetDogApi {

    @GET(Constants.RANDOM_ENDPOINT)
    suspend fun getDogInfo(): Response<GetDogResponse>
}