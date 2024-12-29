package com.example.plugins

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.server.application.Application
import io.ktor.server.config.ApplicationConfig
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

private val logger = KotlinLogging.logger {}

fun Application.configureDatabase(config: ApplicationConfig) {
    logger.info { "Initialize database" }

    val url = config.property("storage.jdbcURL").getString()
    val user = config.property("storage.user").getString()
    val password = config.property("storage.password").getString()

    Flyway.configure()
        .dataSource(url, user, password)
        .load().migrate()

    Database.connect(
        url,
        user = user,
        password = password
    )
}
