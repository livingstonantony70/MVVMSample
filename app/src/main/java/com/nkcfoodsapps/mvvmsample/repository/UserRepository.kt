package com.nkcfoodsapps.mvvmsample.repository

import com.nkcfoodsapps.mvvmsample.dummyapi.UserAPI
import com.nkcfoodsapps.mvvmsample.model.data.Employee
import com.nkcfoodsapps.mvvmsample.model.data.Employees
import com.nkcfoodsapps.mvvmsample.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class UserRepository
@Inject
constructor(
    /*@Named("name1")*/ private val userAPI: UserAPI
) {

    suspend fun getEmployees(): Flow<Resource<Employees>> = flow {
        emit(Resource.loading())
        delay(1000)

        try {
            emit(Resource.success(userAPI.getEmployees()))
        } catch (e: Exception) {
            emit(Resource.error(e.toString(), null))
        }
    }


    suspend fun getEmployee(id: Int): Flow<Resource<Employee>> = flow {
        emit(Resource.loading())
        delay(1000)

        try {
            emit(Resource.success(userAPI.getEmployee(id)))
        } catch (e: Exception) {
            emit(Resource.error(e.toString(), null))
        }
    }

    fun getName() = userAPI.getName()

}