package com.example

import com.example.config.AppConfig
import com.example.di.appModule
import com.example.features.user.userRoutes
import com.example.plugins.configureDatabase
import com.example.plugins.configureMonitoring
import com.example.plugins.configureStatusPages
import com.example.plugins.configureSwagger
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.koin.ktor.plugin.Koin

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    logger.info { "Starting the backend server..." }
    EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    val appConfig = AppConfig.from(environment.config)
    configureDatabase(appConfig.storage)
    configureMonitoring()
    configureStatusPages()
    configureSwagger()

    install(ContentNegotiation) {
        json()
    }

    install(Koin) {
        modules(appModule)
    }

    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
        userRoutes()
    }
}
