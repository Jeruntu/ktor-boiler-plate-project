package com.example.features.user

import com.example.module
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.config.MapApplicationConfig
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class UserIntegrationTest {

    @Test
    fun `test create and get users`() = testApplication {
        environment {
            config = MapApplicationConfig(
                "storage.driverClassName" to "org.h2.Driver",
                "storage.jdbcURL" to "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL",
                "storage.user" to "root",
                "storage.password" to "",
            )
        }
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // 1. Create a user
        val response = client.post("/users") {
            contentType(ContentType.Application.Json)
            setBody(CreateUserRequest("Alice", "alice@example.com"))
        }
        assertEquals(HttpStatusCode.Created, response.status)

        // 2. Get all users
        val listResponse = client.get("/users")
        assertEquals(HttpStatusCode.OK, listResponse.status)
        // Simple string check, in real app use deserialization
        assert(listResponse.bodyAsText().contains("alice@example.com"))
    }
}
