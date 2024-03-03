package com.example.db.dao

import com.example.db.models.Employees
import com.example.db.models.Employees.createdAt
import com.example.db.models.Employees.departmentId
import com.example.db.models.Employees.departmentName
import com.example.db.models.Employees.description
import com.example.db.models.Employees.email
import com.example.db.models.Employees.id
import com.example.db.models.Employees.isActive
import com.example.db.models.Employees.mainPhoneNumber
import com.example.db.models.Employees.name
import com.example.db.models.Employees.officeId
import com.example.db.models.Employees.officeName
import com.example.db.models.Employees.password
import com.example.db.models.Employees.photo
import com.example.db.models.Employees.position
import com.example.db.models.Employees.roleId
import com.example.db.models.Employees.roleName
import com.example.db.models.Employees.secondPhoneNumber
import com.example.db.models.Employees.supervisorId
import com.example.db.models.Employees.supervisorName
import com.example.db.models.dto.EmployeeDTO
import com.example.db.models.dto.EmployeeSerializable
import com.example.db.models.dto.mapToEmployeeDTO
import com.example.parser.ApiParser
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
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
        employees.forEach { employee ->
            if(employee.is_active && !employeesList.contains(employee)) Employees.insert(employee)
        }
    }
}