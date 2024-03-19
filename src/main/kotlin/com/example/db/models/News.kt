package com.example.db.models

import com.example.requestSerializables.NewsRequest
import com.example.responseSerializables.NewsSerializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object News: Table("news"){
    val id = integer("news_id").autoIncrement()
    val title = varchar("title", 200)
    val createdAt = varchar("created_at", 50)
    val description = largeText("description")
    val photos = array<String>("photos")

    override val primaryKey: PrimaryKey = PrimaryKey(id)

    fun insert(news: NewsRequest){
        val calendar = Calendar.getInstance()
        println("news insert")
        transaction {
            News.insert {
                it[createdAt] = convertDate(
                    year = calendar.get(Calendar.YEAR),
                    month = calendar.get(Calendar.MONTH),
                    day = calendar.get(Calendar.DAY_OF_MONTH)
                )
                it[title] = news.title
                it[description] = news.description
                it[photos] = news.photos
            }
        }
    }

    private fun convertDate(year: Int, month: Int, day: Int) : String{
        val currentDate = "$day.$month.$year"
        return currentDate
    }
}

fun mapToNewsSerializable(result: ResultRow) : NewsSerializable{
    val photoList = mutableListOf<ByteArray>()
    if(result[News.photos].isNotEmpty()) {
        for (photo in result[News.photos]) {
            //println(result[News.title])
            //println(photo)
            val file = File("/photos/$photo")
            photoList.add(file.readBytes())
        }
    }

    return NewsSerializable(
        id = result[News.id],
        title = result[News.title],
        createdAt = result[News.createdAt],
        description = result[News.description],
        photos = photoList.toList()
    )
}