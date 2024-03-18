package com.utad.examenfinalpmdm.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TaskApi {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ud5-server.onrender.com/api/v1/") // TODO poner el URL de la api acaba en /
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: TaskService by lazy {
        retrofit.create(TaskService::class.java)
    }

}