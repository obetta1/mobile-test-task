package com.decagon.mobiletesttask.data.remotedata

import com.decagon.mobiletesttask.data.remotedata.dto.UserDto
import retrofit2.http.GET

interface RandomUserApi {

    @GET("api")
    suspend fun getUserData():List<UserDto>
}