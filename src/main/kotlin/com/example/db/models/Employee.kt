package com.example.db.models

import com.example.db.models.Dealings.nullable
import com.example.db.models.dto.EmployeeDTO
import com.example.db.models.dto.EmployeeSerializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction


object Employees: IntIdTable("employees") {
    val createdAt = varchar("created_at", 40)
    val officeId = integer("office_id")
    val officeName = varchar("office_name", 20)
    val departmentId = integer("department_id")
    val departmentName = varchar("department_name", 20)
    val isActive = bool("is_active")

    val name = varchar("name", 40)
    val mainPhoneNumber = varchar("main_phone_number", 15)
    val secondPhoneNumber = varchar("second_phone_number", 15)
    val email = varchar("email", 30)
    val position = varchar("position", 40)
    val supervisorId = integer("supervisor_id")
    val supervisorName = varchar("supervisor_name", 40)
    val roleId = integer("role_id")
    val roleName = varchar("role_name", 40)
    val password = varchar("password", 40).nullable()  // захешированный
    val description = varchar("description", 200).nullable()
    val photo = varchar("photo", 100)

    fun insert(employee: EmployeeSerializable){
        transaction {
            Employees.insert{
                it[Employees.id] = employee.id
                it[createdAt] = employee.created_at
                it[officeId] = employee.office?.id ?: 0
                it[officeName] = employee.office?.name ?: ""
                it[departmentId] = employee.department?.id ?: 0
                it[departmentName] = employee.department?.name ?: ""
                it[isActive] = employee.is_active
                it[name] = employee.first_name ?: ""
                it[mainPhoneNumber] = employee.phone ?: ""
                it[secondPhoneNumber] = employee.second_phone ?: ""
                it[email] = employee.email
                it[position] = employee.position
                it[supervisorId] = employee.supervisor?.id ?: 0
                it[supervisorName] = employee.supervisor?.name ?: ""
                it[roleId] = employee.role.id
                it[roleName] = employee.role.name
                it[password] = ""
                it[description] = ""
                it[photo] = employee.photos?.first() ?: ""
            }
        }
    }
}

class Employee(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<Employee>(Employees)

    val createdAt by Employees.createdAt
    val officeId by Employees.officeId
    val officeName by Employees.officeName
    val departmentId by Employees.departmentId
    val departmentName by Employees.departmentName
    val isActive by Employees.isActive
    val name by Employees.name
    val mainPhoneNumber by Employees.mainPhoneNumber
    val secondPhoneNumber by Employees.secondPhoneNumber
    val email by Employees.email
    val position by Employees.position
    val supervisorId by Employees.supervisorId
    val supervisorName by Employees.supervisorName
    val roleId by Employees.roleId
    val roleName by Employees.roleName
    val password by Employees.password
    val description by Employees.description
    val photo by Employees.photo
}

