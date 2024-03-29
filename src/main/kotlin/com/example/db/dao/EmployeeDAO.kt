package com.example.db.dao

import com.example.db.models.Employees
import com.example.db.models.dto.EmployeeSerializable
import com.example.db.models.dto.mapToEmployeeDTO
import com.example.parser.ApiParser
import io.ktor.http.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class EmployeeDAO {
    fun getEmployees(): List<EmployeeSerializable> = transaction {
        Employees.selectAll().toList().map{
            mapToEmployeeDTO(it)
        }
    }
    fun addEmployees(employees: List<EmployeeSerializable>) = transaction{
        val employeesList = getEmployees()
        val indexList = mutableListOf<Int>()
        employeesList.forEach { db_employee ->
            indexList.add(db_employee.id)
        }
        employees.forEach { employee ->
            if(!indexList.contains(employee.id)) Employees.insert(employee)
        }
    }

    fun checkActiveEmployees(employees: List<EmployeeSerializable>) = transaction {
        val employeeList = getEmployees()
        employees.forEach { employee ->
            if(employeeList.contains(employee)){
                val currentEmployee = employeeList[employeeList.indexOf(employee)]
                if(currentEmployee.is_active != employee.is_active) Employees.delete(employee)
            }
        }
    }

    fun insertPassword(id: Int, password: String) : HttpStatusCode = transaction {
        Employees.updatePassword(id = id, newPassword = password)
    }

    fun checkPassword(id: Int, checkPassword: String) : HttpStatusCode = transaction {
        Employees.checkPassword(id = id, checkPassword = checkPassword)
    }

    fun checkExistPassword(id: Int): Boolean = transaction{
        !Employees.select(Employees.id eq id).empty()
    }
}