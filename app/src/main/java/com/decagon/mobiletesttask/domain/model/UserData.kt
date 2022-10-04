package com.decagon.mobiletesttask.domain.model

import com.decagon.mobiletesttask.data.remotedata.dto.Info
import com.decagon.mobiletesttask.data.remotedata.dto.Result

data class UserData(
    val info: Info,
    val results: List<Result>
)
