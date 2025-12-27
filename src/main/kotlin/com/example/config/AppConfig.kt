package com.example.config

import io.ktor.server.config.ApplicationConfig

data class AppConfig(
    val storage: StorageConfig,
) {
    companion object {
        fun from(config: ApplicationConfig): AppConfig {
            return AppConfig(
                storage = StorageConfig(
                    driverClassName = config.property("storage.driverClassName").getString(),
                    jdbcURL = config.property("storage.jdbcURL").getString(),
                    user = config.property("storage.user").getString(),
                    password = config.property("storage.password").getString(),
                ),
            )
        }
    }
}

data class StorageConfig(
    val driverClassName: String,
    val jdbcURL: String,
    val user: String,
    val password: String,
)
