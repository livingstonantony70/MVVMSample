package com.nkcfoodsapps.mvvmsample.model.data

import com.google.gson.annotations.Expose

data class Employees(
    val status: String?,

    val message: String?,

    val data: List<Data>?
){
    data class Data(
        val id: Int,
        val employee_name: String?,
        val employee_salary: Long,
        val employee_age: Int,
        val profile_image: String?
    )
}