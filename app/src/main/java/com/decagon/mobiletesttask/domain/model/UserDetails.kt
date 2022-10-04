package com.decagon.mobiletesttask.domain.model

import com.decagon.mobiletesttask.data.remotedata.dto.*

data class UserDetails(
    val id: Id,
    val name: Name,
    val email: String,
    val gender: String,
    val phone: String,
    val medium: String,
    val thumbnail: String
)
