package com.decagon.mobiletesttask.data.remotedata.dto

import com.decagon.mobiletesttask.domain.model.UserDetails

data class Result(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)

fun Result.toResultData():UserDetails{
    return UserDetails(
        id = id,
        name = name,
        gender = gender,
        phone = phone,
        email = email,
        location=location,
        medium = picture.medium,
        thumbnail = picture.thumbnail
    )
}