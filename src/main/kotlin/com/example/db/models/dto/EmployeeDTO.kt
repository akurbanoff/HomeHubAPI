package com.example.db.models.dto

import com.example.db.models.Employees
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class EmployeeDTO(
    val code: Int,
    val result: EmployeeResult
)

@Serializable
data class EmployeeResult(
    val execution_time: Double,
    val limit: Int,
    val list: List<EmployeeSerializable>,
    val order_by: String,
    val order_dir: String,
    val page: Int,
    val pages: Int,
    val total: Int
)

@Serializable
data class EmployeeSerializable(
    val created_at: String,
    val department: Department?,
    val email: String,
    val first_name: String?,
    val id: Int,
    val is_active: Boolean,
    val last_name: String,
    val last_visit_at: String,
    val local_phone: String?,
    val middle_name: String?,
    val office: Office?,
    val phone: String?,
    val photos: List<String>?,
    val position: String,
    val role: Role,
    val second_phone: String?,
    val show_on_site: Boolean,
    val supervisor: Supervisor?,
    val updated_at: String
)

@Serializable
data class Office(
    val id: Int,
    val name: String
)

@Serializable
data class Role(
    val id: Int,
    val name: String
)

@Serializable
data class Supervisor(
    val id: Int,
    val name: String
)

fun mapToEmployeeDTO(it: ResultRow): EmployeeSerializable{
    return EmployeeSerializable(
        id = it[Employees.id].value,
        created_at = it[Employees.createdAt],
        office = Office(it[Employees.officeId], it[Employees.officeName]),
        department = Department(it[Employees.departmentId], it[Employees.departmentName]),
        is_active = it[Employees.isActive],
        first_name = it[Employees.name],
        middle_name = it[Employees.name],
        last_name = it[Employees.name],
        phone = it[Employees.mainPhoneNumber],
        second_phone = it[Employees.secondPhoneNumber],
        email = it[Employees.email],
        position = it[Employees.position],
        supervisor = Supervisor(it[Employees.supervisorId], it[Employees.supervisorName]),
        role = Role(it[Employees.roleId], it[Employees.roleName]),
        photos = listOf(it[Employees.photo]),
        local_phone = "",
        updated_at = "",
        last_visit_at = "",
        show_on_site = false
    )
}