package com.decagon.mobiletesttask.data.repository

import com.decagon.mobiletesttask.data.remotedata.RandomUserApi
import com.decagon.mobiletesttask.data.remotedata.dto.UserDto
import com.decagon.mobiletesttask.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val api: RandomUserApi):UserRepository {
    override suspend fun getUserData(): List<UserDto> {
        return api.getUserData()
    }
}