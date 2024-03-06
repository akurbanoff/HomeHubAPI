package com.example.db.repositories

import com.example.db.dao.EmployeeDAO
import com.example.db.models.Employees
import com.example.db.models.dto.*
import com.example.parser.ApiParser
import com.example.responseSerializables.ClientResponse
import com.example.responseSerializables.DealingResponse
import com.example.responseSerializables.EmployeeResponse

class Repository {
    val dao = EmployeeDAO()
    val api = ApiParser()

    fun getEmployees(): EmployeeResponse{
        val newEmployees = api.updateEmployees()
        //dao.addEmployees(newEmployees)
        return newEmployees
    }

    fun getDealings(): DealingResponse{
        val dealings = api.getDealings()
        return dealings
    }

    fun getClients(): ClientResponse{
        val clients = api.getClientsLeads()
        return clients
    }
}