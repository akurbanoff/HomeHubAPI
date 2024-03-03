package com.example.db.models

import org.jetbrains.exposed.sql.Table

object Company: Table() {
    val id = integer("id")

    override val primaryKey = PrimaryKey(id)
}