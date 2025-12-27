package com.example.features.user

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val userService by inject<UserService>()

    route("/users") {
        post {
            val user = call.receive<CreateUserRequest>()
            val id = userService.create(user)
            call.respond(HttpStatusCode.Created, mapOf("id" to id))
        }

        get {
            val users = userService.findAll()
            call.respond(users)
        }
    }
}
