package com.example.pertemuan6november.network

import com.example.pertemuan6november.model.Authors
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("authors")
    fun getAllAuthors() : Call<List<Authors>>
}