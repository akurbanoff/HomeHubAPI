package com.example.db.dao

import com.example.db.models.News
import com.example.db.models.mapToNewsSerializable
import com.example.requestSerializables.NewsRequest
import com.example.responseSerializables.NewsSerializable
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class NewsDAO {
    fun insertNews(news: NewsRequest): Int{
        try {
            println("insert news started")
            transaction {
                News.insert(news)
            }
            println("insert news was ended")
            return 200
        } catch (e: Exception){
            return 500
        }
    }

    fun getNews(skip: Int, limit: Int): List<NewsSerializable> = transaction {
        News.selectAll().limit(limit, offset = skip.toLong()).toList().map {
            mapToNewsSerializable(it)
        }
    }
}