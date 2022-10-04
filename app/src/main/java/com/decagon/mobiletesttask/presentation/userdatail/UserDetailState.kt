package com.decagon.mobiletesttask.presentation.userdatail

import com.decagon.mobiletesttask.domain.model.UserData
import com.decagon.mobiletesttask.domain.model.UserDetails

data class UserDetailState(
    val isLoading: Boolean = false,
    val userData: List<UserData>? = null,
    val error: String = ""
)


