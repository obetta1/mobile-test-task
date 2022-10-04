package com.decagon.mobiletesttask.domain.usecase

import com.decagon.mobiletesttask.common.Resource
import com.decagon.mobiletesttask.data.remotedata.dto.UserDto
import com.decagon.mobiletesttask.data.remotedata.dto.toUserData
import com.decagon.mobiletesttask.domain.model.UserData
import com.decagon.mobiletesttask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository:UserRepository
){
    operator fun invoke():Flow<Resource<List<UserData>>> = flow {
        try {
            emit(Resource.Loading())
            val userData = repository.getUserData().map { it.toUserData() }
            emit(Resource.Success(userData))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Error has occurred"))
        }catch (e:IOException){
            emit(Resource.Error(e.message ?:"Unable to connect to network, Check your internet"))
        }
    }
}