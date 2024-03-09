package com.example.db.repositories

import com.example.db.dao.EmployeeDAO
import com.example.db.models.Employees
import com.example.db.models.dto.*
import com.example.parser.ApiParser
import com.example.requestSerializables.PasswordRequest
import com.example.responseSerializables.ClientResponse
import com.example.responseSerializables.DealingResponse
import com.example.responseSerializables.EmployeeResponse
import com.example.responseSerializables.PasswordResponse

class Repository {
    val dao = EmployeeDAO()
    val api = ApiParser()

    fun getEmployees(): EmployeeResponse{
        val newEmployees = api.getEmployees()
        dao.addEmployees(newEmployees.list)
        dao.checkActiveEmployees(newEmployees.list)
        return EmployeeResponse(dao.getEmployees())
    }

    fun getDealings(): DealingResponse{
        val dealings = api.getDealings()
        return dealings
    }

    fun getClients(): ClientResponse{
        val clients = api.getClientsLeads()
        return clients
    }

    fun insertPassword(request: PasswordRequest) : PasswordResponse{
        return PasswordResponse(dao.insertPassword(id = request.id, password = request.password).value)
    }

    fun checkPassword(request: PasswordRequest): PasswordResponse{
        return PasswordResponse(dao.checkPassword(id = request.id, checkPassword = request.password).value)
    }
}