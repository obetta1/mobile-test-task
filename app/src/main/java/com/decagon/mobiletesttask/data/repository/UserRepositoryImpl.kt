package com.decagon.mobiletesttask.data.repository

import android.util.Log
import com.decagon.mobiletesttask.data.remotedata.RandomUserApi
import com.decagon.mobiletesttask.data.remotedata.dto.UserDto
import com.decagon.mobiletesttask.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: RandomUserApi):UserRepository {
    override suspend fun getUserData(): UserDto {
        Log.d("USER", "REOSITORY  ")

        return api.getUserData()
    }
}