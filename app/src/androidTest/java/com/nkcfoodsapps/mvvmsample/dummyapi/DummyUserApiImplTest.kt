package com.nkcfoodsapps.mvvmsample.dummyapi

import com.nkcfoodsapps.mvvmsample.model.data.Employee
import com.nkcfoodsapps.mvvmsample.model.data.Employees
import javax.inject.Inject

class DummyUserApiImplTest : UserAPI {
    private val employees = listOf(
        Employees.Data(
            1,
            "employee_name 1 test",
            1 + 100L,
            1 * 10,
            ""
        ),
        Employees.Data(
            2,
            "employee_name 2 test",
            2 + 100L,
            2 * 10,
            ""
        ),
        Employees.Data(
            3,
            "employee_name 3 test",
            3 + 100L,
            3 * 10,
            ""
        ),
    )


    override suspend fun getEmployees(): Employees {
        return Employees(
            "success",
            "Dummy API",
            employees
        )
    }

    override suspend fun getEmployee(id: Int): Employee {
        return Employee(
            "success",
            "Dummy api",
            null
        )
    }

    override fun getName(): String {
        return "Test execution"
    }
}
