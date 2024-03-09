package com.example.db.models.dto

import com.example.db.models.Employees
import com.example.db.models.Employees.id
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
        created_at = it[Employees.createdAt],
        department = Department(it[Employees.departmentId], it[Employees.departmentName]),
        email = it[Employees.email],
        first_name = it[Employees.firstName],
        id = it[Employees.id],
        is_active = it[Employees.isActive],
        last_name = it[Employees.lastName],
        last_visit_at = it[Employees.lastVisitAt],
        local_phone = it[Employees.localPhone],
        middle_name = it[Employees.middleName],
        office = Office(it[Employees.officeId], it[Employees.officeName]),
        phone = it[Employees.phone],
        photos = listOf(it[Employees.photo]),
        position = it[Employees.position],
        role = Role(it[Employees.roleId], it[Employees.roleName]),
        second_phone = it[Employees.secondPhone],
        show_on_site = it[Employees.showOnSite],
        supervisor = Supervisor(it[Employees.supervisorId], it[Employees.supervisorName]),
        updated_at = it[Employees.updatedAt],
    )
}