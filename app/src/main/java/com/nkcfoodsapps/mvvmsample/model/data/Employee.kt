package com.nkcfoodsapps.mvvmsample.model.data

data class Employee(
    val status: String?,
    val message: String?,
    val data: Data?
) {
    data class Data(
        val id: Int,
        val employee_name: String?,
        val employee_salary: Long,
        val employee_age: Int,
        val profile_image: String?
    )
}
