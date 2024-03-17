package com.example.db.dao

import com.example.db.models.News
import com.example.db.models.mapToNewsSerializable
import com.example.requestSerializables.NewsRequest
import com.example.responseSerializables.NewsSerializable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class NewsDAO {
    fun insertNews(news: NewsRequest): Int{
        try {
            transaction {
                News.insert(news)
            }
            return 200
        } catch (e: Exception){
            return 500
        }
    }

    fun getNews(): List<NewsSerializable> = transaction {
        News.selectAll().toList().map {
            mapToNewsSerializable(it)
        }
    }
}