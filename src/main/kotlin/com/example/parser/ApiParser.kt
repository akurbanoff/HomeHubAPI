package com.example.parser

import com.example.db.models.dto.*
import com.example.responseSerializables.ClientResponse
import com.example.responseSerializables.DealingResponse
import com.example.responseSerializables.EmployeeResponse
import io.github.cdimascio.dotenv.dotenv
import io.ktor.utils.io.core.*
import io.ktor.utils.io.errors.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.io.use

enum class Stages(val title: String){
    OBJECT_SOLD("Объект продан"),
    CLIENT_BOUGHT("Сделка состоялась")
}

class ApiParser{
    val dotenv = dotenv()
    val GET_POST_API_KEY = dotenv["GET_POST_API_KEY"]
    val client = OkHttpClient()
    val url = "https://faber.yucrm.ru/api/v1/$GET_POST_API_KEY/"

    fun updateEmployees(): EmployeeResponse{
        val request = Request.Builder().url(url + "employees/list").build()
        client.newCall(request).execute().use {response ->
            if(!response.isSuccessful){
                throw IOException("Сервер не отвечает либо api_key устарел")
            }
            val json = Json.decodeFromString<EmployeeDTO>(response.body!!.string())
            val employees = EmployeeResponse(json.result.list.filter { it.is_active })
            return employees
        }
    }
    fun getDealings(): DealingResponse {
        var page = 1
        val pages = 1
        val request = Request.Builder().url(url + "clients/list?limit=1000&page=$page")
            .build()
        client.newCall(request).execute().use {response ->
            val json = Json.decodeFromString<DealingDTO>(response.body!!.string())
            val dealings = json.result.list.filter { it.stage?.name == Stages.OBJECT_SOLD.title || it.stage?.name == Stages.CLIENT_BOUGHT.title }
            return DealingResponse(if(dealings.isEmpty()){
                    emptyList()
                } else {
                    dealings
                }
            )
        }
    }

    fun getClientsLeads(): ClientResponse {
        val request = Request.Builder().url(url + "leads/list").build()
        client.newCall(request).execute().use {response ->
            val dto = Json.decodeFromString<ClientDTO>(response.body!!.string())
            return ClientResponse(dto.result.list)
        }
    }
}