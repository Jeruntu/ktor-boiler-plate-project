package com.example.plugins

import com.example.config.StorageConfig
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.server.application.Application
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

private val logger = KotlinLogging.logger {}

fun Application.configureDatabase(config: StorageConfig) {
    logger.info { "Initialize database" }

    Flyway.configure()
        .dataSource(config.jdbcURL, config.user, config.password)
        .load().migrate()

    Database.connect(
        url = config.jdbcURL,
        user = config.user,
        password = config.password,
        driver = config.driverClassName,
    )
}
