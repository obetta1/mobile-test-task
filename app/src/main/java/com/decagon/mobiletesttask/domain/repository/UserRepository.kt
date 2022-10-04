package com.decagon.mobiletesttask.domain.repository

import com.decagon.mobiletesttask.data.remotedata.dto.UserDto

interface UserRepository {

    suspend fun getUserData():List<UserDto>
}