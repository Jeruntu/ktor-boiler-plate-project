package com.example.di

import com.example.features.user.UserService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::UserService)
}
