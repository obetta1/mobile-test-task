package com.decagon.mobiletesttask.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.mobiletesttask.common.Resource
import com.decagon.mobiletesttask.domain.model.UserDetails
import com.decagon.mobiletesttask.domain.usecase.GetUsersUseCase
import com.decagon.mobiletesttask.presentation.userdatail.UserDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) :ViewModel(){

    private val _state = mutableStateListOf<UserDetailState>()
    val state: SnapshotStateList<UserDetailState> get() = _state

    private val _stateFilter = mutableStateListOf<UserDetails>()
    val stateFilter: SnapshotStateList<UserDetails >get() = _stateFilter

    init {
        for (i in 0..2){
            getUserDetails()
        }
    }

    fun filterList(search:String):List<UserDetails>{

        return  if (search.isEmpty()){
            stateFilter
        }else{
            stateFilter.filter {
                it.name.last.lowercase().contains(search.lowercase())
                        || it.name.first.lowercase().contains(search.lowercase())
            }
        }
    }

     private fun getUserDetails(){
        viewModelScope.launch {
            try {
                getUsersUseCase().collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.add(UserDetailState(isLoading = true))
                        }
                        is Resource.Success -> {
                            _state.add(UserDetailState(userData = result.data!!.results))
                            _stateFilter.addAll(result.data.results)
                        }
                        is Resource.Error -> {
                            _state.add(UserDetailState(error = result.message!!))
                        }
                    }
                }
            }catch (e:IOException){
            }
        }
    }
}