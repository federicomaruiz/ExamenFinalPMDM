package com.utad.examenfinalpmdm.data.network

import com.utad.examenfinalpmdm.data.network.model.responseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TaskService {

    @GET("task/{apartmentName}")
    suspend fun getHomeWork(
        @Header("Authorization") userName: String,
        @Path("apartmentName") apartmentName: String
    ): Response<List<responseItem>>
}