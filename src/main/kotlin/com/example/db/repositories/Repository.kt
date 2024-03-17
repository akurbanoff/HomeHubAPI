package com.example.db.repositories

import com.example.db.dao.EmployeeDAO
import com.example.db.dao.NewsDAO
import com.example.db.models.Employees
import com.example.db.models.dto.*
import com.example.parser.ApiParser
import com.example.requestSerializables.NewsRequest
import com.example.requestSerializables.PasswordRequest
import com.example.responseSerializables.*

class Repository {
    val employeeDao = EmployeeDAO()
    val newsDao = NewsDAO()
    val api = ApiParser()

    fun getEmployees(): EmployeeResponse{
        val newEmployees = api.getEmployees()
        employeeDao.addEmployees(newEmployees.list)
        employeeDao.checkActiveEmployees(newEmployees.list)
        return EmployeeResponse(employeeDao.getEmployees())
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
        return PasswordResponse(employeeDao.insertPassword(id = request.id, password = request.password).value)
    }

    fun checkPassword(request: PasswordRequest): PasswordResponse{
        return PasswordResponse(employeeDao.checkPassword(id = request.id, checkPassword = request.password).value)
    }

    fun checkExistPassword(id: Int): PasswordExistResponse{
        return PasswordExistResponse(employeeDao.checkExistPassword(id))
    }

    fun getNews(skip: Int, limit: Int): NewsResponse{
        return NewsResponse(newsDao.getNews(skip, limit))
    }

    fun insertNews(newsRequest: NewsRequest): NewsInsertResponse{
        return NewsInsertResponse(newsDao.insertNews(newsRequest))
    }
}