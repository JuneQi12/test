package com.example.mvvmchallenge.model

import javax.inject.Inject

class Repository @Inject constructor(private val getDogApi:GetDogApi){
    suspend fun getDog() = getDogApi.getDogInfo()

}