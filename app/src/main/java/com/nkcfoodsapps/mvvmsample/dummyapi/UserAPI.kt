package com.nkcfoodsapps.mvvmsample.dummyapi

import com.nkcfoodsapps.mvvmsample.model.data.Employee
import com.nkcfoodsapps.mvvmsample.model.data.Employees
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserAPI {
    @GET("employees")
    suspend fun getEmployees(): Employees

    @GET("employee/{id}")
    suspend fun getEmployee(@Path("id") id: Int): Employee

    fun getName():String
}