package com.example.features.user

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Serializable
data class User(val id: Int, val name: String, val email: String)

@Serializable
data class CreateUserRequest(val name: String, val email: String)

object Users : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val email = varchar("email", 255).uniqueIndex()

    override val primaryKey = PrimaryKey(id)
}

class UserService {

    suspend fun create(user: CreateUserRequest): Int = newSuspendedTransaction {
        Users.insert {
            it[name] = user.name
            it[email] = user.email
        } get Users.id
    }

    suspend fun findAll(): List<User> = newSuspendedTransaction {
        Users.selectAll().map {
            User(
                id = it[Users.id],
                name = it[Users.name],
                email = it[Users.email],
            )
        }
    }
}
