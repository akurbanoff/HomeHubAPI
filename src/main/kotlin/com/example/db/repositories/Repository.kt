package com.example.db.repositories

import com.example.db.dao.EmployeeDAO
import com.example.db.models.Employees
import com.example.db.models.dto.*
import com.example.parser.ApiParser

class Repository {
    val dao = EmployeeDAO()
    val api = ApiParser()

    fun getEmployees(): List<EmployeeSerializable>{
        val newEmployees = api.updateEmployees()
        //dao.addEmployees(newEmployees)
        return newEmployees
    }

    fun getDealings(): ArrayList<Dealing>{
        val dealings = api.getDealings()
        return dealings
    }

    fun getClients(): List<Client>{
        val clients = api.getClientsLeads()
        return clients
    }
}