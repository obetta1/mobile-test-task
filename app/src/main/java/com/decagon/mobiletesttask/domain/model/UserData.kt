package com.decagon.mobiletesttask.domain.model

import com.decagon.mobiletesttask.data.remotedata.dto.Info

data class UserData(
    val info: Info,
    val results: List<UserDetails>
)
