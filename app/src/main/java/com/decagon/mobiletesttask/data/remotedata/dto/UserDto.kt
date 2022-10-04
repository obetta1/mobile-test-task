package com.decagon.mobiletesttask.data.remotedata.dto

import com.decagon.mobiletesttask.domain.model.ResultData
import com.decagon.mobiletesttask.domain.model.UserData

data class UserDto(
    val info: Info,
    val results: List<Result>
)

fun UserDto.toUserData():UserData{
    return UserData(
        info = info,
        results = results
    )
}