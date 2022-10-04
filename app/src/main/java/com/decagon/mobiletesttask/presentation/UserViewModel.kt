package com.decagon.mobiletesttask.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.mobiletesttask.common.Resource
import com.decagon.mobiletesttask.domain.usecase.GetUsersUseCase
import com.decagon.mobiletesttask.presentation.userdatail.UserDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) :ViewModel(){

    private val _state = mutableStateOf(UserDetailState())
    val state:State<UserDetailState> get() = _state

    init {
        getUserDetails()
    }

    private fun getUserDetails(){
        viewModelScope.launch {
            getUsersUseCase().onEach { result->
                when(result){
                    is Resource.Loading->{
                        _state.value = UserDetailState(isLoading = true)
                    }
                    is Resource.Success ->{
                        _state.value = UserDetailState(userData = result.data!!)
                    }
                    is Resource.Error ->{
                        _state.value = UserDetailState(error = result.message!!)
                    }
                }
            }
        }
    }
}