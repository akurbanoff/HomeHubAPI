package com.example.db.models

import com.example.db.models.Dealings.nullable
import com.example.db.models.dto.EmployeeDTO
import com.example.db.models.dto.EmployeeSerializable
import io.ktor.client.statement.*
import io.ktor.http.*
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction


object Employees: Table("employees") {
    val createdAt = varchar("created_at", 40)
    val departmentId = integer("department_id")
    val departmentName = varchar("department_name", 20)
    val email = varchar("email", 30)
    val firstName = varchar("first_name", 40)
    val id = integer("id")
    val isActive = bool("is_active")
    val lastName = varchar("last_name", 40)
    val lastVisitAt = varchar("last_visit_at", 40)
    val localPhone = varchar("local_phone", 25).nullable()
    val middleName = varchar("middle_name", 40).nullable()
    val officeId = integer("office_id")
    val officeName = varchar("office_name", 20)
    val phone = varchar("phone", 25).nullable()
    val photo = varchar("photo", 200)
    val position = varchar("position", 40)
    val roleId = integer("role_id")
    val roleName = varchar("role_name", 40)
    val secondPhone = varchar("second_phone", 25).nullable()
    val showOnSite = bool("show_on_site")
    val supervisorId = integer("supervisor_id")
    val supervisorName = varchar("supervisor_name", 40)
    val updatedAt = varchar("updated_at", 40)
    var password = varchar("password", 200).nullable()  // захешированный
    val description = varchar("description", 200).nullable()

    override val primaryKey = PrimaryKey(id)

    fun insert(employee: EmployeeSerializable){
        transaction {
            Employees.insert{
                it[createdAt] = employee.created_at
                it[departmentId] = employee.department?.id ?: 0
                it[departmentName] = employee.department?.name ?: ""
                it[email] = employee.email
                it[firstName] = employee.first_name ?: ""
                it[id] = employee.id
                it[isActive] = employee.is_active
                it[lastName] = employee.last_name
                it[lastVisitAt] = employee.last_visit_at
                it[localPhone] = employee.local_phone ?: ""
                it[middleName] = employee.middle_name ?: ""
                it[officeId] = employee.office?.id ?: 0
                it[officeName] = employee.office?.name ?: ""
                it[phone] = employee.phone ?: ""
                it[photo] = employee.photos?.first() ?: ""
                it[position] = employee.position
                it[roleId] = employee.role.id
                it[roleName] = employee.role.name
                it[secondPhone] = employee.second_phone ?: ""
                it[showOnSite] = employee.show_on_site
                it[supervisorId] = employee.supervisor?.id ?: 0
                it[supervisorName] = employee.supervisor?.name ?: ""
                it[updatedAt] = employee.updated_at
                it[password] = ""
                it[description] = ""
            }
        }
    }

    fun delete(employee: EmployeeSerializable){
        transaction {
            Employees.deleteWhere {
                Employees.id eq employee.id
            }
        }
    }

    fun updatePassword(id: Int, newPassword: String): HttpStatusCode = transaction {
        Employees.update({ Employees.id eq id }) {
            it[password] = newPassword
        }
        return@transaction HttpStatusCode.OK
    }

    fun checkPassword(id: Int, checkPassword: String): HttpStatusCode = transaction {
        val employee = Employees.select{Employees.id eq id}.singleOrNull()
        if(employee != null){
            val storedPassword = employee[password]
            println(storedPassword)
            println(storedPassword == checkPassword)
            if(storedPassword == checkPassword){
                return@transaction HttpStatusCode.OK
            } else {
                return@transaction HttpStatusCode.BadRequest
            }
        } else {
            return@transaction HttpStatusCode.BadRequest
        }
    }
}

//class Employee(id: EntityID<Int>): IntEntity(id){
//    companion object : IntEntityClass<Employee>(Employees)
//
//    val createdAt by Employees.createdAt
//    val officeId by Employees.officeId
//    val officeName by Employees.officeName
//    val departmentId by Employees.departmentId
//    val departmentName by Employees.departmentName
//    val isActive by Employees.isActive
//    val firstName by Employees.firstName
//    val mainPhoneNumber by Employees.mainPhoneNumber
//    val secondPhoneNumber by Employees.secondPhoneNumber
//    val email by Employees.email
//    val position by Employees.position
//    val supervisorId by Employees.supervisorId
//    val supervisorName by Employees.supervisorName
//    val roleId by Employees.roleId
//    val roleName by Employees.roleName
//    val password by Employees.password
//    val description by Employees.description
//    val photo by Employees.photo
//}

